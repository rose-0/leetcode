package leecode.dfs;

public class 在二叉树中分配硬币_979 {
    /*
    https://leetcode-cn.com/problems/distribute-coins-in-binary-tree/solution/hou-xu-bian-li-cong-dang-qian-jie-dian-na-chu-node/
    dfs时针对一个具体的节点，只考虑从当前节点拿走多少而不考虑拿入，因为拿走负数个金币就等价于拿入对应金币。
    对于当前节点node，我们只需要从node拿走数目为node.val-1的金币就可以使得金币为1，因为拿入金币给node就相当于从node拿走负数个数的金币，二者完全等价。

    之所以用的是后序遍历是因为，当我们考察节点node的时候，我们希望node的左右子树已经满足要求了，
    否则还得从根节点拿走金币分发给左右子树，与“只考虑拿走这一动作”冲突（对于左右子树又是拿入动作）

     */
    int sum=0;
    public int distributeCoins(TreeNode root) {
        //当前节点的金币数为1的，需要拿出的金币数目为0时
        if(dfs(root)==0){
            return sum;
        }
        return -1;
    }

    public int dfs(TreeNode root){//返回使当前节点的金币数为1的，需要拿出的金币数目
        if(root==null){
            return 0;
        }
        int left=dfs(root.left);//要使左子树满足题意，需要从左子树拿走的金币数目,可能==0，正数或负数
        sum=sum+Math.abs(left);//移动次数即拿走的金币绝对值
        int right=dfs(root.right);
        sum=sum+Math.abs(right);
        return left+right+root.val-1;//此时当前节点node金币数目为（l+r+node.val），返回保留一个金币需要拿走的金币数
    }
}

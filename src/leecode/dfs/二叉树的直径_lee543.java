package leecode.dfs;

public class 二叉树的直径_lee543 {
    //看sammy的题解，就知道为什么要设置一个全局的max
    public int max=0;
    public int diameterOfBinaryTree(TreeNode root) {
        calDepth(root);
        return max;
    }
    /*
                    1
                   / \
                   2  3
                  / \
                  4  5
     */

    //二叉树深度：求根节点到最远叶子节点最长路径的节点数目！！
    //calDepth就是返回二叉树的深度，返回的是节点数，而直径记录的是边数  // 返回max{1左边节点数，1右边节点数}+1
    //经过根的直径长度（1的直径是3）=左子树深度（2）+右子树深度（1） // 1左边两个节点 + 1右边一个节点
    public int calDepth(TreeNode root){
        if(root==null){
            return 0;
        }
        int left=calDepth(root.left);
        int right =calDepth(root.right);
        //我想的是left是返回左子树最大直径（不一定过左孩子）
        //为什么不是Math.max(max,Math.max(left,right)+1)
        //可以理解为 max=Math.max(max,left+right+0)//左边深度+右边深度+自己深度（自己深度为0）
        max=Math.max(max,left+right);//记录每经过一个节点时候，以该节点为根的直径=左子树深度+右子树深度
        return Math.max(left,right)+1;//返回树的深度
    }
}

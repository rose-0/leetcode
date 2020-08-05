package leecode.dfs;

public class 求根到叶子节点数字之和_129 {
    public int sumNumbers(TreeNode root) {
        if(root==null){
            return 0;
        }
        return childsum(root,0);
    }
    public int childsum(TreeNode root,int sum){
        /*
        如    4
             /
            1
           4右边节点为空应该返回0，而不是40(return 10*sum;)
         */
        if(root==null){
            return 0;
        }
        //遍历到当前层时，上面层累加和为sum
        //如果当前为叶子节点，则没有左右子树，则sum*10加上当前节点的值，并返回！！
        if(root.left==null&&root.right==null){
            return 10*sum+root.val;
        }
        //不是叶子节点，则对左右子树处理，
        return childsum(root.left,10*sum+root.val)+childsum(root.right,10*sum+root.val);
    }
}

package leecode.dfs;

public class 路径总和_112 {
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root==null){
            return false;
        }
        if(root.left==null&&root.right==null){
            return sum-root.val==0;
        }
        return hasPathSum(root.left,sum-root.val)||hasPathSum(root.right,sum-root.val);

    }
    /*
    本质上还是利用那个博客上面讲的递归的思想，想着左右子树分别返回的应该是是否左右子树存在
    路径和等于sum-根的值，拿到这个返回结果后，本层递归返回它们的或，
    递归终止的条件是节点为空，（节点为空只是一个边界情况，不算是递归终止的条件，叶子节点才是）或者节点为叶子节点时判断是返回true，还是false，
    这儿要分两个情况是因为节点为空和节点为叶子节点时返回的结果是不一样的
    如果不对叶子节点进行讨论，则叶子节点再进行递归，左右子树为空返回false，叶子节点则只能返回false
    所以应该对叶子节点单独讨论
    上面那些递归的题目中如果不对叶子节点讨论是，左右子树为空的情况的返回结果就是叶子节点
    应该返回的结果则不必要对叶子节点进行讨论
     */
    public boolean hassum(TreeNode root,int sum){
        if(root==null){
            return false;
        }
        if(root.left==null&&root.right==null){
            if(root.val==sum){
                return true;
            }else {
                return false;
            }
        }else {
            return hassum(root.left,sum-root.val)||hassum(root.right,sum-root.val);
        }
    }














    public boolean hasPathSum2(TreeNode root, int sum) {
        if(root.left==null&&root.right==null){
            return root.val==sum;
        }
        return hassum(root.left,sum-root.val)||hassum(root.right,sum-root.val);

    }
}

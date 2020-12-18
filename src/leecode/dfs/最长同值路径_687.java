package leecode.dfs;

public class 最长同值路径_687 {
    /*
    对比 lee124
    https://leetcode-cn.com/problems/longest-univalue-path/solution/687-by-ikaruga/
     */
    int max=0;
    public int longestUnivaluePath(TreeNode root) {
        longPath(root);
        return max;
    }
    public int longPath(TreeNode root){

        if(root==null){
            return 0;
        }

        int left=longPath(root.left);
        int right=longPath(root.right);

        left=(root.left!=null&&root.val==root.left.val)?left+1:0;
        right=(root.right!=null&&root.val==root.right.val)?right+1:0;

        max=Math.max(left+right,max);

        return Math.max(left,right);//这里返回值其实已经考虑了头节点，上面 left 和 right 已经进行了更新
    }
}

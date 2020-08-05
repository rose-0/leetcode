package leecode.dfs;

public class 二叉树最大路径_打印 {
    static int max=0;
    static TreeNode node=null;
//    public static
    public static int maxPathSum2(TreeNode root) {
//        int len = 0;
        maxPathSumMe(root);//写成max=maxPathSumMe(root) 就不对了，等于max是返回的return！！为子树最大值
        return max;
    }

    public static int maxPathSumMe(TreeNode root) {
        if(root==null){
            return 0;
        }
        int left=Math.max(0,maxPathSumMe(root.left));//// 如果子树路径和为负则应当置0表示最大
        int right=Math.max(0,maxPathSumMe(root.right));
//        max=Math.max(left+right+root.val,max);
        if(left+right+root.val>max){
            max=left+right+root.val;
            node=root;
        }
//        return Math.max(len,Math.max(left,right));//这样写不对，应该返回本节点的最大值
        return Math.max(left,right)+root.val;
    }

}

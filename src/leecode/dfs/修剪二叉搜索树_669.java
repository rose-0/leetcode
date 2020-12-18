package leecode.dfs;

public class 修剪二叉搜索树_669 {
    /*
    注意这个题目是bst，所以如果删除带有左右孩子的父节点 那么肯定会删一半树
    https://leetcode-cn.com/problems/trim-a-binary-search-tree/solution/669-xiu-jian-er-cha-sou-suo-shu-di-gui-die-dai-xia/
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if(root==null){
            return null;
        }
        //下面两个if处理特别巧妙
        if(root.val<low){
            TreeNode right=trimBST(root.right,low,high);
            return right;
        }
        if(root.val>high){
            TreeNode left=trimBST(root.left,low,high);
            return left;
        }
        //这里也要赋值
        root.left=trimBST(root.left,low,high);
        root.right=trimBST(root.right,low,high);
        return root;
    }
}

package leecode.dfs;

public class 翻转二叉树_226 {
    public TreeNode invertTree(TreeNode root) {
        if(root==null){
            return null;
        }
        TreeNode head=new TreeNode(root.val);
        head.left=invertTree(root.right);
        head.right=invertTree(root.left);
        return head;
    }
    //不开辟空间的方式:
    public TreeNode invertTree2(TreeNode root) {
        if(root==null){
            return null;
        }
        TreeNode right=root.right;
        root.right=invertTree(root.left);
        root.left=invertTree(right);
        return root;
    }
}

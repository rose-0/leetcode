package leecode.dfs;
//剑指offer 二叉树镜像
//https://leetcode-cn.com/problems/er-cha-shu-de-jing-xiang-lcof/solution/mian-shi-ti-27-er-cha-shu-de-jing-xiang-di-gui-fu-/
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
    //非递归

}

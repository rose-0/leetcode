package leecode.dfs;

public class 最大BST子树_333 {
    public int largestBSTSubtree(TreeNode root){
        if(root==null){
            return 0;
        }
        if(isBst(root)){
            return countTree(root);
        }
        return Math.max(largestBSTSubtree(root.left),largestBSTSubtree(root.right));
    }
    //方法1 对于每一个节点，都来验证其是否是 BST，如果是的话，就统计节点的个数
    TreeNode pre=null;

    public boolean isBst(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (isBst(root.left)) {
            if (pre != null) {
                if (pre.val > root.val) {
                    return false;
                }
            }
            pre = root;
            return isBst(root.right);

        } else {
            return false;
        }
    }

    public int countTree(TreeNode root){
        if(root==null){
            return 0;
        }
        return countTree(root.left)+countTree(root.right)+1;
    }
}

package leecode.dfs;

public class 二叉搜索树的最近公共祖先_235 {
    //直接使用 lee236的解题方法
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null && right == null) {
            return null;
        }
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        return root;
    }

    /*
    利用二叉搜索树的性质：p.val 和 q.val 都比 root.val 小，那么 p、q 肯定在 root 的左子树。
    p 和 q 的值不满足都大于（小于）root.val：
        1、p 和 q 分居 root 的左右子树。
        2、root 就是 p，q 在 p 的子树中。
        3、root 就是 q，p 在 q 的子树中
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if(root.val>p.val&&root.val>q.val){
            return  lowestCommonAncestor2(root.left,p,q);
        }
        if(root.val<p.val&&root.val<q.val){
            return lowestCommonAncestor2(root.right,p,q);
        }
        return root;
    }
    //如果 p 和 q 的节点值都小于 root.val，它们都在 root 的左子树，root = root.left
    //
    //其他情况，当前的 root 就是最近公共祖先，结束遍历， break。
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        while (root!=null){
            if(root.val>p.val&&root.val>q.val){
                root=root.left;
            }else if(root.val<p.val&&root.val<q.val){
                root=root.right;
            }else {
                break;
            }
        }
        return root;
    }
}
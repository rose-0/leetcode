package leecode.dfs;

public class 二叉树的最近公共祖先_236 {
    //https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/solution/236-er-cha-shu-de-zui-jin-gong-gong-zu-xian-hou-xu/
    //讲解的十分清楚

    /*
    https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/solution/c-jing-dian-di-gui-si-lu-fei-chang-hao-li-jie-shi-/
    lowestCommonAncestor这个函数不要理解为找公共祖先，而就理解为帮两个节点找祖先
    传入的值是root, p, q，帮p和q找到一个祖先就行，找到两个就更好了，如果找不到就返回NULL

    在root->left里面找一次，root->right里面再找一次，如果某一边返回值是NULL，
     那么说明两个值都在另一边 由于找的时候，一定是找的最近的祖先返回，所以这里直接返回前面的返回值就行了，
     可以保证是最近的公共祖先 如果左右的返回值都不是NULL，那说明p和q分别在两边，则当前节点就是最近公共祖先
     左右都找不到就直接返回NULL
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if(root == null || root == p || root == q) return root;
        //后序遍历，当遇到节点 p 或 q 时返回 看上面递归终止的条件 left / right 的返回值是 null p q 这三个中的一个
        TreeNode left = lowestCommonAncestor(root.left, p, q);

        TreeNode right = lowestCommonAncestor(root.right, p, q);


        if(left == null && right == null) return null; // 1.
        if(left == null) return right; // 3.  返回前面的 保证是最近公共祖先
        if(right == null) return left; // 4.
        return root; // 2. if(left != null and right != null)
    }
}

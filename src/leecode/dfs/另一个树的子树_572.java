package leecode.dfs;

public class 另一个树的子树_572 {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if(s==null){
            return false;
        }
        /*
        这样写是错的 如[1,1] 和 [1]
        if(s.val==t.val){
            return isSameTree(s,t);
        }
        return isSubtree(s.left,t) || isSubtree(s.right,t);
         */
        //t是s的子树，要么t等于s，要么t等于s的左/右子树。
        return isSameTree(s,t)||isSubtree(s.left,t) || isSubtree(s.right,t);
    }
    public boolean isSameTree(TreeNode s, TreeNode t){
        if(s==null&&t==null){
            return true;
        }
        if(s==null&&t!=null||s!=null&&t==null){
            return false;
        }
        if(s.val!=t.val){
            return false;
        }
        return isSameTree(s.left,t.left)&&isSameTree(s.right,t.right);
    }
}

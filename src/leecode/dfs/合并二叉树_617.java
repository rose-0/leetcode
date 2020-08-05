package leecode.dfs;

public class 合并二叉树_617 {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1==null&&t2==null){
            return null;
        }
        TreeNode head=null;
        /*
        这里返回t1不能用new 这样会出错:
           1        1
          /          \
         2            2
        /              \
       3                3
       递归到第二步时，左边的left不为空，右边的left为空，如果new的话，返回的结果只有左边的left节点一个
       丢掉了左边的left的left节点！！！
         */
        if(t1!=null&&t2==null){
            return new TreeNode(t1.val);
        }
        if(t1==null&&t2!=null){
            return new TreeNode(t2.val);
        }
        if(t1!=null&&t2!=null){
            head=new TreeNode(t1.val+t2.val);
        }
        head.left=mergeTrees(t1.left,t2.left);
        head.right=mergeTrees(t1.right,t2.right);
        return head;
    }
    public TreeNode mergeTrees2(TreeNode t1, TreeNode t2) {
        if(t1==null){
            return t2;
        }
        if(t2==null){
            return t1;
        }
        TreeNode head=null;
//        if(t1!=null&&t2==null){
//            return new TreeNode(t1.val);
//        }
//        if(t1==null&&t2!=null){
//            return new TreeNode(t2.val);
//        }
        if(t1!=null&&t2!=null){
            head=new TreeNode(t1.val+t2.val);
        }
        head.left=mergeTrees(t1.left,t2.left);
        head.right=mergeTrees(t1.right,t2.right);
        return head;
    }
}

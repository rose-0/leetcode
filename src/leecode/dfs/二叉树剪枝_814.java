package leecode.dfs;

public class 二叉树剪枝_814 {
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
  /*
  子树问题套路之递归子问题：
  先写出满足条件的子树的递归函数。然后对自身调用这个递归函数，并且对左右子树递归调用【原问题函数】！！！
  https://leetcode-cn.com/problems/binary-tree-pruning/solution/814er-cha-shu-jian-zhi-zi-shu-wen-ti-tao-lu-zhi-di/
  类似的题目
  572. 另一个树的子树
  剑指 Offer 26. 树的子结构
  1367. 二叉树中的列表
   */
    public TreeNode pruneTree(TreeNode root) {
        if(root==null){
            return root;
        }
        if(isZero(root)){
            return null;
        }
        root.left=pruneTree(root.left);
        root.right=pruneTree(root.right);
        return root;
    }
    public boolean isZero(TreeNode node){
        if(node==null){
            return true;
        }
        if(node.val!=0){
            return false;
        }
        return isZero(node.left)&&isZero(node.right);
    }
}

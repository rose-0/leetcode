package leecode.dfs;

import java.util.LinkedList;
import java.util.Queue;

//判断一个二叉树是不是完全二叉树
public class 二叉树的完全性校验_958 {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
    public boolean isCompleteTree(TreeNode root) {
        boolean leaf=false;
        Queue<TreeNode>queue=new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            root=queue.poll();
            if((leaf&&(root.left!=null||root.right!=null))||(root.left==null&&root.right!=null)){
                return false;
            }
            if(root.left!=null){
                queue.offer(root.left);
            }
            if(root.right!=null){
                queue.offer(root.right);
            }else {
                leaf=true;
            }
        }
        return true;
    }
}

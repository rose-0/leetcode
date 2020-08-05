package leecode.dfs;

import sun.reflect.generics.tree.Tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 二叉树的右视图_199 {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer>list=new LinkedList<>();
        if(root==null){
            return list;
        }
        Queue<TreeNode>queue=new LinkedList<>();
        TreeNode last=root;
        queue.offer(root);
        TreeNode nlast=null;
        while (!queue.isEmpty()){
            root=queue.poll();
            if(root.left!=null){
                queue.offer(root.left);
                nlast=root.left;
            }
            if(root.right!=null){
                queue.offer(root.right);
                nlast=root.right;
            }
            if(root==last){
                list.add(root.val);
                last=nlast;
                nlast=null;
            }
        }
        return list;
    }
    List<Integer>res=new LinkedList<>();
    public List<Integer> rightSideDfs(TreeNode root){
        dfsnode(root,0);
        return res;
    }
    public void dfsnode(TreeNode root,int depth){
        if(root==null){
            return;
        }
        if(res.size()==depth){
            res.add(root.val);
        }
        dfsnode(root.right,depth+1);
        dfsnode(root.left,depth+1);
    }
}

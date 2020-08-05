package leecode.dfs;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class 二叉树的锯齿形层序遍历_103 {
    List<List<Integer>> res=new ArrayList<>();

    public List<List<Integer>> zigzagLevelOrder(TreeNode root){
        Deque<TreeNode>deque=new LinkedList<>();
        deque.addFirst(root);
        boolean rl=true;

        TreeNode last=root;
        TreeNode nlast=null;
        List<Integer>list=new ArrayList<>();
        while (!deque.isEmpty()){
            if(rl){//从右到左打印，先进左孩子，再进右孩子
                 root=deque.getFirst();
                if(root.left!=null){
                    nlast=nlast==null?root.left:nlast;
                    deque.offerLast(root.left);
                }
                if(root.right!=null){
                    nlast=nlast==null?root.right:nlast;
                    deque.offerLast(root.right);
                }
            }
            else {
                 root=deque.getLast();
                if(root.right!=null){
                    nlast=nlast==null?root.right:nlast;
                    deque.offerFirst(root.right);
                }
                if(root.left!=null){
                    nlast=nlast==null?root.left:nlast;
                    deque.offerFirst(root.left);
                }
            }
            list.add(root.val);
            if(last==root){
                rl=!rl;
                last=nlast;
                nlast=null;
                res.add(list);
                list=new ArrayList<>();//不要忘记这个
            }
        }
        return res;
    }
}

package zuoshen.Tree;

import java.util.LinkedList;
import java.util.Queue;

public class 判断一颗二叉树是否为完全二叉树 {
    public boolean isCbt(treeNode head){
        if(head==null){
            return true;
        }
        Queue<treeNode>queue=new LinkedList<>();
        boolean leaf=false;
        queue.offer(head);
        while (!queue.isEmpty()){
            head=queue.poll();
            if((leaf&&(head.left!=null||head.right!=null))||(head.left==null&&head.right==null)){
                return true;
            }
            if(head.left!=null){
                queue.offer(head.left);
            }
            if(head.right!=null){
                queue.offer(head.right);
            }else {
                leaf=true;
            }
        }
        return true;
    }
}

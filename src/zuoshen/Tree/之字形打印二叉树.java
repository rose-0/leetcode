package zuoshen.Tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class 之字形打印二叉树 {
    public void print_zig(treeNode head){
        if(head==null){
            return;
        }
        Deque<treeNode>queue=new LinkedList<>();
        treeNode last=head;
        treeNode nlast=null;
        queue.offerFirst(head);
        int level=1;
        boolean flag=true;
        while (!queue.isEmpty()){
            if(flag){
                head=queue.peekFirst();
                if(head.left!=null){
                    queue.offerLast(head.left);
                    nlast=nlast==null?head.left:nlast;
                }
                if(head.right!=null){
                    queue.offerLast(head.right);
                    nlast=nlast==null?head.right:nlast;
                }
            }else {
                head=queue.peekFirst();
                if(head.right!=null){
                    queue.offerFirst(head.right);
                    nlast=nlast==null?head.right:nlast;
                }
                if(head.left!=null){
                    queue.offerFirst(head.left);
                    nlast=nlast==null?head.left:nlast;
                }
            }
            System.out.println(head.value);
            if(head==last&!queue.isEmpty()){
                flag=!flag;
                last=nlast;
                nlast=null;
                System.out.println(level++);
            }
        }

    }
}

package zuoshen.Tree;

import java.util.LinkedList;
import java.util.Queue;

public class 二叉树按层打印 {
    public void print_level(treeNode head){
        if(head==null){
            return;
        }
        Queue<treeNode>queue=new LinkedList<>();
        queue.offer(head);
        int level=1;
        System.out.print("level:"+(level++)+" : ");
        treeNode last=head;
        treeNode nlast=null;
        while (!queue.isEmpty()){
            head=queue.poll();
            System.out.print(head.value);
            if(head.left!=null){
                queue.offer(head.left);
                nlast=head.left;
            }
            if(head.right!=null){
                queue.offer(head.right);
                nlast=head.right;
            }
            if(head==last&&!queue.isEmpty()){
                System.out.print("\nlevel:"+(level++)+" : ");
                last=nlast;
            }
        }
    }
}

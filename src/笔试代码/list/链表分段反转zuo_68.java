package 笔试代码.list;

import java.util.ArrayList;
import java.util.List;

public class 链表分段反转zuo_68 {
    public static ListNode reverseKGroup(ListNode head, int k) {
        if(k<2){
            return null;
        }
        int count=1;
        ListNode cur=head;
        ListNode next=null;
        ListNode start=null;
        ListNode pre=null;
        while (cur!=null){
            next=cur.next;
            if(count==k){
                start=pre==null?head:pre.next;
                head=pre==null?cur:head;
                resign(start,cur,pre,next);
                pre=start;
                count=0;
            }
            count++;
            cur=next;
        }
        return head;
    }
    public static void resign(ListNode start,ListNode end,ListNode left,ListNode right){
        ListNode pre=null;
        ListNode cur=start;
        ListNode next=null;
        while (cur!=right){
            next=cur.next;
            cur.next=pre;
            pre=cur;
            cur=next;
        }
        if(left!=null) {
            left.next = end;
        }
        start.next=right;
    }

    public static void main(String[] args) {
        ListNode l=new ListNode(1);
        l.next=null;
        ListNode r=new ListNode(2);
        l.next=r;
        l.next.next=new ListNode(3);
        l.next.next.next=new ListNode(4);
        l.next.next.next.next=new ListNode(5);
//        while (l!=null){
//            System.out.println(l.val);
//            l=l.next;
//        }
        ListNode head=reverseKGroup(l,2);
        System.out.println(head.val);
        while (head!=null){
            System.out.println(head.val);
            head=head.next;
        }

    }
}

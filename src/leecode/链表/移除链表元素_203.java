package leecode.链表;

import java.util.Stack;
import java.util.WeakHashMap;

public class 移除链表元素_203 {
    public ListNode removeElements(ListNode head, int val) {
        if(head==null){
            return head;
        }
        Stack<ListNode>stack=new Stack<>();
        while (head!=null){
            if(head.val!=val) {
                stack.push(head);
            }
            head=head.next;
        }
        while (!stack.empty()){
            stack.pop().next=head;
            head=stack.pop();
        }
        return head;
    }
    public ListNode removeElements1(ListNode head, int val){
        ListNode newhead;
        while (head!=null){
            if(head.val!=val){
                break;
            }
            head=head.next;
        }
        newhead=head;
        ListNode cur=head;
        while (cur!=null){
            if(cur.val==val){
                newhead.next=cur.next;
            }else {
                newhead=cur;
            }
            cur=cur.next;
        }
        return head;
    }
}

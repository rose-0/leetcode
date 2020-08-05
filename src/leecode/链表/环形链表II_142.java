package leecode.链表;

import zuoshen.输入输出练习.H;

import java.util.HashMap;

public class 环形链表II_142 {
    public ListNode detectCycle(ListNode head) {
        ListNode slow=head;
        ListNode fast=head;
        while (fast!=null&&fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
            if(fast==slow){
                break;
            }
        }
        HashMap<Integer,Character>map=new HashMap<>();
        if(fast==null||fast.next==null){
            return null;
        }
        slow=head;
        while (fast!=slow){
            slow=slow.next;
            fast=fast.next;
        }
        return slow;
    }
}

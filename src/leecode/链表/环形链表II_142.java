package leecode.链表;

import zuoshen.输入输出练习.H;

import java.util.HashMap;

public class 环形链表II_142 {
    public ListNode detectCycle(ListNode head) {
        ListNode slow,fast;
        slow=fast=head;
        while(fast!=null&&fast.next!=null){//有环的话，是挑不出循环的
            fast=fast.next.next;
            slow=slow.next;
            if(fast==slow){ //有环的话，一定是会相交的！
                slow=head;
                while(fast!=slow){
                    fast=fast.next;
                    slow=slow.next;
                }
                return fast;
            }
        }
        return null;
    }
}

package leecode.链表;

public class 相交链表_160 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode endA=null;
        ListNode endB=null;
        int lenA=0;
        int lenB=0;
        ListNode cur1=headA;//不能直接移动head！！！，后面还需要使用head赋值
        ListNode cur2=headB;
        while (cur1!=null){
            endA=cur1;
            lenA++;
            cur1=cur1.next;
        }
        while (cur2!=null){
            endB=cur2;
            lenB++;
            cur2=cur2.next;
        }
        if(endA!=endB){
            return null;
        }

        cur1=lenA>lenB?headA:headB;
        cur2=cur1==headA?headB:headA;
        int diff=Math.abs(lenA-lenB);
        while (diff>0){
            cur1=cur1.next;
            diff--;
        }
        while (cur1!=cur2){
            cur1=cur1.next;
            cur2=cur2.next;
        }
        return cur1;
    }
}

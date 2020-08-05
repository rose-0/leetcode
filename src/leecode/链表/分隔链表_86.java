package leecode.链表;

public class 分隔链表_86 {
    public static ListNode partition(ListNode head, int x) {
        ListNode dummy1=new ListNode(0);
        ListNode dummy2=new ListNode(0);
        ListNode head1=dummy1;
        ListNode head2=dummy2;
        ListNode cur=head;
        while (cur!=null){
            if(cur.val<x){
                head1.next=cur;
                head1=head1.next;
            }else {
                head2.next=cur;
                head2=head2.next;
            }
            cur=cur.next;
        }
        head1.next=null;
        head2.next=null;
        head1.next=dummy2.next;
        return dummy1.next;
    }

    public static void main(String[] args) {
        ListNode head=new ListNode(1);
        head.next=new ListNode(4);
        head.next.next=new ListNode(3);
        head.next.next.next=new ListNode(2);
        head.next.next.next.next=new ListNode(5);
        head.next.next.next.next.next=new ListNode(2);
        ListNode newhead=partition(head,3);
        while (newhead!=null){
            System.out.print(newhead.val+" ");
            newhead=newhead.next;
        }
    }
}

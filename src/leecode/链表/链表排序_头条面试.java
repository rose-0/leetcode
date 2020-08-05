package leecode.链表;
//奇数位升序，偶数位降序，链表排序
//拆分奇偶，偶反转，合并链表
public class 链表排序_头条面试 {
    public static ListNode getLists(ListNode head){
        ListNode odd=head;
        ListNode even=head.next;
        ListNode reverseHead=head.next;
        while (even!=null&&even.next!=null){
            odd.next=even.next;
            odd=odd.next;
            even.next=odd.next;
            even=even.next;
        }
        System.out.println("head odd-value-");
        printValue(head);
        System.out.println();
        printValue(reverseHead);

        odd.next=null;
        ListNode evenHead=reverse_list(reverseHead);
        System.out.println();
        printValue(evenHead);
        return mergeList(head,evenHead);
    }
    public static ListNode reverse_list(ListNode head){
        if(head==null){
            return null;
        }
        ListNode pre=null;
        ListNode next=null;
        while (head!=null){
            next=head.next;
            head.next=pre;
            pre=head;
            head=next;
        }
        return pre;
    }
    public static ListNode mergeList(ListNode head1,ListNode head2){
        ListNode dummy=new ListNode(-1);
        ListNode cur=dummy;
        while (head1!=null&&head2!=null){
            if(head1.val<head2.val){
                cur.next=head1;
                head1=head1.next;
            }else {
                cur.next=head2;
                head2=head2.next;
            }
            cur=cur.next;
        }
        if(head1!=null){
            cur.next=head1;
        }
        if(head2!=null){
            cur.next=head2;
        }
        return dummy.next;
    }

    public static void printValue(ListNode head){
        while (head!=null){
//            System.out.print(head.val+"->");
            head=head.next;
        }
    }
    public static void main(String[] args) {
        ListNode head=new ListNode(1);
        head.next=new ListNode(4);
        head.next.next=new ListNode(3);
        head.next.next.next=new ListNode(2);
        head.next.next.next.next=new ListNode(5);
        ListNode newhead=getLists(head);
        System.out.println();
        while (newhead!=null){
            System.out.print(newhead.val+"->");
            newhead=newhead.next;
        }
    }
}

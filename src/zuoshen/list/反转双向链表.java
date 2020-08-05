package zuoshen.list;

public class 反转双向链表 {
    public DoubleNode reverse_dNode(DoubleNode head){
        DoubleNode pre=null;
        DoubleNode next=null;
        while (head!=null){
            next=head.next;
            head.next=pre;
            head.last=next;
            pre=head;
            head=next;
        }
        return pre;
    }
}

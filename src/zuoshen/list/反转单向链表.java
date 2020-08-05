package zuoshen.list;

public class 反转单向链表 {
    public Node reverse_list(Node head){
        Node pre=null;
        Node next=null;
        while (head!=null){
            next=head.next;
            head.next=pre;
            pre=head;
            head=next;
        }
        return pre;
    }

    //递归实现 见labuladong
    public Node reverse_list_digui(Node head){
        if(head.next==null){
            return head;
        }
        Node reverseHead = reverse_list_digui(head.next);
//        reverseHead.next=head;//这里不对，reverseHead是翻转后链表的头节点，不是head之后的节点
        head.next.next=head;//head之后的节点通过head.next.next寻找，并让其指向head
        head.next=null;
        return reverseHead;
    }
}

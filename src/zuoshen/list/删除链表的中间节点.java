package zuoshen.list;

public class 删除链表的中间节点 {
    public void delete_mid_node(Node head){
        if(head.next==null){
            return;
        }
        if(head.next.next==null){
            head=head.next;
            return;
        }
        Node cur=head;
        Node last=head.next.next;
        while (last.next!=null&&last.next.next!=null){
            cur=cur.next;
            last=last.next.next;
        }
        cur.next=cur.next.next;
    }
}

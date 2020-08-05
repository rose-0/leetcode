package zuoshen.list;

public class 在双链表中删除倒数第k个节点 {
    public void delete_last_node(DoubleNode head,int k){
        DoubleNode cur=head;
        while (cur!=null){
            k--;
            cur=cur.next;
        }
        if(k==0){
            head=head.next;
            return;
        }
        cur=head;
        if(k<0) {
            while (++k != 0) {
                cur = cur.next;
            }
            if(cur.next.next!=null) {
                cur.next = cur.next.next;
                cur.next.last = cur;
            }else {
                cur.next=null;
            }
        }
    }
}

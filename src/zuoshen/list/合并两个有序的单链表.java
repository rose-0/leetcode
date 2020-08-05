package zuoshen.list;

public class 合并两个有序的单链表 {

    public Node merge(Node head1, Node head2){
        if(head1==null){
            return head2;
        }
        if(head2==null){
            return head1;
        }
        Node res=null;
        if(head1.value>head2.value){
            res=head2;
            res.next=merge(head1,head2.next);
        }else {
            res=head1;
            res.next=merge(head1.next,head2);
        }
        return res;
    }
    public Node merge_no(Node head1,Node head2){
        if(head1==null){
            return head2;
        }
        if(head2==null){
            return head1;
        }
        Node mergehead=null;
        Node cur = null;
        while (head1!=null&&head2!=null){
            if(head1.value<head2.value){
                if(mergehead==null){
                    mergehead= cur =head1;
                }else {
                    cur.next=head1;
                    cur = cur.next;
                }
                head1=head1.next;
            }else if(head1.value>head2.value){
                if(mergehead==null){
                    mergehead= cur =head2;
                }else {
                    cur.next=head2;
                    cur = cur.next;
                }
                head2=head2.next;
            }
        }
        if(head1==null){
            cur.next=head2;
        }
        if(head2==null){
            cur.next=head1;
        }
        return mergehead;
    }
}

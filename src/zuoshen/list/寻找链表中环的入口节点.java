package zuoshen.list;

public class 寻找链表中环的入口节点 {
    public Node search_node(Node head){
        Node fast=head;
        Node slow=head;
        while (fast!=slow){
            if(fast.next==null||fast.next.next==null){
                return null;
            }
            fast=fast.next.next;
            slow=slow.next;
        }
        fast=head;
        while (fast!=slow){
            fast=fast.next;
            slow=slow.next;
        }
        return slow;
    }
    public Node noLoop(Node head1,Node head2){
        Node cur1=head1;
        Node cur2=head2;
        int n=0;
        while (cur1!=null){
            n++;
            cur1=cur1.next;
        }
        while (cur2!=null){
            n--;
            cur2=cur2.next;
        }
        if(cur1!=cur2){
            return null;
        }
        cur1=n>0?head1:head2;
        cur2=cur1==head1?head2:head1;
        n=Math.abs(n);
        while (n!=0){
            n--;
            cur1=cur1.next;
        }
        while (cur1!=cur2){
            cur1=cur1.next;
            cur2=cur2.next;
        }
        return cur1;
    }
    public Node Loop_Node(Node head1,Node loop1,Node head2,Node loop2){
        Node cur1=null;
        Node cur2=null;
        if(loop1==loop2){
            cur1=head1;
            cur2=head2;
            int n=0;
            while (cur1!=null){
                n++;
                cur1=cur1.next;
            }
            while (cur2!=null){
                n--;
                cur2=cur2.next;
            }
            cur1=n>0?head1:head2;
            cur2=cur1==head1?head2:head1;
            n=Math.abs(n);
            while (n!=0){
                cur1=cur1.next;
                n--;
            }
            while (cur1!=cur2){
                cur1=cur1.next;
                cur2=cur2.next;
            }
            return cur1;
        }else {
            cur1=loop1.next;
            while (cur1!=loop1){
                if(cur1==loop2){
                    return loop1;
                }
                cur1=cur1.next;
            }
            return null;
        }
    }
}

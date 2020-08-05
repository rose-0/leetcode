package zuoshen.list;

public class 反转部分单向链表 {
    public Node reverse_part(Node head,int from,int to){
        Node before=head;
        Node after=head;
        while (--from!=1){
            before=before.next;

        }
        while (to--!=0){
            after=after.next;

        }

        Node node1=before==null?head:before.next;
        Node node2=node1.next;
        node1.next=after;
        Node next=null;
        while (node2!=after){
            next=node2.next;
            node2.next=node1;
            node1=node2;
            node2=next;
        }
        if(before!=null){
            before.next=node1;
            return head;
        }
        return node1;
//        return head;
    }

    //反转链表前N个节点 labuladong 递归实现
    Node successor=null;//记录第n+1个节点
    public Node reverse_N(Node head,int n){
        if(n==1){
            //记录n+1个节点，反转后要连上
            successor=head.next;
            return head;
        }
        Node last=reverse_N(head.next,n-1);//以head.next为起点，反转n-1个节点，类似反转单向链表。反转head.next
        //last是反转后链表的头节点，反转后链表的尾节点是通过head.next找到，尾节点的next是空，要把它指向head
        head.next.next=head;
        //
        head.next=successor;
        return last;
    }
    //反转m,n区间的链表 反转链表的一部分 见labuladong
    public Node reverseBetween(Node head,int m,int n){
        if(m==1){//题目说的m是从1开始的
            return reverse_N(head,n);//记得return
        }
        head.next=reverseBetween(head.next,m-1,n-1);
        return head;
    }

}

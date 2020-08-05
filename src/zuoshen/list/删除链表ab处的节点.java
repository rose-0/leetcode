package zuoshen.list;

public class 删除链表ab处的节点 {
    public void delete_ab_node(Node head,int a,int b){
        int n=0;
        Node cur=head;
        while (cur!=null){
            n++;
            cur=cur.next;
        }
        n=(int)Math.ceil(((double)(a*n))/(double)b);
        if(n==1){
            head=head.next;
            return;
        }
        System.out.println(n);
        if(n>1){
            cur=head;
            while (--n!=1){
                cur=cur.next;
//                n--;
                System.out.println(n);
            }
            cur.next=cur.next.next;
        }
    }
}

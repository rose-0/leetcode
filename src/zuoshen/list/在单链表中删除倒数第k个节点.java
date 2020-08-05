package zuoshen.list;

public class 在单链表中删除倒数第k个节点 {
    public void print_last_k(Node head,int k){
        if(head==null||k<1){
            return;
        }
        Node slow=head;
        Node fast=head;
        for (int i = 1; i <k ; i++) {
            fast=fast.next;
            if(fast==null){
                System.out.println("重新输入k");
                return;
            }
        }
        while (fast.next!=null){
            slow=slow.next;
            fast=fast.next;
        }
        System.out.println(slow.value);
    }
    public void print_last_kNode(Node head,int k){
        if(head==null||k<1){
            return;
        }
        Node h=head;
        while (h!=null){
            h=h.next;
            k--;
//            System.out.print(k+" ");
        }
        if(k==0){
            head=head.next;
        }
        h=head;
        while (++k<0){
            h=h.next;
//            k++;
//            System.out.print(k+" "+h.value);
        }
//        System.out.println(h.value);
        h.next=h.next.next;
    }
}

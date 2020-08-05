package zuoshen.list;

import java.util.Stack;

public class 两个单链表生成相加链表 {
    public Node add_list(Node head1,Node head2){
        Stack<Integer>stack1=new Stack<>();
        Stack<Integer>stack2=new Stack<>();
        while (head1!=null){
            stack1.push(head1.value);
            head1=head1.next;
        }
        while (head2!=null){
            stack2.push(head2.value);
            head2=head2.next;
        }
        int n1=0;
        int n2=0;
        int n=0;
        int ca=0;
        Node node=null;
        Node pre=null;
        while (!stack1.empty()||!stack2.empty()){
            n1=stack1.empty()?0:stack1.pop();
            n2=stack2.empty()?0:stack2.pop();
            n=n1+n2+ca;
            pre=node;
            node=new Node(n%10);
            node.next=pre;
            ca=n/10;
        }
        if(ca==1){
            pre=node;
            node=new Node(1);
            node.next=pre;
        }
        return node;
    }
    public Node add_list_reveerse(Node head1,Node head2){
        reverse_list(head1);
        reverse_list(head2);
        int ca=0;
        int n1=0;
        int n2=0;
        int n=0;
        Node c1=head1;
        Node c2=head2;
        Node node=null;
        Node pre=null;
        while (c1!=null||c2!=null){
            n1=c1==null?0:c1.value;
            n1=c2==null?0:c2.value;
            n=n1+n2+ca;
            pre=node;
            node=new Node(n%10);
            node.next=pre;
            ca=n/10;
            c1=c1==null?null:c1.next;
            c2=c2==null?null:c2.next;
        }
        if(ca==1){
            pre=node;
            node=new Node(1);
            node.next=pre;
        }
        reverse_list(head1);
        reverse_list(head2);
        return node;
    }
    public void reverse_list(Node head){
        Node pre=null;
        Node next=null;
        while (head!=null){
            next=head.next;
            head.next=pre;
            pre=head;
            head=next;
        }
    }
}

package zuoshen.list;

public class Test {
    public static void print_list(Node head){
        while (head!=null){
            System.out.print(head.value+" ");
            head=head.next;
        }
    }
    public static void print_Doublelist(DoubleNode head){
        while (head!=null){
            System.out.print(head.value+" ");
            head=head.next;
        }
    }
    public static void main(String[] args) {
        Node head1=new Node(1);
        head1.next=new Node(3);
        head1.next.next=new Node(2);
        head1.next.next.next=new Node(8);
        head1.next.next.next.next=new Node(5);
        head1.next.next.next.next.next=new Node(11);
        head1.next.next.next.next.next.next=new Node(13);
        print_list(new 单链表的选择排序().sort(head1));
        Node head2=new Node(5);

        head2.next=new Node(6);
        head2.next.next=new Node(8);
        head2.next.next.next=new Node(6);
        head2.next.next.next.next=new Node(4);
//        print_list(new 反转部分单向链表().reverse_part(head2,2,4));
//        System.out.println(new 判断一个链表是否为回文结构().stack_method_two(head2));

        DoubleNode head=new DoubleNode(1);
        head.next=new DoubleNode(3);
        head.next.last=head;
        head.next.next=new DoubleNode(4);
        head.next.next.last=head.next;
        head.next.next.next=new DoubleNode(8);
        head.next.next.next.last=head.next.next;
        head.next.next.next.next=new DoubleNode(10);
        head.next.next.next.next.last=head.next.next.next;
//        print_Doublelist(new 反转双向链表().reverse_dNode(head));
    }
}

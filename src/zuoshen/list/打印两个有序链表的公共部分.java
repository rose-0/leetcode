package zuoshen.list;

public class 打印两个有序链表的公共部分 {
    public void print(Node head1,Node head2){
        System.out.println("common part");
        while (head1!=null&&head2!=null){
            if(head1.value<head2.value){
                head1=head1.next;
            }else if(head1.value>head2.value){
                head2=head2.next;
            }else {
                System.out.println(head1.value+" ");
                head1=head1.next;
                head2=head2.next;

            }
        }
    }

}

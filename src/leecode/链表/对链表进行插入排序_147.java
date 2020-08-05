package leecode.链表;
//链表的题目一定要边画图边写代码，找到有序的尾部，未排序的头结点，在有序链表
//找到插入的前驱，返回的结果有两种，null则插入头部，否则插入pre后面，
//把节点插到前面，也要把后面的链表连上，记录next节点，插入时候，一定要先连后断开
public class 对链表进行插入排序_147 {
    public static ListNode insertionSortList(ListNode head) {

        ListNode cur=head;//cur为排好序链表的尾部
        ListNode unsort=null;
        ListNode next=null;
        while (cur.next!=null){
            unsort=cur.next;
            next=unsort.next;
            if(unsort.val<cur.val){
                ListNode pre=findpre(head,cur,unsort);
                if(pre==null){
                    unsort.next=head;
                    head=unsort;
                }else {
                    unsort.next=pre.next;
                    pre.next=unsort;
                }
                cur.next=next;
            }else {
                unsort=unsort.next;
                cur=cur.next;
            }
        }
        return head;
    }
    public static ListNode findpre(ListNode head,ListNode end,ListNode node){
        ListNode pre=null;
        ListNode cur=head;
        while (cur!=end){//进入这个方法的时候end节点的值肯定大于node的值
            if(cur.val<node.val&&cur.next.val>=node.val){//这里cur.next
                pre=cur;
                break;
            }
            cur=cur.next;
        }
        return pre;
    }
    public static void print_list(ListNode head){
        while (head!=null){
            System.out.print(head.val+" ");
            head=head.next;
        }
    }
    public static void main(String[] args) {

        ListNode head1 = new ListNode(4);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(1);
        head1.next.next.next = new ListNode(3);
//        head1.next.next.next.next = new ListNode(5);
//        head1.next.next.next.next.next = new ListNode(11);
//        head1.next.next.next.next.next.next = new ListNode(13);

        print_list(insertionSortList(head1));
    }

}

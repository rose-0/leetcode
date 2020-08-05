package leecode.链表;

public class 两两交换链表中的节点_24 {
    public ListNode swapPairs(ListNode head) {
        int length=0;
        ListNode cur=head;
        while (cur!=null){
            length++;
            cur=cur.next;
        }
        cur=head;
        ListNode[]arr=new ListNode[length];
        for (int i = 0; i <arr.length ; i++) {
            arr[i]=cur;
            cur=cur.next;
        }
        int n=0;
        n=length%2==0?length:length-1;
        for (int i = 1; i <n ; i=i+2) {
            ListNode temp=arr[i];
            arr[i]=arr[i-1];
            arr[i-1]=temp;
        }

        for (int i = 1; i <length ; i++) {
            arr[i-1].next=arr[i];//这儿链表转化为数组直接数组之间相连，不需要重造链表连数组
        }
        arr[length-1].next=null;
        return arr[0];
    }
    //对本层进行处理时，本层的节点就是传进来的参数值，直接对传进来的参数进行处理即可，
    public ListNode swapPairs_2(ListNode head) {
        if(head==null||head.next==null){
            return head;
        }
//要保存返回给上一级的节点
//        ListNode nextnode=head.next.next;
//        head.next.next=head;
//        head.next=swapPairs(nextnode);
//        return head.next;

        ListNode nextnode=head.next;
//        head.next.next=head;
        head.next=swapPairs_2(nextnode.next);
        nextnode.next=swapPairs_2(nextnode);
        return head.next;
    }

}

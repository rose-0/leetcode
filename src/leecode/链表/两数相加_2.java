package leecode.链表;

public class 两数相加_2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //仿照 415
        int carry=0;
        ListNode dummy=new ListNode(-1);
        ListNode cur=dummy;//要用cur
        while (l1!=null||l2!=null){
            int val1=l1!=null?l1.val:0;
            int val2=l2!=null?l2.val:0;
            int temp=val1+val2+carry;
            carry=temp/10;
            ListNode sum=new ListNode(temp%10);
            cur.next=sum;
            cur=cur.next;
            if(l1!=null)l1=l1.next;//
            if(l2!=null)l2=l2.next;
        }
        if(carry==1){
            cur.next=new ListNode(1);
        }
        return dummy.next;
    }
}

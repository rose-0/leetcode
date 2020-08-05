package leecode.链表;


import java.util.Stack;

public class 回文链表_234 {
    public static boolean isPalindrome(ListNode head) {
        ListNode slow=head;
        ListNode fast=head;
        /*
        1->2->3       fast走到3退出  slow走到2(slow还需要走一步)
        1->2->3->4    fast走到4后面退出 slow走到3
         */
        Stack<ListNode>stack=new Stack<>();
        //移动快慢指针时候，可以直接入栈。
        while (fast!=null&&fast.next!=null){
            stack.push(slow);
            fast=fast.next.next;
            slow=slow.next;
        }
        if(fast!=null){//奇节点，slow再走一步
            slow=slow.next;
        }
        while (!stack.isEmpty()){
            ListNode cur=stack.pop();
            if(cur.val!=slow.val){
                return false;
            }
            slow=slow.next;
        }
        return true;
    }

    //快慢指针遍历的同时，慢指针移动的同时翻转链表
    public static boolean isPalindrome1(ListNode head) {
        ListNode fast=head;
        ListNode slow=head;
        ListNode p=null;
        ListNode pre=null;
        while (fast!=null&&fast.next!=null){
            //对比翻转链表： (pre cur next) (pre p slow) 最后链表的头节点是p
            p=slow;
            slow=slow.next;
            fast=fast.next.next;

            p.next=pre;
            pre=p;
        }
        if(fast!=null){
            slow=slow.next;
        }
        while (p!=null){
            if(p.val!=slow.val){
                return false;
            }
            p=p.next;
            slow=slow.next;
        }
        return true;
    }

    public static void main(String[] args) {
        ListNode head1=new ListNode(1);
        head1.next=new ListNode(2);
        head1.next.next=new ListNode(3);
        isPalindrome(head1);
        ListNode head2=new ListNode(1);
        head2.next=new ListNode(2);
        head2.next.next=new ListNode(3);
        head2.next.next.next=new ListNode(4);
        isPalindrome(head2);
    }
}

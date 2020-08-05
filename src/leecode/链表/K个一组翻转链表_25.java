package leecode.链表;

import java.util.List;

public class K个一组翻转链表_25 {
    //参考翻转单向链表和翻转部分单向链表递归写法 labuladong公众号
    public ListNode reverseKGoup(ListNode head,int k){
        if(head==null){
            return null;//这个是返回空
        }
        ListNode a=head;//使用a，b！！不直接使用head
        ListNode b=head;
        for (int i = 0; i <k ; i++) {
            //不够k个，不用翻转
            if(b==null) return head;  //return head!!!
            b=b.next;
        }
        //此时b是k+1个元素，反转[a，b）
        //翻转前k个元素
        //传入的是a
        ListNode newHead=reverse(a,b);//reverse(a,b)这个应该是反转前k的节点的非递归形式。先找到第k+1个节点b
        //递归翻转b后面的链表和a连起来
        a.next=reverseKGoup(b,k);//是a.next 不是 newhead.next，传入的是b，不是b.next
        return newHead;
    }
    public ListNode reverse(ListNode a,ListNode b){
        //翻转a开头的单链表，就是翻转a到null之间的节点，现在翻转a到b之间（[a,b)）的节点，就是把null改为b
        ListNode pre=null;//pre为空 不是b
        ListNode next=null;
        ListNode cur=a;//这个使用cur，不要直接使用a，会改变a的指针
        while (cur!=b){
            next=cur.next;
            cur.next=pre;
            pre=cur;
            cur=next;
        }
        return pre;
    }

    //迭代解法
    //https://leetcode-cn.com/problems/reverse-nodes-in-k-group/solution/tu-jie-kge-yi-zu-fan-zhuan-lian-biao-by-user7208t/
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head==null||head.next==null){
            return head;
        }
        ListNode dummy=new ListNode(0);
        //假节点的next指向head。
        // dummy->1->2->3->4->5
        dummy.next=head;
        //一共四个指针 pre指每次要翻转的链表的头结点的上一个节点。
        // end指每次要翻转的链表的尾节点
        // next反转链表后继，便于连接
        // start反转链表的头节点
        ListNode pre=dummy;
        ListNode end=dummy;
        while (end.next!=null){
            for (int i = 0; i <k ; i++) {
                if(end==null){//如果end==null，即需要翻转的链表的节点数小于k，不执行翻转。
                    break;
                }
                end=end.next;
            }
            if(end==null){//这儿也要判断！！
                break;
            }
            //先记录下end.next,（3）方便后面链接链表
            ListNode next=end.next;
            //然后断开链表
            end.next=null;//为空后，后面反转start才能变成只传一个参数

            //记录下要翻转链表的头节点
            ListNode start=pre.next;
            //翻转链表,pre.next指向翻转后的链表。1->2 变成2->1。 dummy->2->1
            pre.next=reverse_list(start);
            //翻转后头节点变到最后。通过.next把断开的链表重新链接。dummy->2->1  3->4->....
            //连接 1和3
            start.next=next;//start反转时候移动到了尾部


            //将pre换成下次要翻转的链表的头结点的上一个节点。即start
            pre=start;
            //翻转结束，将end置为下次要翻转的链表的头结点的上一个节点。即start
            end=start;
        }
        return dummy.next;
    }
    public ListNode reverse_list(ListNode head){//直接改变start！！不要用cur
        ListNode pre=null;
        ListNode next=null;
        while (head!=null){
            next=head.next;
            head.next=pre;
            pre=head;
            head=next;
        }
        return pre;
    }
}

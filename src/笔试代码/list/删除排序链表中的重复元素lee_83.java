package 笔试代码.list;

import 笔试代码.list.ListNode;

import java.util.List;

public class 删除排序链表中的重复元素lee_83 {
    public void deleteDupNum(int[] num) {
        int slow = 0;
        int fast = 1;
        while (fast < num.length) {
            if (num[fast] != num[slow]) {
                num[++slow] = num[fast];
            }
            fast++;
        }
    }

    public ListNode deleteDuplicates2(ListNode head){
        ListNode slow=head;
        ListNode fast=head.next;
        while (fast!=null){
            if(fast.val!=slow.val){//不相等 才后移
                slow.next=fast;
                slow=slow.next;
            }
            fast=fast.next;
        }
        slow.next=null;//注意这个
        return head;
    }






    public ListNode deleteDuplicates(ListNode head) {
        if(head==null){
            return head;
        }
        ListNode cur=head.next;
        ListNode pre=head;
        while (cur!=null){
            if(cur.val==pre.val){
                pre.next=cur.next;
                cur=cur.next;
            }else {
                pre=pre.next;
                cur=cur.next;
            }
        }
        return head;
    }
}

package 笔试代码.list;

import 笔试代码.list.ListNode;

public class 合并两个有序链表lee_21 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1==null){
            return l2;
        }
        if(l2==null){
            return l1;
        }
        ListNode head=l1.val<l2.val?l1:l2;
        if(head==l1){
            head.next=mergeTwoLists(l1.next,l2);
        }else {
            head.next=mergeTwoLists(l1,l2.next);
        }
        return head;
    }
//    public ListNode mergeTwoLists2(ListNode l1, ListNode l2){
//        if(l1==null){
//            return l2;
//        }
//        if(l2==null){
//            return l1;
//        }
//        ListNode head=null;
//        ListNode cur=null;
//        while (l1!=null&&l2!=null){
//            if(l1.val<l2.val){
//                if(head==null){
//                    head=cur=l1;
//                }else {
//                    cur.next=l1;
//                    cur=cur.next;
//                }
//                l1=l1.next;
//            }
//        }
//    }
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode dummy=new ListNode(0);
        ListNode cur=dummy;
        while (l1!=null&&l2!=null){
            if(l1.val<l2.val){
                cur.next=l1;
                l1=l1.next;
            }else {
                cur.next=l2;
                l2=l2.next;
            }
            cur=cur.next;
        }
        if(l1==null){
            cur.next=l2;
        }
        if(l2==null){
            cur.next=l1;
        }
        return dummy.next;
    }
}

package 笔试代码.list;

import 笔试代码.list.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;
/*
链表问题可以设置一个虚拟节点，方便对链表进行处理，包括两个有序链表的合并
 */
//https://www.nowcoder.com/discuss/245882?type=post&order=time&pos=&page=1&subType=2
//看lee上liweiwei的两种方法
//https://leetcode-cn.com/problems/merge-k-sorted-lists/solution/tan-xin-suan-fa-you-xian-dui-lie-fen-zhi-fa-python/
public class 合并k个排序链表lee_23 {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length==0){
            return null;
        }
        if(lists.length<2){
            return lists[0];
        }
        ListNode l=merge(lists[0],lists[1]);
        ListNode head=l;
        for (int i = 2; i <lists.length ; i++) {
            head=merge(l,lists[i]);
            l=head;
        }
        return head;
    }
    public ListNode mergeKLists1(ListNode[] lists) {
        return mergeKLists2(lists,0,lists.length-1);
    }
    public ListNode mergeKLists2(ListNode[] lists,int l,int r) {
        if(l==r){
            return lists[l];
        }
        int mid=(l+r)/2;
        ListNode left=mergeKLists2(lists,l,mid);
        ListNode right=mergeKLists2(lists,mid+1,r);
        return merge(left,right);
    }
    public ListNode merge(ListNode l1,ListNode l2){
        if(l1==null){
            return l2;
        }
        if(l2==null){
            return l1;
        }
        ListNode head=null;
        if(l1.val<l2.val){
            head=l1;
            head.next=merge(l1.next,l2);
        }else {
            head=l2;
            head.next=merge(l1,l2.next);
        }
        return head;
    }
    //合并两个链表要对第一个节点做特殊的处理
    public ListNode merge2(ListNode l1,ListNode l2){
        if(l1==null){
            return l2;
        }
        if(l2==null){
            return l1;
        }
        ListNode head=null;
        ListNode cur=null;
        while (l1!=null&&l2!=null){
            if(l1.val<l2.val){
                if(head==null){
                    head=cur=l1;
                }else {
                    cur.next = l1;
//                    l1= l1.next;
                    cur=cur.next;//注意这个句子和l1=l1.next;的位置关系
                }
                l1=l1.next;
            }else {
                if(head==null){
                    head=cur=l2;
                }else {
                    cur.next = l2;
//                    l1= l1.next;
                    cur=cur.next;
                }
                l1=l1.next;
            }
        }
        if(l1==null){
            cur.next=l2;
        }
        if(l2==null){
            cur.next=l1;
        }
        return head;
    }
    public ListNode mergeKList_priorityqueue(ListNode[] lists) {
        int len=lists.length;
        if(len==0)return null;
        PriorityQueue<ListNode> queue=new PriorityQueue(len, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val-o2.val;//默认最小，最大堆是后面减去前面
            }
        });
        for (ListNode listNode:lists) {//合并k个有序链表就要申请优先级队列，把k个节点都放进去
            if(listNode!=null){//要判断不为空！！
                queue.add(listNode);
            }
        }
        ListNode dummynode=new ListNode(-1);
        ListNode curnode=dummynode;
        while (!queue.isEmpty()){
            ListNode node=queue.poll();
            curnode.next=node;
            curnode=curnode.next;
            if(curnode.next!=null){//链表后面还有节点，再放进去
                queue.add(curnode.next);
            }
        }
        return dummynode.next;
    }

}

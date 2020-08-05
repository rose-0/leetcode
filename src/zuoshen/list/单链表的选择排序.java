package zuoshen.list;

import zuoshen.输入输出练习.H;

public class 单链表的选择排序 {
    public Node findminpre(Node head){
        Node pre=null;
        Node cur=head;
        Node small=cur;
        Node preSmall=pre;
        while (cur!=null){
            if(cur.value<small.value){
                small=cur;
                preSmall=pre;
            }
            pre=cur;
            cur=cur.next;
        }
        return preSmall;
    }
    public Node sort(Node head){
        Node unhead=head;//未排序链表的头结点
        Node tail=null;//排序链表的尾节点
        Node smallpre=null;
        Node small=null;
        while (unhead!=null){
            small=unhead;
            smallpre=findminpre(unhead);
            if(smallpre!=null){
                small=smallpre.next;
                smallpre.next=small.next;
            }
            unhead=unhead==small?unhead.next:unhead;
            if(tail==null){
                head=small;
            }else {
                tail.next=small;
            }
            tail=small;
        }
        return head;
    }
}

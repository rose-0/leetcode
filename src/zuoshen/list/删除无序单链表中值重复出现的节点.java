package zuoshen.list;

import java.util.HashSet;

public class 删除无序单链表中值重复出现的节点 {
    public void delete_map(Node head){
        if(head==null){
            return;
        }
        HashSet<Integer>hashSet=new HashSet<>();
        Node pre=head;
        Node cur=head.next;
        hashSet.add(pre.value);
        while (cur!=null){
            if(hashSet.contains(cur.value)){
                pre.next=cur.next;
            }else {
                hashSet.add(cur.value);
                pre=cur;
            }
            cur=cur.next;
        }
    }
    public void remove_list_node(Node head){
        Node cur=head;
        Node pre=null;
        Node next=null;
        while (cur!=null){
            pre=cur;
            next=cur.next;
            while (next!=null){
                if(cur.value==next.value){
                    pre.next=next.next;
                }else {
                    pre=next;
                }
                next=next.next;
            }
            cur=cur.next;
        }
    }
}

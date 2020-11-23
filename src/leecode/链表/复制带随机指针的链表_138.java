package leecode.链表;

import java.util.HashMap;
import java.util.Map;

public class 复制带随机指针的链表_138 {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }

    //https://leetcode-cn.com/problems/copy-list-with-random-pointer/solution/liang-chong-shi-xian-tu-jie-138-fu-zhi-dai-sui-ji-/
    //图解
    public Node copyRandomList2(Node head) {
        Node cur=head;
        while (cur!=null){
            Node curCopy=new Node(cur.val);
            curCopy.next=cur.next;
            cur.next=curCopy;
            cur=curCopy.next;
        }
        cur=head;
        while (cur!=null){
            if(cur.random!=null){
                cur.next.random=cur.random.next;
            }
            cur=cur.next.next;
        }
        //链表分离，为了方便 设置dummy
        Node dummy=new Node(-1);
        cur=head;
        Node itr=dummy;
        //先设置复制后链表的next指针，再切断原来的next指针
        while (cur!=null){
            itr.next=cur.next;
            itr=itr.next;
            cur.next=itr.next;
            cur=cur.next;
        }
        return dummy.next;
    }
}

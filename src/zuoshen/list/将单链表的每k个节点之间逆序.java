package zuoshen.list;

import java.util.Stack;

public class 将单链表的每k个节点之间逆序 {
    public Node reverseNode(Node head,int k){
        if(k<2){
            return null;
        }
        Stack<Node>stack=new Stack<>();
        Node newHead=head;
        Node cur=head;
        Node next=null;
        Node pre=null;
        while (cur!=null){
            next=cur.next;
            stack.push(cur);
            if(stack.size()==k){
                pre=resign(stack,pre,next);
                newHead=newHead==head?cur:newHead;
            }
            cur=next;
        }
        return newHead;
    }
    public Node resign(Stack<Node>stack,Node left,Node right){
        Node cur=stack.pop();
        if(left!=null){
            left.next=cur;
        }
        Node next=null;
        while (!stack.empty()){
            next=stack.pop();
            cur.next=next;
            cur=next;
        }
        cur.next=right;
        return cur;
    }

    public static void reverse3(Node head,int k){
        int count=0;
        Node pre=null;
        Node start=null;
        Node next=null;
        Node cur=head;
        while (cur!=null){
            next=cur.next;
            if(count==k){
                start=pre==null?head:pre.next;
                head=pre==null?cur:head;
                reverse2(pre,start,cur,next);
                pre=start;
                count=0;
            }
            count++;
            cur=next;
        }
    }



    public static void reverse2(Node left,Node start,Node end,Node right){
        Node pre=left;
        Node next=null;
        Node cur=start;
        while (cur.next!=end){
            next=cur.next;
            cur.next=pre;
            pre=cur;
            cur=next;
        }
        if(left!=null){
            left.next=end;
        }
        start.next=right;
    }
}

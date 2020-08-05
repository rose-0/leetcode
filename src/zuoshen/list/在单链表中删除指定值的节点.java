package zuoshen.list;

import java.util.Stack;

public class 在单链表中删除指定值的节点 {
    public Node remove(Node head,int num){
        Stack<Node>stack=new Stack<>();
        while (head!=null){
            if(head.value!=num){
                stack.push(head);
            }
            head=head.next;
        }
        while (!stack.empty()){
            stack.peek().next=head;
            head=stack.pop();
        }
        return head;
    }
    public Node remove_value(Node head,int num){
        while (head!=null){
            if(head.value!=num){
                break;
            }
            head=head.next;
        }
        Node pre=head;
        Node cur=head;
        while (cur!=null){
            if(cur.value==num){
                pre.next=cur.next;
            }else {
                pre=cur;
            }
            cur=cur.next;
        }
        return head;
    }
}

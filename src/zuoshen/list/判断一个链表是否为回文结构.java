package zuoshen.list;

import java.util.Stack;

public class 判断一个链表是否为回文结构 {
    public boolean stack_method(Node head){
        Stack<Node>stack=new Stack<>();
        Node cur=head;
        while (cur!=null){
            stack.push(cur);
            cur=cur.next;
        }
        while (head!=null){
            if(head.value!=stack.pop().value){
                return false;
            }
            head=head.next;
        }
        return true;
    }
    public boolean stack_method_two(Node head){
        Node right=head.next;
        Node cur=head;
        while (cur.next!=null&&cur.next.next!=null){
            cur=cur.next.next;
            right=right.next;
        }
        Stack<Node>stack=new Stack<>();
        while (right!=null){
            stack.push(right);
            right=right.next;
        }
        while (!stack.empty()){
            if(stack.pop().value!=head.value){
                return false;
            }
            head=head.next;
        }
        return true;
    }
}

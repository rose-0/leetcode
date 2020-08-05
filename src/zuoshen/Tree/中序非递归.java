package zuoshen.Tree;

import java.util.Stack;

public class 中序非递归 {
    public void midOrderRecu(treeNode head){//递归实现中序
        if(head==null){
            return;
        }
        midOrderRecu(head.left);
        System.out.println(head.value);
        midOrderRecu(head.right);
    }
    public void midOrderNoRecu(treeNode head){//非递归实现中序
        if(head==null){
            return;
        }
        Stack<treeNode>stack=new Stack<>();
        while (head!=null||!stack.empty()){
            if(head!=null){
                stack.push(head);
                head=head.left;
            }else {
                head=stack.pop();
                System.out.println(head.value);
                head=head.right;
            }
        }
    }
}

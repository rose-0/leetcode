package zuoshen.Tree;

import java.util.Stack;

public class 后序非递归 {
    public void postOrder(treeNode head){
        if(head==null){
            return;
        }
        postOrder(head.left);
        postOrder(head.right);
        System.out.print(head.value+" ");
    }
    public void postOrderNo(treeNode head){
        if(head==null){
            return;
        }
        Stack<treeNode>s1=new Stack<>();
        Stack<treeNode>s2=new Stack<>();
        s1.push(head);
        while (!s1.empty()){
            head=s1.pop();
            s2.push(head);
            if(head.left!=null){
                s1.push(head.left);
            }
            if(head.right!=null){
                s1.push(head.right);
            }
        }
        while (!s2.empty()){
            System.out.println(s2.pop().value);
        }
    }
}

package zuoshen.Tree;

import java.util.Stack;

public class 先序非递归 {
    public void preOrderRecu(treeNode head){
        if(head==null){
            return;
        }
        System.out.print(head.value+" ");
        preOrderRecu(head.left);
        preOrderRecu(head.right);
    }
    public void preOrderUnrecu(treeNode head){
        if(head==null){
            return;
        }
        Stack<treeNode>stack=new Stack<>();
        stack.push(head);
        while (!stack.empty()){
            head=stack.pop();
            System.out.print(head.value+" ");
            if(head.right!=null){
                stack.push(head.right);
            }
            if(head.left!=null){
                stack.push(head.left);
            }
        }
    }

}

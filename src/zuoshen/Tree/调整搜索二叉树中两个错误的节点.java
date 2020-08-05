package zuoshen.Tree;

import java.util.Stack;

public class 调整搜索二叉树中两个错误的节点 {
    public void check_tree(treeNode head){
        if(head==null){
            return ;
        }
        Stack<treeNode>stack=new Stack<>();
        Stack<treeNode>stack2=new Stack<>();
        treeNode temp=null;
        treeNode[] err=new treeNode[2];
        while (!stack.empty()||head!=null){
            if(head!=null){
                stack.push(head);
                head=head.left;
            }else {
                head=stack.pop();
                System.out.println(head.value);
                if(temp!=null&&temp.value>head.value){
                    err[0]=err[0]==null?temp:err[0];
                    err[1]=head;
                }
                temp=head;
                head=head.right;
            }
        }

    }
}

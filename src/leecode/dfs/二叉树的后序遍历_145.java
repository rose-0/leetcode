package leecode.dfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class 二叉树的后序遍历_145 {
    
    //左神 两个栈
    public List<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> s1=new Stack<>();
        Stack<TreeNode> s2=new Stack<>();
        if(root==null){
            return null;
        }
        s1.push(root);
        List<Integer>list=new ArrayList<>();
        while (!s1.empty()){
            TreeNode cur=s1.pop();
            s2.push(cur);
            if(cur.left!=null){
                s1.push(cur.left);
            }
            if(cur.right!=null){
                s1.push(cur.right);
            }
        }
        while (!s2.empty()){
            list.add(s2.pop().val);
        }
        return list;
    }
    //leecode 题解
}

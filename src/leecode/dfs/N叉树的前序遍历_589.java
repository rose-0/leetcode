package leecode.dfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class N叉树的前序遍历_589 {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
    public List<Integer> preorder(Node root) {
        List<Integer> res=new ArrayList<>();
        dfs(res,root);
        return res;
    }
    public void dfs(List<Integer> res,Node node){
        if(node==null){
            return;
        }
        res.add(node.val);
        for(Node child:node.children){
            dfs(res,child);
        }
    }
    //迭代法
    public List<Integer> preorder2(Node root) {
        List<Integer> res=new ArrayList<>();
        if(root==null){
            return res;
        }
        Stack<Node>stack=new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            Node cur=stack.pop();
            res.add(cur.val);
            List<Node>children=cur.children;
            for(int i=children.size()-1;i>=0;i--){
                stack.push(children.get(i));
            }
        }
        return res;
    }
}

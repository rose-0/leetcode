package leecode.dfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ThreadPoolExecutor;

public class 填充每个节点的下一个右侧节点指针II_117 {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val,Node _left,Node _right,Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
    /*
    自己的思路还是从右向左打印：(弹出节点要处理三步；设置当前节点的next指针，要不要更新last，更新pre)
    设置一个flag，当cur是last时设置为true，用来标识下一次弹出的cur的next设置为null，而不是pre
    不能使用if(pre==last||cur==root){
                cur.next=null;}
    来设置空指针，因为当cur==last后，再进行下一步，last就变成了nlast了，last已经更新
    所以要设置一个标志位判断
    if(flag||cur==root){
                cur.next=null;
                flag=false;｝
    判断完成之后flag要设置回false
     */
    public Node connect(Node root){
        Queue<Node>queue=new LinkedList<>();
        queue.offer(root);
        Node last=root;
        Node nlast=null;
        Node cur=null;
        Node pre=null;
        boolean flag=false;
        while (!queue.isEmpty()){
            cur=queue.poll();
            if(cur.right!=null){
                queue.offer(cur.right);
                nlast=cur.right;
            }
            if(cur.left!=null){
                queue.offer(cur.left);
                nlast=cur.left;
            }
            if(flag||cur==root){
                cur.next=null;
                flag=false;
            }else {
                cur.next=pre;
            }
            if(cur==last){
                flag=true;
                last=nlast;
            }
            pre=cur;
        }
        return root;
    }
    public Node connect1(Node root){//和上个题目一样
        Queue<Node>queue=new LinkedList<>();
        Node cur=null;
        queue.offer(root);
        while (!queue.isEmpty()){
            int nodes=queue.size();
            Node pre=null;
            for (int i = 0; i <nodes ; i++) {
                cur=queue.poll();
                if(cur.right!=null){
                    queue.offer(cur.right);
                }
                if(cur.left!=null){
                    queue.offer(cur.left);
                }
                cur.next=pre;
                pre=cur;
            }
        }
        return root;
    }
    public Node connects2(Node root){
        Queue<Node>queue=new LinkedList<>();
        Node cur=null;
        queue.add(cur);
        while (!queue.isEmpty()){
            int size=queue.size();
            Node pre=null;
            for (int i = 0; i <size ; i++) {
                cur=queue.poll();
                if(cur.right!=null){
                    queue.offer(cur.right);
                }
                if(cur.left!=null){
                    queue.offer(cur.left);
                }
                cur.next=pre;
                pre=cur;
            }
        }
        return root;
    }
    //递归实现 https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii/comments/
    public Node connect2(Node root){//根 右 左（先处理右边！！）
        if(root==null){
            return null;
        }
        if(root.left!=null){
            if(root.right!=null){
                root.left.next=root.right;
            }else {
                root.left.next=next(root.next);
            }
        }
        if(root.right!=null){
            root.right.next=next(root.next);
        }
        connect2(root.right);
        connect2(root.left);
        return root;
    }
    public Node next(Node root){
        if(root!=null){
            if(root.left!=null){
                return root.left;
            }
            if(root.right!=null){
                return root.right;
            }
        }
        return null;
    }
}

package leecode.dfs;

import java.util.LinkedList;
import java.util.Queue;
/*
在每一层的时候，本层的next已经连接好，所以任务是：把下一层的next连好。
由于是满二叉树，所以必有左右节点，如果没有孩子节点则也不用处理了直接返回
因为本层的任务是处理孩子节点的next，左孩子连自己的右孩子，右孩子在自己的
next不为空的时候指向兄弟节点的左孩子，如果自己的next为空，则右孩子的next也为空，所以不必要处理

等到了下一层，本层的next已经连好，

迭代的解法 https://leetcode.wang/leetcode-116-Populating-Next-Right-Pointers-in-Each-Node.html
 */
public class 填充每个节点的下一个右侧节点指针_116 {
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
    public Node connect1(Node root) {
        if(root==null){
            return null;
        }
        Queue<Node>queue=new LinkedList<>();
        queue.offer(root);
        Node last=root;
        Node nlast=null;
        Node pre=root;
        Node next=null;
        while (!queue.isEmpty()){
            next=queue.poll();
            if(next.left!=null){
                queue.offer(next.left);
                nlast=next.left;
            }
            if(next.right!=null){
                queue.offer(next.right);
                nlast=next.right;
            }

            if(pre==last){
                pre.next=null;
                last=nlast;
            }else {
                pre.next=next;
            }
            if(next==root){
                next=queue.poll();
            }
            pre=next;
        }
        return root;
    }
    public Node connect(Node root){
        if(root==null||root.left==null){
            return root;
        }
        root.left.next=root.right;
        if(root.next!=null){
            root.right.next=root.next.left;
        }
        connect(root.left);
        connect(root.right);
        return root;
    }

    public Node connect2(Node root){//从右到左处理
      if(root==null){
          return root;
      }
      Queue<Node>queue=new LinkedList<>();
      queue.offer(root);

      while (!queue.isEmpty()){
          int nodes=queue.size();
          Node pre=null;
          for (int i = 0; i <nodes ; i++) {
              Node cur=queue.poll();
              if(cur.right!=null){//从右到左就不需要特别处理第一个节点，从左到右需要特别处理最后一个节点
                  queue.offer(cur.right);
                  queue.offer(cur.left);
              }
              cur.next=pre;
              pre=cur;
          }
      }
      return root;
    }
    public Node connect3(Node root){//队列实现，从左到右处理
        if(root==null||root.left==null){
            return root;
        }
        Queue<Node>queue=new LinkedList<>();
        queue.offer(root.left);//根的next不用设置，所以没有必要加到队列里面
        queue.offer(root.right);
        while (!queue.isEmpty()){
            int nodes=queue.size();
            Node left=null;
            for (int i = 0; i <nodes ; i++) {
                Node right=queue.poll();
                if(right.left!=null){
                    queue.offer(right.left);
                    queue.offer(right.right);
                }
                if(left!=null){
                    left.next=right;
                }
                left=right;
            }
        }
        return root;
    }
}

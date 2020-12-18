package leecode.dfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class N叉树的层序遍历_429 {
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
    //bfs
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>>res=new ArrayList<>();
        if(root==null){
            return res;
        }
        Queue<Node>queue=new ArrayDeque<>();
        queue.add(root);
        List<Integer>list=new ArrayList<>();
        list.add(root.val);
        res.add(list);
        while (!queue.isEmpty()){
            int size=queue.size();
            List<Integer>temp=new ArrayList<>();
            for (int i = 0; i <size ; i++) {
                Node cur=queue.poll();
                for(Node child:cur.children){
                    temp.add(child.val);
                    queue.add(child);
                }
            }
            if(temp.size()!=0){
                res.add(temp);
            }
        }
        return res;
    }
    //dfs
    public List<List<Integer>> levelOrder2(Node root) {
        List<List<Integer>>res=new ArrayList<>();
        List<Integer>list=new ArrayList<>();
        dfs(res,root,0);
        return res;
    }
    public void dfs(List<List<Integer>>res,Node node,int depth){
        if(node==null){
            return;
        }
        //到了新的一层
        if(depth+1>res.size()){
            res.add(new ArrayList<>());
        }
        res.get(depth).add(node.val);
        for(Node child:node.children){
            dfs(res,node,depth+1);
        }
    }
}

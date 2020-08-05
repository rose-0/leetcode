package 程序员面试金典;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class 有向路径检查 {
    public class UndirectedGraphNode {
        int label = 0;
        UndirectedGraphNode left = null;
        UndirectedGraphNode right = null;
        ArrayList<UndirectedGraphNode> neighbors = new ArrayList<UndirectedGraphNode>();

        public UndirectedGraphNode(int label) {
            this.label = label;
        }
    }
    public boolean checkPath(UndirectedGraphNode a, UndirectedGraphNode b) {
        return check_bfs(a,b)||check_bfs(b,a);
    }

    /*
    void search(node root){
        if(root==null)return;
        visit(root);
        root.visted=true;
        foreach(node n in root.adjacent){
            if(n.visited==false){
                search(n);
            }
        }
    }
    void search(node root){
        queue queue=new queue();
        root.visited=true;
        visit(root);
        queue.add(root);
        while(!queue.isempty()){
            node r=queue.poll();
            for(node x in r.adjacent){
                if(x.visited==false){
                    visited(x);
                    x.visited=true;
                    queue.add(x);
                }
            }
        }
    }
     */
    public boolean check_dfs(UndirectedGraphNode a,UndirectedGraphNode b){
        if(a==null||b==null)return false;
        if(a==b) return true;
        if(a.label==-1)return false;
        a.label=-1;
        for (int i = 0; i <a.neighbors.size() ; i++) {
            if(check_dfs(a.neighbors.get(i),b)){
                return true;
            }
        }
        return false;
    }
    public boolean check_bfs(UndirectedGraphNode a,UndirectedGraphNode b){
        Queue<UndirectedGraphNode>queue=new LinkedList<>();
        if(a==null||b==null)return false;
        if(a==b) return true;
        if(a.label==-1)return false;
        a.label=-1;
        queue.add(a);
        while (!queue.isEmpty()){
            UndirectedGraphNode temp=queue.poll();
            for (int i = 0; i <temp.neighbors.size() ; i++) {
                if(temp.neighbors.get(i).label==0){
                    if(temp.neighbors.get(i)==b){
                        return true;
                    }else {
                        temp.neighbors.get(i).label=-1;
                        queue.offer(temp.neighbors.get(i));
                    }
                }
            }
        }
        return false;
    }
}

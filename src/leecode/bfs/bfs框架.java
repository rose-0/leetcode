package leecode.bfs;

import zuoshen.list.Node;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

//一般就是解决两点之间最短距离，从start开始到target的最短距离
public class bfs框架 {
    int BFS(Node start,Node target){
        Queue<Node>q = new LinkedList<>();//核心数据结构，队列
        Set<Node> visited = new HashSet<>();//避免走回头路，标记访问

        q.offer(start); //起点加入队列
        visited.add(start); //标记访问
        int step=0;//记录扩散的步数

        while (!q.isEmpty()){
            int size=q.size();
            for (int i = 0; i <size ; i++) {
                Node cur = q.poll();
                if(cur.equals(target)){
                    return step;
                }
                /*
                for(Node x : cur.adj()){ //cur.adj()指cur相邻的节点，比如二维数组中该点的上下左右
                                         // visit 防止走回头路，但是一般的二叉树结构，没有子节点到父节点指针，不需要visit
                    if(x not in visited){
                        q.offer(x);
                        visited.add(x);
                    }
                }
                 */
                step++;
            }
        }
        return step;
    }
}

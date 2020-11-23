package leecode.dfs;

import java.util.*;

public class 找到最终的安全状态_802 {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<List<Integer>>list=new ArrayList<>();
        for (int i = 0; i <graph.length ; i++) {
            list.add(new ArrayList<>());
        }
        int[]degree=new int[graph.length];

        /*
        本来 0->1
        常规思路：先把出度为0的节点入队，再把指向该节点的所有节点的出度--，如果为0 则入队
        反向：把边的方向反过来，把出度变成入度，则将入度为0的节点入队
         */
        for (int i = 0; i <graph.length ; i++) {
            for (int j = 0; j <graph[i].length ; j++) {
                list.get(graph[i][j]).add(i);//建立逆邻接链表
                degree[i]++;
            }
        }
        Queue<Integer>queue=new ArrayDeque<>();
        for (int i = 0; i <degree.length ; i++) {
            if(degree[i]==0){
                queue.add(i);
            }
        }
        List<Integer>res=new ArrayList<>();
        while (!queue.isEmpty()){
            int cur=queue.poll();
            res.add(cur);
            for(int i:list.get(cur)){
                degree[i]--;
                if(degree[i]==0){
                    queue.add(i);
                }
            }
        }
        //
        Collections.sort(res);
        return res;
    }
}

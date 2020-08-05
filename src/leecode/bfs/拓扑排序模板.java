package leecode.bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

//https://www.jianshu.com/p/3347f54a3187
//对于图中的任意两个结点u和v，若存在一条有向边从u指向v，则在拓扑排序中u一定出现在v前面
//当且仅当一个有向图为有向无环图（directed acyclic graph，或称DAG）时，才能得到对应于该图的拓扑排序。
public class 拓扑排序模板 {
    //bfs
    /*
    初始化一个int[] inDegree保存每一个结点的入度。
    对于图中的每一个结点的子结点，将其子结点的入度加1。
    选取入度为0的结点开始遍历，并将该节点加入输出。
    对于遍历过的每个结点，更新其子结点的入度：将子结点的入度减1。
    重复步骤3，直到遍历完所有的结点。
    如果无法遍历完所有的结点，则意味着当前的图不是有向无环图。不存在拓扑排序。
     */
    //这是图的邻接矩阵表示方法！！
    public List<Integer>toplogicalSort(int n,int[][]adjacencyList){
        List<Integer>topRes=new ArrayList<>();//存储拓扑排序可能的结果
        int[]indegree=new int[n];
        for (int[]parent:adjacencyList) {
            for (int child:parent){//parent->child
                indegree[child]++;
            }
        }
        Queue<Integer>queue=new ArrayDeque<>();
        for (int i = 0; i <n ; i++) {
            if(indegree[i]==0){
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()){
            int cur=queue.poll();
            topRes.add(cur);
            for (int child: adjacencyList[cur]){//cur的所有孩子
                indegree[child]--;
                if(indegree[child]==0){
                    queue.offer(child);
                }
            }
        }

        return topRes.size()==0?new ArrayList<>():topRes;

    }
}

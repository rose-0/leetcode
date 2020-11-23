package leecode.dfs;

import java.util.*;

public class 课程表II_210 {
    /*
    拓扑排序：专门应用于有向图的算法；
    BFS 的写法就叫「拓扑排序」 贪心思想：当前让入度为 0 的那些结点入队，如果存在环 则得不到入度为0的节点
    拓扑排序常常为了检测有向图中是否存在环，以及得到一个排序的结果 （不唯一）
    常见题目：任务调度，课程安排
     */
    public int[] findOrderByBFS(int numCourses, int[][] prerequisites) {
        List<List<Integer>>list=new ArrayList<>();
        for (int i = 0; i <numCourses ; i++) {
            list.add(new ArrayList<>());
        }
        //入度数组：通过结点的索引，我们能够得到指向这个结点的结点个数。
        int[]indegree=new int[numCourses];
        for (int i = 0; i <prerequisites.length ; i++) {
            indegree[prerequisites[i][0]]++;
            list.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }
        Queue<Integer>queue=new LinkedList<>();
        for (int i = 0; i <numCourses ; i++) {
            if(indegree[i]==0){
                queue.add(i);
            }
        }
        int[]res=new int[numCourses];
        int index=0;
        while (!queue.isEmpty()){
            //从队首取出入度为 0 的结点，将这个结点输出到结果集中，并且将这个结点删除：即所有邻接结点（它指向的结点）的入度减 1，
            int pre=queue.poll();
            res[index++]=pre;
//            numCourses--;//
            for (int cur:list.get(pre)) {//adjacency.get(pre) pre节点指向的所有节点
                //通过入度减1 表示移除一个节点
                indegree[cur]--;
                //这个被减 1 的结点的入度为 0 ，就继续入队
                if(indegree[cur]==0){
                    queue.add(cur);
                }
            }
        }
        if(index==numCourses){//不是index==numCourses-1 因为index已经加1了
            return res;
        }
        return new int[0];
    }


    /*
    代码:https://leetcode-cn.com/problems/course-schedule-ii/solution/tuo-bu-pai-xu-shen-du-you-xian-bian-li-python-dai-/
    图示：看花花酱讲解的ppt https://zxi.mytechroad.com/blog/graph/leetcode-210-course-schedule-ii/
     */

    public int[] canFinishwithdfs(int numCourses, int[][] prerequisites) {
        List<List<Integer>>adjacency=new ArrayList<>();
        for (int i = 0; i <numCourses ; i++) {
            adjacency.add(new ArrayList<>());
        }
        int[]flags=new int[numCourses];
        //[[1,0]]:  1<---0
        for (int i = 0; i <prerequisites.length ; i++) {
            //有向图 就只需要加一个边
            adjacency.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }
        Stack<Integer>stack=new Stack<>();
        for (int i = 0; i <numCourses ; i++) {
            if(dfs(flags,i,adjacency,stack)){//这里也要直接进行判断
                return new int[0];
            }
        }
        int[] res=new int[numCourses];
        for (int i = 0; i <res.length ; i++) {
            res[i]=stack.pop();
        }
        return res;
    }
    //每个点进行dfs遍历
    /*
    更开始每个节点visit[i]=0
    正在访问中：visit[i]=1
    已经访问结束：visit[i]=-1
     */
    public boolean dfs(int[]visit,int i,List<List<Integer>>list,Stack<Integer>stack){
        if(visit[i]==1){
            //从正在访问中，到正在访问中，表示遇到了环
            return true;
        }
        if(visit[i]==-1){
            //表示在访问的过程中没有遇到环，这个节点访问过了
            return false;
        }
        visit[i]=1;//正在访问中
        for(int j:list.get(i)){
            if(dfs(visit,j,list,stack)){//这里一定要加判断
                return true;
            }
        }
        //i所有的节点都dfs访问完成，不存在环 这个节点标记为访问结束
        visit[i]=-1;
        stack.push(i);
        return false;
    }
}

package leecode.dfs;

import java.util.*;

public class 课程表II_210 {
    public int[] findOrderByBFS(int numCourses, int[][] prerequisites) {
        List<List<Integer>>list=new ArrayList<>();
        for (int i = 0; i <numCourses ; i++) {
            list.add(new ArrayList<>());
        }
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
            int pre=queue.poll();
            res[index++]=pre;
//            numCourses--;//弹出一个入度为0的点，就减一
            for (int cur:list.get(pre)) {//adjacency.get(pre) pre节点指向的所有节点
                indegree[cur]--;
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




    public int[] canFinishwithdfs(int numCourses, int[][] prerequisites) {
        List<List<Integer>>adjacency=new ArrayList<>();
        for (int i = 0; i <numCourses ; i++) {
            adjacency.add(new ArrayList<>());
        }
        int[]flags=new int[numCourses];
        //[[1,0]]:  1<---0
        for (int i = 0; i <prerequisites.length ; i++) {
            adjacency.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }
        Stack<Integer>stack=new Stack<>();
        for (int i = 0; i <numCourses ; i++) {
            if(dfs(flags,i,adjacency,stack)){
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
    public boolean dfs(int[]visit,int i,List<List<Integer>>list,Stack<Integer>stack){
        if(visit[i]==1){
            return true;
        }
        if(visit[i]==-1){
            return false;
        }
        visit[i]=1;
        for(int j:list.get(i)){
            if(dfs(visit,j,list,stack)){
                return true;
            }
        }
        visit[i]=-1;
        stack.push(i);
        return false;
    }
}

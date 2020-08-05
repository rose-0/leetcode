package leecode.dfs;

import java.util.*;

//这道题使用拓扑排序（BFS）判断有没有环比较好
//https://leetcode-cn.com/problems/course-schedule/solution/course-schedule-tuo-bu-pai-xu-bfsdfsliang-chong-fa/
//dfs解法未做
public class 课程表_207 {
    public boolean canFinish1(int numCourses, int[][] prerequisites) {
        //拓扑排序解法bfs
        //[0,1] 0 <- 1  0的先决条件是1
        //int[][] prerequisites 里面每个元素都是长度为2的一维数据
        int[]indegrees=new int[numCourses];//存放每个节点的入度,下标代表节点几
        for (int i = 0; i <prerequisites.length ; i++) {
            indegrees[prerequisites[i][0]]++;
        }
        LinkedList<Integer>queue=new LinkedList<>();
        for (int i = 0; i <numCourses ; i++) {//入度为0的节点入队
            if(indegrees[i]==0){
                queue.addLast(indegrees[i]);
            }
        }
        while (!queue.isEmpty()){
            int pre = queue.removeFirst();
            numCourses--;//出队一次，课程减一，因为出队的都是入度为0的，所以出队的总次数=入度为0节点个数
            for (int[] req:prerequisites) {
                if(req[1]!=pre)continue;//出队的节点是不是1
                //如果是1的话，要把0的入度减1，0入度减1之后如果0的入度变为0，则把0入队
                if(--indegrees[req[0]]==0){
                    queue.addLast(req[0]);
                }
            }
        }
        return numCourses==0;
    }
//    统计每个课被指向次数，初始被指向次数为0的肯定是安全的（不在环上）。
//
//    每被安全课程指向一次，被指次数减一，
//
//    如果被指次数减到0，说明该课程全部指向都来自安全课程，则它也是安全的。
//
//    依此进行队列循环。
    public boolean canFinish2(int numCoursses, int[][] prerequisites) {
        int m = prerequisites.length, head, cnt = 0;//cnt为出队的节点数
        int[] inDeg = new int[numCoursses];//每个节点的入度表
        Deque<Integer> queue = new ArrayDeque<Integer>();
        List<List<Integer>> list = new ArrayList<List<Integer>>(); // 存放每个节点相邻的所有节点
        for (int i = 0; i < numCoursses; i++)
            list.add(new ArrayList<Integer>());
        for (int i = 0; i < m; i++) {
            inDeg[prerequisites[i][0]]++; // 1 -> 0
            list.get(prerequisites[i][1]).add(prerequisites[i][0]); // 添加[1]指向的下一个节点[0]
        }
        //初始化和上面不一样
        for (int i = 0; i < numCoursses; i++)
            if (inDeg[i] == 0) // 先将入度为0的进队
                queue.addLast(i);
        while (!queue.isEmpty()) {
            head = queue.removeFirst();
            cnt++;
            for (int j = 0, val, len = list.get(head).size(); j < len; j++) { // 遍历出队节点的所有相邻节点
                val = list.get(head).get(j);
                if (--inDeg[val] == 0) // 依次将与其相关联的顶点，入度数减1，若减完为0，则直接入队
                    queue.addLast(val);
            }
        }
        return cnt == numCoursses;
    }
    /*int[][] prerequisites
    [[1,0]]: 1的先决条件是0   1<---0
     */
    public boolean canFinishwithbfs(int numCoursses, int[][] prerequisites) {
        int[]indegrees=new int[numCoursses];
        //通过prerequisites构造邻接表adjacency
        /*
        通过List<List<Integer>> 形式构造邻接矩阵的方式很巧妙，需要学习下这个！！
         */
        List<List<Integer>>adjacency=new ArrayList<>();//new ArrayList<>(5) 这个是声明了容量为5，里面并没有元素
        Queue<Integer>queue=new LinkedList<>();
        for (int i = 0; i <numCoursses ; i++) {
            //如果不添加的话，下面adjacency.get的时候就会报错
            adjacency.add(new ArrayList<>());//添加了num个元素 即num个空的list，通过下标拿出来就是空的list
        }
        for (int i = 0; i <prerequisites.length ; i++) {
            indegrees[prerequisites[i][0]]++;//1的入度加一
            adjacency.get(prerequisites[i][1]).add(prerequisites[i][0]);//0->1  1的先决条件是0,存放0指向的所有节点
        }
        for (int i = 0; i <numCoursses ; i++) {
            if(indegrees[i]==0){
                queue.add(i);
            }
        }
        while (!queue.isEmpty()){
            int pre=queue.poll();
            numCoursses--;//弹出一个入度为0的点，就减一
            for (int cur:adjacency.get(pre)) {//adjacency.get(pre) pre节点指向的所有节点
                indegrees[cur]--;
                if(indegrees[cur]==0){
                    queue.add(cur);
                }
            }
        }
        return numCoursses==0;
    }

    //dfs 原理是通过dfs判断图是否有环  不需要入度表
    public boolean canFinishwithdfs(int numCoursses, int[][] prerequisites) {
        List<List<Integer>>adjacency=new ArrayList<>();
        for (int i = 0; i <numCoursses ; i++) {
            adjacency.add(new ArrayList<>());
        }
        int[]flags=new int[numCoursses];
        //[[1,0]]:  1<---0
        for (int i = 0; i <prerequisites.length ; i++) {
            adjacency.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }
        for (int i = 0; i <numCoursses ; i++) {
            if(dfsnode(adjacency,flags,i)){
                return false;
            }
        }
        return true;
    }
    /*
    判断每个节点起步 DFS 是否存在环，若存在环直接返回 true:
    当 flag[i] == -1，说明当前访问节点已被其他节点启动的 DFS 访问，无需再重复搜索，直接返回 false。
    当 flag[i] == 1，说明在本轮 DFS 搜索中节点 i 被第 2 次访问，即 课程安排图有环 ，直接返回 False。
     */
    private boolean dfsnode(List<List<Integer>>adjacency,int[]flags,int i){
        if(flags[i]==1) return true;//当前节点又开始dfs 则有环
        if(flags[i]==-1) return false;//已经被dfs了 ，不用再搜索了
        flags[i]=1;//当前节点开始dfs 标记为1
        for (int j:adjacency.get(i)){
            if(dfsnode(adjacency,flags,j)) return true;
        }
        //当前节点已经dfs了，没有环，所以上面if(flags[i]==-1) 说明走到了一个已经被dfs的节点，且没有环
        flags[i]=-1;//当前节点dfs结束 标记为-1,
        return false;
    }
}

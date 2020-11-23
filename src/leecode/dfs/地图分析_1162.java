package leecode.dfs;

import java.util.LinkedList;
import java.util.Queue;

public class 地图分析_1162 {
    int[][]dir=new int[][]{{0,-1},{0,1},{1,0},{-1,0}};
    //https://leetcode-cn.com/problems/as-far-from-land-as-possible/solution/li-qing-si-lu-wei-shi-yao-yong-bfs-ru-he-xie-bfs-d/
    public int maxDistance(int[][] grid) {
        int m=grid.length;
        int n=grid[0].length;
        Queue<int[]>queue=new LinkedList<>();
        //把所有的1加入队列，相当于进行多源的bfs，原来树bfs是单源的，多源相当于是单源的第一层
        //https://leetcode-cn.com/problems/as-far-from-land-as-possible/solution/zhen-liang-yan-sou-huan-neng-duo-yuan-kan-wan-miao/
        for (int i = 0; i <m ; i++) {
            for (int j = 0; j <n ; j++) {
                if(grid[i][j]==1){
                    queue.offer(new int[]{i,j});
                }
            }
        }
        if(queue.size()==0||queue.size()==m*n){
            return -1;
        }
        int distance=-1;
        /*
        tree只有1个root，而图可以有多个源点，所以首先需要把多个源点都入队。

        tree是有向的因此不需要标志是否访问过，而对于无向图来说，必须得标志是否访问过！
        并且为了防止某个节点多次入队，需要在入队之前就将其设置成已访问！
         */
        boolean[][]visit=new boolean[m][n];
        while (!queue.isEmpty()){
            int size=queue.size();
            distance++;
            for (int i = 0; i <size ; i++) {
                int[]curPoint=queue.poll();
                int x=curPoint[0];
                int y=curPoint[1];
//                visit[x][y]=true;
                for (int j = 0; j <4 ; j++) {
                    int newX=x+dir[j][0];
                    int newY=y+dir[j][1];
                    //因为开始把1全部加进去了 这里把1周围的海洋0全部加进去
                    if(isBound(grid,newX,newY)&&grid[newX][newY]==0&&!visit[newX][newY]){
                        visit[newX][newY]=true;//这里入队的时候就要标记，防止下次重复入队
                        queue.add(new int[]{newX,newY});
                    }
                }
            }
        }
        return distance;
    }
    public boolean isBound(int[][]grid,int x,int y){
        return x>=0&&x<grid.length&&y>=0&&y<grid[0].length;
    }
}

package 笔试代码.other;

import java.util.LinkedList;
import java.util.Queue;

//https://www.cnblogs.com/grandyang/p/6381458.html
//优化算法采用map记录
//https://github.com/cherryljr/LeetCode/blob/master/The%20Maze.java
/*
回溯算法的visit[i][j]的标记在回溯完成之后是否需要还原
如果是从一点开始的dfs回溯，则不需要，并且开头判断如果已经访问过则返回false，如迷宫lin_787问题，因为从这一点dfs意味着这一点开始能够到达另一点
这个迷宫问题标记为true的点时回溯点周边的方向点，自己可以运行看一下，不是所有的点
如果要判断二维矩阵每一点能不能满足解，则需要还原，如单词搜索问题
但是回溯对于一些全局变量的修改是一定要恢复的
 */
public class 迷宫lin_787 {
    public  static boolean hasPath(int[][] maze, int[] start, int[] destination) {
        // write your code here
        int m=maze.length;
        int n=maze[0].length;
        boolean[][]visit=new boolean[m][n];
        int startx=start[0];
        int starty=start[1];
        int endx=destination[0];
        int endy=destination[1];
        int[][]next={{-1,0},{1,0},{0,-1},{0,1}};

        boolean flag= dfs(maze,visit,start,startx,starty,endx,endy,next);
        for (int i = 0; i <m ; i++) {
            for (int j = 0; j <n ; j++) {
                System.out.print(visit[i][j]+" ");
            }
            System.out.println();
        }
        return flag;
    }
    public static boolean dfs(int[][] maze,boolean[][]visit,int[] start,
                       int startx,int starty,int endx,int endy,
                       int[][]next)
    {
        if(visit[startx][starty]){
            return false;
        }
        if(startx==endx&&starty==endy){
            return true;
        }
//        if((startx==start[0]&&starty==start[1])||maze[startx][starty]==1||bound(maze,startx,starty)) {
            visit[startx][starty]=true;
//            for (int i = 0; i < 4; i++) {
//                int nextx=startx+next[i][0];
//                int nexty=starty+next[i][1];
//                if(bound2(maze,nextx,nexty)&&!visit[nextx][nexty]){
//                    dfs(maze,visit,start,nextx,nexty,endx,endy,next);
//                }
//            }
        /*
        这里不能使用这种方式，应该使用下面一种方式，startx，starty不能改变，因为递归结束，下一个方向也要从这里开始
         */
        /*
            for (int i = 0; i < 4; i++) {
                //最后一个条件要注意maze[startx][starty]!=1，循环结束后要减去一个单位的值
                while (startx>=0&&startx<maze.length&&starty>=0&&starty<maze[0].length&&maze[startx][starty]!=1) {
                    startx = startx + next[i][0];
                    starty = starty + next[i][1];
                }
                startx = startx - next[i][0];
                starty = starty - next[i][1];
                if(!visit[startx][starty]){
                    /*
                    注意这里，如果dfs为true，则及时返回true，不能写成不加if的形式
                    dfs(maze,visit,start,startx,starty,endx,endy,next);
                     /*
                     if(dfs(maze,visit,start,startx,starty,endx,endy,next)){
                        return true;
                    }
                }
            }
            */

             for (int i = 0; i < 4; i++) {
                int nextx=startx;
                int nexty=starty;
                while (nextx>=0&&nextx<maze.length&&nexty>=0&&nexty<maze[0].length&&maze[nextx][nexty]!=1) {
                    nextx =nextx  + next[i][0];
                    nexty =nexty + next[i][1];
                }
                nextx =nextx  -next[i][0];
                nexty =nexty - next[i][1];
                if(!visit[nextx][nexty]){
                     if(dfs(maze,visit,start,nextx,nexty,endx,endy,next)){
                        return true;
                    }
                }
            }

//            visit[startx][starty]=false;
//        }
        return false;
    }
    public boolean bound(int[][] maze,int x,int y){
        if(x==0||y==0||x==maze.length-1||y==maze[0].length-1){
            return true;
        }
        return false;
    }
    public boolean bound2(int[][] maze,int x,int y){
        if(x<0||y<0||x>maze.length-1||y>maze[0].length-1){
            return false;
        }
        return true;
    }
    /*
    bfs实现
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
    public boolean hasPath_bfs(int[][] maze, int[] start, int[] destination) {
        int m=maze.length;
        int n=maze[0].length;
        boolean[][]visit=new boolean[m][n];
        Queue<int[]>queue=new LinkedList<>();
        visit[start[0]][start[1]]=true;
        queue.offer(start);
        int[][]dir={{-1,0},{1,0},{0,-1},{0,1}};
        while (!queue.isEmpty()){
            int[]poi=queue.poll();
            if(poi[0]==destination[0]&&poi[1]==destination[1]){
                return true;
            }
            for (int i = 0; i <4 ; i++) {
                int x=poi[0];
                int y=poi[1];
                while (x>=0&&y>=0&&x<maze.length&&y<maze[0].length&&maze[x][y]!=1){
                    x=x+dir[i][0];
                    y=y+dir[i][1];
                }
                x=x-dir[i][0];
                y=y-dir[i][1];
                if(!visit[x][y]){
                    visit[x][y]=true;//不要少了这个
                    queue.offer(new int[]{x,y});
                }
            }

        }
        return false;
    }
    public static void main(String[] args) {
        int[][]maze={
                {0,0,1,0,1},
                {0,0,0,0,0},
                {0,0,0,1,0},
                {1,1,0,1,1},
                {0,0,0,0,0}
        };
        int[]start={0,4};
        int[]end={4,0};
        System.out.println(hasPath(maze,start,end));
    }
}

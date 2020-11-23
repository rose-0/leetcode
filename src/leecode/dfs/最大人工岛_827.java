package leecode.dfs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class 最大人工岛_827 {

    //暴力解法
    public int largestIsland(int[][] grid) {
        int m=grid.length;
        int n=grid[0].length;
//        boolean[][]visit=new boolean[m][n];
        int max=0;
        for (int i = 0; i <m ; i++) {
            for (int j = 0; j <n ; j++) {
                if(grid[i][j]==0){
                    grid[i][j]=1;
                    //注意这里，从一个 0 点进行dfs时候，都要初始化一个visit数组，将0变成1，相当于创建了一个新的图
                    boolean[][]visit=new boolean[m][n];
                    int count=dfs(grid,i,j,visit);
                    max=Math.max(max,count);
                    grid[i][j]=0;
                }
            }
        }
        return max==0?m*n:max;
    }
    int[][]dir=new int[][]{{0,-1},{0,1},{1,0},{-1,0}};
    public int dfs(int[][] grid,int i,int j,boolean[][]visit){
        int count=1;
        visit[i][j]=true;
        //dfs终止的条件
        for (int k = 0; k <4 ; k++) {
            int x=i+dir[k][0];
            int y=j+dir[k][1];
            if(isBound(grid,x,y)&&grid[x][y]==1&&!visit[x][y]){
                count=count+dfs(grid,x,y,visit);
            }
        }
        return count;
    }
    public boolean isBound(int[][]grid,int x,int y){
        return x>=0&&x<grid.length&&y>=0&&y<grid[0].length;
    }


    //图解： https://leetcode-cn.com/problems/number-of-islands/solution/dao-yu-lei-wen-ti-de-tong-yong-jie-fa-dfs-bian-li-/
    //代码： https://leetcode-cn.com/problems/making-a-large-island/solution/827-zui-da-ren-gong-dao-yan-xu-463-dao-yu-de-zhou-/
    //岛屿最大面积的升级版

    //优化解法
    public int largestIsland2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][]visit=new boolean[m][n];
        int index=1;//从2开始，因为1和0表示海洋和陆地
        Map<Integer,Integer> map=new HashMap<>();
        for (int i = 0; i <m ; i++) {
            for (int j = 0; j <n ; j++) {
                if(grid[i][j]==1&&!visit[i][j]){
                    index++;
                    int count=dfs2(grid,i,j,visit,index);
                    map.put(index,count);
                }
            }
        }

        //对于每个0 计算周围的面积
        int max=0;
        for (int i = 0; i <m ; i++) {
            for (int j = 0; j < n; j++) {
//                if(grid[i][j]==0){//可能grid全为1，所以这里需要注释掉
                    max=Math.max(max,calLand(grid,i,j,map));
//                }
            }
        }
        return max;
    }

    //计算图的面积
    public int dfs2(int[][] grid,int i,int j,boolean[][]visit,int index){

        int count=1;
        visit[i][j]=true;
        grid[i][j]=index;
        for (int k = 0; k <4 ; k++) {
            int x=i+dir[k][0];
            int y=j+dir[k][1];
            if(isBound(grid,x,y)&&grid[x][y]==1&&!visit[x][y]){
                count=count+dfs2(grid,x,y,visit,index);
            }
        }
        return count;
    }

    //0的周边放进set里面，计算面积
    public int calLand(int[][] grid,int i,int j,Map<Integer,Integer>map){
        Set<Integer>set = new HashSet<>();
        //如果grid全为1时候 返回
        if(grid[i][j]!=0){
            return map.get(grid[i][j]);
        }

        for (int k = 0; k <4 ; k++) {
            int x=i+dir[k][0];
            int y=j+dir[k][1];
            if(isBound(grid,x,y)&&grid[x][y]!=0){
                set.add(grid[x][y]);
            }
        }
        int count=0;
        for(Integer index:set){
            count+=map.get(index);
        }
        return count+1;//注意这里要加上当前海洋
    }

}

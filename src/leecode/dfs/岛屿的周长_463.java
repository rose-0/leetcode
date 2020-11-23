package leecode.dfs;
//岛屿系列问题题解
//https://leetcode-cn.com/problems/number-of-islands/solution/dao-yu-lei-wen-ti-de-tong-yong-jie-fa-dfs-bian-li-/
public class 岛屿的周长_463 {
    public int islandPerimeter(int[][] grid) {
        int m=grid.length;
        int n=grid[0].length;
        boolean[][]visit=new boolean[m][n];
        int res=0;
        for (int i = 0; i <m ; i++) {
            for (int j = 0; j <n ; j++) {
                if(grid[i][j]==1){
//                    res=dfs(grid,i,j,visit); 不能这样写 这样的话对每个1进行dfs，那么相连的1 后遍历的返回0
                    return dfs(grid,i,j,visit);
                }
            }
        }
        return res;
    }

    int[][]dir=new int[][]{{0,-1},{0,1},{1,0},{-1,0}};

    public int dfs(int[][] grid,int i,int j,boolean[][]visit){
        /*
        这道题目可以更好的理解dfs的过程
        开头的判断都是dfs时选择方向时候的抉择
         */
        //下一个方向越界 则不进行dfs
        if(!isBound(grid,i,j)){
            return 1;
        }
        //下一个方向已经进行dfs 则返回
        if(visit[i][j]){
            return 0;
        }
        //下一个方向不是陆地则返回
        if(grid[i][j]!=1){
            return 1;
        }
        visit[i][j]=true;
        int res=0;
        for (int k = 0; k <4 ; k++) {
            int x=i+dir[k][0];
            int y=j+dir[k][1];
            res=res+dfs(grid,x,y,visit);
        }
        return res;
    }
    public boolean isBound(int[][]grid,int x,int y){
        return x>=0&&x<grid.length&&y>=0&&y<grid[0].length;
    }
}

package leecode.dfs;

public class 图dfs模版 {
    int[][]dir=new int[][]{{0,-1},{0,1},{1,0},{-1,0}};
    public int maxAreaOfIsland(int[][] grid) {
        int m=grid.length;
        int n=grid[0].length;
        boolean[][]visit=new boolean[m][n];
        for (int i = 0; i <m ; i++) {
            for (int j = 0; j <n ; j++) {
                if(grid[i][j]==1){
                    //如何获得相连1的数量

                }
            }
        }
        return 0;
    }
    public void dfs(int[][] grid,int i,int j,boolean[][]visit){

        for (int k = 0; k <4 ; k++) {
            int x=i+dir[k][0];
            int y=j+dir[k][1];

        }
    }
    public boolean isBound(int[][]grid,int x,int y){
        return x>=0&&x<grid.length&&y>=0&&y<grid[0].length;
    }
}

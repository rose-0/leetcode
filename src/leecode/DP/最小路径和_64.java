package leecode.DP;

public class 最小路径和_64 {
    //注意 每次只能向下 或者 向右
    public int minPathSum(int[][] grid) {
        int m=grid.length;
        int n=grid[0].length;
        int[][]dp=new int[m][n];
        dp[m-1][n-1]=grid[m-1][n-1];
        for (int i = m-2,j=n-1; i >=0 ; i--) {
            dp[i][j]=grid[i][j]+dp[i+1][j];
        }
        for (int i = m-1,j=n-2; j >=0 ; j--) {
            dp[i][j]=grid[i][j]+dp[i][j+1];
        }
        for (int i = m-2; i >=0 ; i--) {
            for (int j = n-2; j >=0 ; j--) {
                dp[i][j]=Math.min(dp[i+1][j],dp[i][j+1])+grid[i][j];
            }
        }
        return dp[0][0];
    }
}

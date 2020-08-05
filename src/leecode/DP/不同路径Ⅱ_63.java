package leecode.DP;
//对最后一行一列修改一定要注意，如果有一个1，则后面全部为0；不像上个题目全赋值为1，从后向前赋值
public class 不同路径Ⅱ_63 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m=obstacleGrid.length;
        int n=obstacleGrid[0].length;
        if(m<0||n<0){
            return 0;
        }
        int[][]dp=new int[m][n];
        if(obstacleGrid[m-1][n-1]==1){
            return 0;
        }
        for (int i = m-1,j=n-1; i >=0 ; i--) {
            if(obstacleGrid[i][j]==1){
                dp[i][j]=0;
                break;
            }
            dp[i][j]=1;
        }
        for (int i = m-1,j=n-1; j >=0 ; j--) {
            if(obstacleGrid[i][j]==1){
                dp[i][j]=0;
                break;
            }
            dp[i][j]=1;
        }
        for (int i = m-2; i >=0 ; i--) {
            for (int j = n-2; j >=0 ; j--) {
                dp[i][j]=obstacleGrid[i][j]==1?0:(dp[i+1][j]+dp[i][j+1]);
            }
        }
        return dp[0][0];
    }
}

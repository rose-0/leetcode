package leecode.DP;
//初始 dp[i][j]= matrix[i][j] == '1'?1:0
//多申请一行一列就会好处理
public class 最大正方形_221 {
    public int maximalSquare(char[][] matrix) {
        int n=matrix.length;
        int[][]dp=new int[n+1][n+1];
        int max=0;
        for (int i = 1; i <dp.length ; i++) {
            for (int j = 1; j <dp[0].length ; j++) {
                if(matrix[i-1][j-1]=='1'){
                    dp[i][j]=1+Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]));
                    max=Math.max(max,dp[i][j]);
                }
            }
        }
        return max*max;
    }
}

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
    /*
    这个人画的图很好理解
    https://leetcode-cn.com/problems/maximal-square/solution/221-zui-da-zheng-fang-xing-tu-jie-shi-pin-yan-shi-/

     */
    public int maximalSquare2(char[][] matrix) {
        int[][]dp=new int[matrix.length][matrix[0].length];
        int maxbian=0;
        for (int i = 0; i <matrix.length ; i++) {
            dp[i][0]=matrix[i][0]-'0';
            maxbian=Math.max(maxbian,dp[i][0]);
        }
        for (int j = 0; j <matrix[0].length ; j++) {
            dp[0][j]=matrix[0][j]-'0';
            maxbian=Math.max(maxbian,dp[0][j]);
        }
        for (int i = 1; i <matrix.length ; i++) {
            for (int j = 1; j <matrix[0].length ; j++) {
                if(matrix[i][j]=='1'){
                    dp[i][j]=Math.min(dp[i-1][j],Math.min(dp[i-1][j-1],dp[i][j-1]))+1;
                    maxbian=Math.max(maxbian,dp[i][j]);
                }
            }
        }
        return maxbian*maxbian;
    }
}

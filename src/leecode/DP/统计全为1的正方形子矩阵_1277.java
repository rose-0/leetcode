package leecode.DP;

public class 统计全为1的正方形子矩阵_1277 {
    //https://leetcode-cn.com/problems/count-square-submatrices-with-all-ones/solution/tong-ji-quan-wei-1-de-zheng-fang-xing-zi-ju-zhen-f/
    //对比lee221 最大正方形
    public int countSquares(int[][] matrix) {
        int ans=0;
        int[][]dp=new int[matrix.length][matrix[0].length];
        for (int i = 0; i <matrix.length ; i++) {
            dp[i][0]=matrix[i][0];
            ans=ans+dp[i][0];
        }
        for (int j = 0; j <matrix[0].length ; j++) {
            dp[0][j]=matrix[0][j];
            ans=ans+dp[0][j];
        }
        if(matrix[0][0]==1){
            ans--;
        }
        for (int i = 1; i <dp.length ; i++) {
            for (int j = 1; j <dp[0].length ; j++) {
                if(matrix[i][j]==1){
                    dp[i][j]=Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]))+1;
                    ans=ans+dp[i][j];
                }
            }
        }
        return ans;
    }
}

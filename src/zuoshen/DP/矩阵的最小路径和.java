package zuoshen.DP;

public class 矩阵的最小路径和 {
    public int min_sum(int[][]matix,int i,int j){
        if(i==matix.length-1&&j==matix[0].length-1){
            return matix[i][j];
        }
        if(i==matix.length-1){
            return min_sum(matix,i,j+1)+matix[i][j];
        }
        if(j==matix[0].length-1){
            return min_sum(matix,i+1,j)+matix[i][j];
        }
        int right=matix[i][j]+min_sum(matix,i,j+1);
        int down=matix[i][j]+min_sum(matix,i+1,j);
        return Math.min(right,down);
    }
    public int min_sum_dp(int[][]matix,int a,int b){
        int row=matix.length;//行数
        int col=matix[0].length;//列数
        int[][]dp=new int[row][col];
        dp[row-1][col-1]=0;
        for(int i=row-1, j=col-2;j>=0;j--){
            dp[i][j]=dp[i][j+1]+matix[i][j];
        }
        for(int i=row-2,j=col-1;i>=0;i--){
            dp[i][j]=dp[i+1][j]+matix[i][j];
        }
        for(int i=row-2;i>=0;i--){
            for(int j=col-2;j>=0;j--){
                dp[i][j]=matix[i][j]+Math.min(dp[i+1][j],dp[i][j+1]);
            }
        }
        return dp[0][0];
    }
}

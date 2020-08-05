package leecode.DP;

public class 编辑距离_72 {
    public int minDistance(String word1, String word2) {
        char[]chars1=word1.toCharArray();
        char[]chars2=word2.toCharArray();
        int m=word1.length()+1;
        int n=word2.length()+1;
        int[][]dp=new int[m][n];
        for (int i = 1; i <m ; i++) {
            dp[i][0]=i;
        }
        for (int i = 1; i <n ; i++) {
            dp[0][i]=i;
        }
        for (int i = 1; i <m ; i++) {
            for (int j = 1; j <n ; j++) {
                if(chars1[i-1]==chars2[j-1]){
                    dp[i][j]=dp[i-1][j-1];
                }else {
                    dp[i][j]=dp[i-1][j-1]+1;
                }
                dp[i][j]=Math.min(dp[i][j],dp[i][j-1]+1);
                dp[i][j]=Math.min(dp[i][j],dp[i-1][j]+1);
            }
        }
        return dp[m-1][n-1];
    }
}

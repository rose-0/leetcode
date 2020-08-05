package zuoshen.DP;

public class 换钱的最少货币数_任意张 {
    public static int method_dp(int[]arr,int aim){
        int[][]dp=new int[arr.length][aim+1];
        int max=Integer.MAX_VALUE;
        for (int i = 1; i <=aim ; i++) {
            dp[0][i]=i%arr[0]==0?i/arr[0]:max;
        }
        for (int i = 0; i <arr.length ; i++) {
            dp[i][0]=0;
        }
        for (int i = 1; i <arr.length ; i++) {
            for (int j = 1; j <=aim ; j++) {
                dp[i][j]=dp[i-1][j];
                if(j-arr[i]>=0&&dp[i][j-arr[i]]!=max){
                    dp[i][j]=Math.min(dp[i][j],dp[i][j-arr[i]]+1);
                }
            }
        }
        return dp[arr.length-1][aim]==max?-1:dp[arr.length-1][aim];
    }

    public static void main(String[] args) {
        int[]arr={5,2,3};
        System.out.println(method_dp(arr,20));
    }
}

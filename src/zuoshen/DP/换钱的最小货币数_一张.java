package zuoshen.DP;

public class 换钱的最小货币数_一张 {
    public static int method_dp(int[]arr,int aim){
        int[][]dp=new int[arr.length][aim+1];
        dp[0][0]=0;
        int max=Integer.MAX_VALUE;
        for (int i = 1; i <=aim ; i++) {
            dp[0][i]=i==arr[0]?1:max;
        }
        for (int i = 1; i <dp.length ; i++) {
            for (int j = 0; j <dp[0].length ; j++) {
                dp[i][j]=dp[i-1][j];
                if(j-arr[i]>=0&&dp[i-1][j-arr[i]]!=max){
                    dp[i][j]=Math.min(dp[i][j],dp[i-1][j-arr[i]]+1);
                }
            }
        }
        return dp[arr.length-1][aim]==max?-1:dp[arr.length-1][aim];
    }

    public static void main(String[] args) {
        int[]arr={5,2,5,3};
        System.out.println(method_dp(arr,10));
//        for (int i = 0; i < ; i++) {
//
//        }
    }
}

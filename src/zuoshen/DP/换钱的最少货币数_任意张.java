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
        printdp(dp);
        return dp[arr.length-1][aim]==max?-1:dp[arr.length-1][aim];
    }
    // 都多一行一列
    public static int method_dp2(int[]arr,int aim){
        int[][]dp=new int[arr.length+1][aim+1];
        int max=Integer.MAX_VALUE;
        for (int i = 1; i <dp[0].length ; i++) {
            dp[0][i]=max;
        }
//        for (int i = 0; i <arr.length ; i++) {
//            dp[i][0]=0;
//        }
        for (int i = 1; i <dp.length ; i++) {
            for (int j = 1; j <dp[0].length ; j++) {
                dp[i][j]=dp[i-1][j];
                if(j-arr[i-1]>=0&&dp[i][j-arr[i-1]]!=max){
                    dp[i][j]=Math.min(dp[i][j],dp[i][j-arr[i-1]]+1);
                }
            }
        }
        printdp(dp);
        return dp[dp.length-1][dp[0].length-1]==max?-1:dp[dp.length-1][dp[0].length-1];
    }
    public static void printdp(int[][]dp){
        for (int i = 0; i <dp.length ; i++) {
            for (int j = 0; j <dp[0].length ; j++) {
                System.out.print(dp[i][j]+"  ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        int[]arr={1,2,5};
        System.out.println(method_dp(arr,11));
        System.out.println(method_dp2(arr,11));
    }
}

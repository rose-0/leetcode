package leecode.DP;

import java.util.Arrays;

//对比数组累加和为aim 数组选择数累加为aim
//这个其实也可以作为二维dp，和左神换钱的最少货币数一样！！p191
//可以参考 换钱的最少货币数_任意张method_dp2 都多一行一列
public class 零钱兑换_322 {
    static int res=Integer.MAX_VALUE;
    public static int coinChange(int[] coins, int amount) {
        return 0;
    }
    //https://github.com/labuladong/fucking-algorithm/blob/master/%E7%AE%97%E6%B3%95%E6%80%9D%E7%BB%B4%E7%B3%BB%E5%88%97/%E5%AD%A6%E4%B9%A0%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84%E5%92%8C%E7%AE%97%E6%B3%95%E7%9A%84%E9%AB%98%E6%95%88%E6%96%B9%E6%B3%95.md
    public static void baoli(int[]coins,int amount,int count){//可以看出变量只有一个
        if(amount<0){//像是树的遍历结构，（或者回溯框架），回溯框架的终止条件
            return ;
        }
        if(amount==0){
            res=Math.min(res,count);
        }
        for (int i = 0; i <coins.length ; i++) {//回溯框架的选择列表就是树的所有子节点
            baoli(coins,amount-coins[i],count+1);
        }
    }
    /*
    public static int coinChangedp(int[] coins, int amount) {
        int[]memo=new int[amount+1];//memo[n]凑成n需要最少的硬币数目，一定要初始化
        for (int i = 1; i <memo.length ; i++) {
            int min=Integer.MAX_VALUE;
            for (int j = 0; j <coins.length ; j++) {
                if(i-coins[j]>=0){
                    min=Math.min(min,memo[i-coins[j]]+1);
                }
            }
            memo[i]=min;
        }
        return memo[amount]==Integer.MAX_VALUE?-1:memo[amount];

    }
     */
    //二维转化成一维就是左神的空间压缩算法。。
    // dp[i]=k 目标金额为i时，最少需要k枚硬币
    public static int coinChangedp(int[] coins, int amount) {
        int[]memo=new int[amount+1];//memo[n]凑成n需要最少的硬币数目，一定要初始化
        for (int i = 1; i <memo.length ; i++) {
            memo[i]=amount+1;
        }
        //dp[0]是0！！注意，如果上面把所有数都初始化为amount+1,这儿要把dp[0]重新初始化为0；
        for (int i = 1; i <memo.length ; i++) {
            for (int j = 0; j <coins.length ; j++) {
                //这里对于每个i，不管你前面有没有用过coin[j],都可以再用，所以是任意张
                if(i-coins[j]>=0){
                    memo[i]=Math.min(memo[i],memo[i-coins[j]]+1);//不要丢到+1
                }
            }
        }
        for (int i = 0; i <memo.length ; i++) {
            System.out.println("i="+i+"->"+memo[i]);
        }
        return memo[amount]==(amount+1)?-1:memo[amount];

    }
    //二维dp 左神的方法 可以简化成一位dp，与左神P153 类似，只是没有初始化为最大值
    public static int coinChangewithZUOSHEN(int[] coins, int amount) {
        int[][]dp=new int[coins.length+1][amount+1];//多申请一行一列
        for(int i=0;i<dp[0].length;i++){
            dp[0][i]=Integer.MAX_VALUE;//第一行初始化为max
        }
        for(int i=1;i<dp.length;i++){
            for (int j = 1; j < dp[0].length; ++j) {
                //
                dp[i][j]=dp[i-1][j];
                if(j-coins[i-1]>=0&&dp[i][j-coins[i-1]]!=Integer.MAX_VALUE){//不要忘记后面这个条件
                    dp[i][j]=Math.min(dp[i][j],dp[i][j-coins[i-1]]+1);
                }
            }
        }
        return dp[coins.length][amount]==Integer.MAX_VALUE?-1:dp[coins.length][amount];
    }

    //左神二维转一维dp
    public static int coinChangewithZUOSHEN2(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp,1,dp.length,Integer.MAX_VALUE);
        for (int i = 0; i < coins.length; i++) {//这个和上面那个内外层循环正好相反，还需要再理解
            for (int j=0; j <= amount; j++) {
                if(j-coins[i]>=0&&dp[j-coins[i]]!=Integer.MAX_VALUE){
                    dp[j]=Math.min(dp[j], dp[j-coins[i]]+1);
                }
            }
        }
        return dp[amount]==Integer.MAX_VALUE?-1:dp[amount];
    }

        public static void main(String[] args) {
        int[]coins={1,2,5};

//        baoli(coins,11,0);
//        System.out.println(res);
//        System.out.println(coinChangedp(coins,11));
            System.out.println(coinChangewithZUOSHEN2(coins,11));
    }
}

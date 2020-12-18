package leecode.DP;

public class 最佳买卖股票时机含冷冻期_309 {
    /*
    https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/solution/dong-tai-gui-hua-by-liweiwei1419-5/
    先定义状态 不持股定义为两种状态
    如何画状态图：通过枚举状态之间的转换关系，来画出状态图
    https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/solution/zhuang-tai-ji-mo-xing-dp-by-acw_wangdh15/
    可以看这个人的状态转移图：明白各个状态之间是通过什么操作（买入卖出）转换的
    通过状态图写出状态转移方程
     */
    /*
    第i天不持股 = 前一天不持股， 前一天卖出(第二天不能买入，就是不持股)
    dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2]);

    第i天持股 = 前一天持股， 前一天不持股且买入
    dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]); //注意 不是prices[i-1]

    第i天不持股(当天卖出) = 前一天持股
    dp[i][2] = dp[i - 1][1] + prices[i]; //注意 不是prices[i-1]
     */
    public int maxProfit(int[] prices) {
        int[][]dp=new int[prices.length][3];
        dp[0][0]=0;
        dp[0][1]=-prices[0];
        dp[0][2]=0;
        for (int i = 1; i <prices.length ; i++) {
            dp[i][0]=Math.max(dp[i-1][0],dp[i-1][2]);
            dp[i][1]=Math.max(dp[i-1][1],dp[i-1][0]-prices[i]);
            dp[i][2]=dp[i-1][1]+prices[i];
        }
        return Math.max(dp[prices.length-1][0],dp[prices.length-1][2]);//要想获得最大收益肯定是不持有股票
    }
}

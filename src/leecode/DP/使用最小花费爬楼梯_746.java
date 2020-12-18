package leecode.DP;

public class 使用最小花费爬楼梯_746 {
    /*
    https://leetcode-cn.com/problems/min-cost-climbing-stairs/solution/cong-meng-bi-dao-ti-jie-de-quan-guo-cheng-by-keepa/
     */
    public int minCostClimbingStairs(int[] cost) {
        //暴力
        //从0 或 1 开始爬
        int res = Math.min(baoli(cost, 0), baoli(cost, 1));
        return 0;
    }

    public int baoli(int[] cost, int i) {
        if (i >= cost.length) {
            return 0;
        }
        int num1 = baoli(cost, i + 1) + cost[i];//当前楼梯爬一级
        int num2 = baoli(cost, i + 2) + cost[i];//当前楼梯爬二级
        return Math.min(num1, num2);
    }

    public int dp(int[] cost) {
        //dp数组可以看做是记录走到当前阶梯i，（把当前阶梯的屎吃掉，可以看评论理解这个题目）继续向上所需的最小体力值。
        int[] dp = new int[cost.length + 1];
        //可以和题解一样，将dp数组加2，使得不用单独处理开始两个
        dp[0] = cost[0];
        dp[1] = Math.min(dp[0], 0) + cost[1];
        for (int i = 2; i < cost.length; i++) {
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i];
        }
        int len = dp.length;
        //注意这里，最后求的是顶部
        dp[len - 1] = Math.min(dp[len - 2], dp[len - 3]);
        return dp[dp.length - 1];
    }

    //优化dp
    public int dp2(int[] cost) {
        int[]dp=new int[cost.length+3];
        int dp_i_1=0;
        int dp_i_2=0;
        int dp_i=0;
        for (int i = 2; i <dp.length-1 ; i++) {
            dp_i=Math.min(dp_i_2,dp_i_1)+cost[i-2];
            dp_i_2=dp_i_1;
            dp_i_1=dp_i;
        }
        return Math.min(dp_i_1,dp_i_2);
    }
}

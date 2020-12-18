package leecode.DP;

import java.util.*;

public class 完全平方数_279 {
    /*
    在这里，平方数sqr就是物品的体积，物品产生的价值val都是1。题目问的问题是，
    能让这m个物品装满空间为n的背包的方法中，产生价值最少的装法，所以把模板里的最大值改成最小值就行。

    其实这道题就是 coin change 问题，可以先找到自己的coins，即找到小于等于这个数的所有平方数集合，然后就是用最少的coins来凑target了
    这题跟322零钱兑换是一样的，只不过coin[j]换成了j*j
     */
    public int numSquares(int n) {
        /*
        dp[i][j] 中的i是下标，表示使用前i个物品填充容量为j的背包
        可以将小于n的平方数放在数组里面，就可以使用二维dp来解决
         */
        for (int i = 1; i * i <= n; i++) {
            for (int j = i * i; j <= n; j++) {

            }
        }

        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {

                for (int k = 0; k * v[i] <= j; k++) {
                    //第二个是 dp[i-1] 不是 i
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - k * v[i]] + k * w[i]);
                }

            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }
    /*
    https://leetcode-cn.com/problems/perfect-squares/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--51/
     */

    //解法1 回溯算法,优化就是加入记忆方法
    public int numSquares1(int n) {
        if (n == 0) {
            return 0;
        }
        int count = Integer.MAX_VALUE;
        for (int i = 1; i * i <= n; i++) {
            count = Math.min(count, numSquares1(n - i * i) + 1);
        }
        return count;
    }

    //dp解法
    public int numSquaresdp(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        /*
        必须从1开始，因为dp[0]已经初始化为0
        如果从0开始，等于又对dp[0]进行求解，dp[0]=Math.min(dp[0],dp[0]+1),Integer.MAX_VALUE+1就是负的，将dp[0]的0进行了改变
         */
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

    //bfs
    public int numSquaresbfs(int n) {
        Queue<Integer>queue=new ArrayDeque<>();
        Set<Integer>visit=new HashSet<>();
        queue.add(n);
        int level=0;
        while (!queue.isEmpty()){
            int size=queue.size();
            level++;
            for (int i = 0; i <size ; i++) {
                int cur=queue.poll();
                for (int j = 1; j *j<=cur ; j++) {
                    int next=cur-j*j;
                    if(next==0){
                        return level;
                    }
                    if(!visit.contains(next)){
                        queue.add(next);
                        visit.add(next);
                    }
                }
            }
        }
        return -1;
    }
}

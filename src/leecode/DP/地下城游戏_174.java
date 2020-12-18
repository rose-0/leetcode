package leecode.DP;

public class 地下城游戏_174 {
    public int calculateMinimumHP(int[][] dungeon) {
        //https://leetcode-cn.com/problems/dungeon-game/solution/dong-tai-gui-hua-by-wisemove-2-7/
        int m=dungeon.length;
        int n=dungeon[0].length;
        /*
        dp是倒序的：倒序的含义是“从（i，j）出发，到达终点需要最少的血量”。
        因为有“加血”的过程，只能依赖后面的值判断需要的血量。所以倒序
        正序的含义为“从起点出发，到达位置（i，j）所需要的最少血量”，最小路径和为什么是正序的？
        因为【最小路径和】是无状态的，你会发现【最小路径和】倒序dp也是可以的，
         */
        int[][]dp=new int[m][n];

        //保证最低血为1
        dp[m-1][n-1]=Math.max(1,1-dungeon[m-1][n-1]);//最后一点存活的血量：到达最后一点要有1滴血-改点怪物，保证消耗完后还有1滴

        for (int i = m-2; i >=0 ; i--) {
            int temp=dp[i+1][n-1]-dungeon[i][n-1];//邻点 - 该点的血量
            dp[i][n-1]=Math.max(temp,1);//保证血量最少为1
        }
        for(int i = n - 2; i >= 0; i--){
            dp[m-1][i] = Math.max(1, dp[m-1][i+1] - dungeon[m-1][i]);
        }

        for(int i = m - 2; i >= 0; i--){
            for(int j = n - 2; j >= 0; j--){
                int dpmin = Math.min(dp[i+1][j], dp[i][j+1]);
                dp[i][j] = Math.max(1, dpmin - dungeon[i][j]);
            }
        }
        return dp[0][0];
    }
}

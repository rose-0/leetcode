package acwing.背包问题;

import java.util.Scanner;

public class 背包问题01_2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();//物品的数量
        int m = sc.nextInt();//背包的容积
        int[] v = new int[n + 1];
        int[] w = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            v[i] = sc.nextInt();
            w[i] = sc.nextInt();
        }
        System.out.println(dpMethod(m, n, v, w));
    }

    public static int dpMethod(int m, int n, int[] v, int[] w) {
        /*
        v[i]是体积  w[i]是价值
         */
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = dp[i - 1][j];
                /*
                是 >= 不是 >
                dp[i - 1][j - v[i]] + w[i] 而不是 dp[i][j - w[i]] + v[i]
                 */
                if (j >= v[i]) {
                    //也可以写成：dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - v[i]] + w[i]);
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - v[i]] + w[i]);
                }
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }

    /*
    https://blog.csdn.net/qie_wei/article/details/81320229
    dp[i]的状态只和 dp[i-1]有关，所以可以使用一维的数组求解

    dp[i - 1][j - v[i]]              dp[i - 1][j]
                          dp[i-x][j] dp[i][j]      dp[i+x][j]
    计算dp[i][j]时候依赖的相对位置，计算完  dp[i][j] 后，就会将 dp[i - 1][j] 覆盖为 dp[i][j]
    如果循环第二层的时候， 从小到大计算，那么接下来计算 dp[i+x][j] 如果依赖dp[i - 1][j]，但是dp[i - 1][j]已经被覆盖
                       从大到小计算，那么接下来计算 dp[i-x][j] 不会用到 dp[i - 1][j]，所以依然可以得到正确的结果

    第一层，我们知道dp[i]依赖于dp[i-1]所以从小到大循环

     */
    public static int dpMethod2(int m, int n, int[] v, int[] w) {
        int[]dp=new int[m+1];
        for (int i = 1; i <=n ; i++) {
            for (int j = m; j >=v[i] ; j--) {
                // dp[j] 而不是 dp[i]
                dp[j]=Math.max(dp[j],dp[j-v[i]]+w[i]);
            }
        }
        return dp[m];
    }
}

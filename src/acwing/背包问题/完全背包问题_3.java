package acwing.背包问题;

import java.util.Scanner;

public class 完全背包问题_3 {
    //题解 https://www.acwing.com/solution/content/5345/
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

    //最简单的思路：三个循环
    public static int dpMethod(int m, int n, int[] v, int[] w) {
        int[][]dp=new int[n+1][m+1];
        for (int i = 1; i <dp.length ; i++) {
            for (int j = 0; j <dp[0].length ; j++) {

                for (int k = 0; k*v[i] <= j ; k++) {
                    //第二个是 dp[i-1] 不是 i
                    dp[i][j]=Math.max(dp[i][j],dp[i-1][j-k*v[i]]+k*w[i]);
                }

            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }

    /*
    优化的思路：去掉第三个循环
                             k=0         k=1            k=2
    dp[i][j]=Math.max（dp[i-1][j],dp[i-1][j-v]+w,dp[i-1][j-2v]+2w,...）
    将 j 变成 j-v
  dp[i][j-v]=Math.max（           dp[i-1][j-v],  dp[i-1][j-2v]+w,...)

  所以 dp[i][j]=Math.max(dp[i-1][j],dp[i][j-v]+w)
     */
    public static int dpMethod2(int m, int n, int[] v, int[] w) {
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = dp[i - 1][j];
                if (v[i] <= j) {
                    //第二个是 dp[i] 不是 i-1,和上面有循环时候不同
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - v[i]] + w[i]);
                    /*
                    这里写成：dp[i][j] = Math.max(dp[i-1][j], dp[i][j - v[i]] + w[i]);
                    代码也可以通过
                     */
                }
                /*
                上面对比 01背包：
                dp[i][j] = dp[i - 1][j];

                if ( v[i] <= j) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - v[i]] + w[i]);
                }
                 */
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }
    /*
    参考01背包优化为一维数组
                         dp[i-1][j]
      dp[i][j-v]         dp[i][j]
      dp[i][j]需要依赖dp[i][j-v]，所以要先把dp[i][j-v]计算出来，即第二层循环从小到大
     */
    public static int dpMethod3(int m, int n, int[] v, int[] w) {
        int[] dp = new int[m + 1];
        for (int i = 1; i < dp.length; i++) {
            for (int j = v[i]; j <= m; j++) {
                dp[j]=Math.max(dp[j],dp[j-v[i]]+w[i]);
            }
            /*
            for(int j = 1; j <= n; j ++)
            {
                 if(i < v[j]) continue;
                 f[i] = max(f[i], f[i - v[j]] + w[j]);
            }
             */
        }
        return dp[m];
    }
}
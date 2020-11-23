package acwing.背包问题;

import java.util.Scanner;

public class 多重背包问题I_4 {
    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();//物品的数量
        int m = sc.nextInt();//背包的容积
        int[] v = new int[n + 1];
        int[] w = new int[n + 1];
        int[] s = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            v[i] = sc.nextInt();
            w[i] = sc.nextInt();
            s[i] = sc.nextInt();
        }
        System.out.println(dpMethod(m, n, v, w,s));
    }

    /*
    使用完全背包的解法进行求解
     */
    public static int dpMethod(int m, int n, int[] v, int[] w,int[] s) {
        int[][]dp=new int[n+1][m+1];
        for(int i = 1; i < dp.length; i ++){
            for(int j = 0; j < dp[0].length; j ++){//枚举体积
                for(int k = 0; k <= s[i]; k ++){//k一定要从0开始
                    if(j >=  k * v[i]){
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - k * v[i]] + k * w[i]);
                    }
                }
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }
    /*
    //https://www.acwing.com/solution/content/9434/
    多重背包问题不能使用完全背包问题的优化递推，假设第i个物品只能选1个，
                                 k=0         k=1            k=2（只能选一个）
    dp[i][j]=Math.max（dp[i-1][j],dp[i-1][j-v]+w）
    将 j 变成 j-v                                  j>2v,j变成j-v后，所以有这一项
  dp[i][j-v]=Math.max（           dp[i-1][j-v],  dp[i-1][j-2v]+w,...)

  所以不能使用 dp[i][j]=Math.max(dp[i-1][j],dp[i][j-v]+w)，dp[i][j-v]多一项！！

     */

    /*
    转化为01背包问题：比如物品1有3件，每件价值为2，我们不妨创建3个物品1，存在数组v和数组w中
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();//物品的数量
        int m = sc.nextInt();//背包的容积
        int[] v = new int[n + 1];
        int[] w = new int[n + 1];
        int[] s = new int[n + 1];
        int index=1;
        for (int i = 1; i <= n; i++) {
            int cur_v=sc.nextInt();
            int cur_w=sc.nextInt();
            int cur_s=sc.nextInt();
            while (cur_s>0){
                v[index]=cur_v;
                w[index]=cur_w;
                index++;
            }
        }
        //调用 01 背包
        System.out.println(dpMethod(m, n, v, w,s));
    }
}

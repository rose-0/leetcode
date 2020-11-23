package acwing.背包问题;

import java.util.Scanner;

public class 混合背包问题_7 {
    /*
    01背包    dp(i,j)=max[dp(i-1,j),dp(i-1,j-v)+w]
    完全背包   dp(i,j)=max[dp(i-1,j),dp(i,j-v)+w]
    多重背包   dp(i,j)=max[dp(i-1,j),dp(i,j-v)+w,dp(i,j-2v)+2w,...,dp(i,j-kv)+kw]
     */
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 物品个数
        int V = sc.nextInt(); // 背包总容量
        int[] dp = new int[V + 1];
        for(int i = 0; i < N; i++){
            int v = sc.nextInt(); // 体积
            int w = sc.nextInt(); // 价值
            int s = sc.nextInt(); // 数量
            if(s == 0){
                // 完全背包问题
                for(int j = v; j <= V; j++){//从小到大
                    dp[j] = Math.max(dp[j], dp[j - v] + w);
                }
            }else{
                // 多重背包问题，01背包是多重背包的特例，即s=1，可以一并处理
                s = Math.abs(s);
                /*
                s该物品的数量 v该物品的体积 V背包的总体积
                j=j*2是对s进行二进制拆分，假设 s=8 8=1+2+4+1,这四个数可以凑成0-8的所有数字
                j=1,dp[k]表示对j选或者不选
                j=2,dp[k]表示对j选或者不选
                 */
                for (int j = 1; j <= s; j = j*2) {
                    for(int k = V; k >= j * v; k--){
                        dp[k] = Math.max(dp[k], dp[k - j * v] + j * w);
                    }
                    s=s-j;
                }
                if(s > 0){
                    for(int k = V; k >= s * v; k--){
                        dp[k] = Math.max(dp[k], dp[k - s * v] + s * w);
                    }
                }
            }
        }
        System.out.println(dp[V]);
    }
    /*
    或者直接对输入进行二进制优化 https://www.acwing.com/solution/content/12711/
     */

}

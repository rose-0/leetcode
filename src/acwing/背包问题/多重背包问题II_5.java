package acwing.背包问题;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 多重背包问题II_5 {
    /*
    public static void main(String[] args) {
            int[]v=new int[11];
            int[]w=new int[11];
            int cnt = 0;
            int a = 3;
            int b = 4;
            int s = 17;
            int k = 1;
            while(k <= s){
                cnt++;
                v[cnt] = a * k;
                w[cnt] = b * k;
                s -= k;
                k *= 2;
            }
            if(s > 0){

                cnt++;
                System.out.println(cnt);
                v[cnt] = a * s;
                w[cnt] = b * s;
            }
        for (int i = 0; i <v.length; i++) {
            System.out.print(v[i]+" ");
        }
        System.out.println();
        for (int i = 0; i <w.length ; i++) {
            System.out.print(w[i]+" ");
        }
    }
     */
    static BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception{
        String[] ss = read.readLine().split(" ");
        int n = Integer.valueOf(ss[0]);
        int m = Integer.valueOf(ss[1]);
        //物品个数S == 2000, logS = 11
        int[] v = new int[n * 11];
        int[] w = new int[n * 11];
        int cnt = 0;
        for(int i = 1; i <= n; i++){
            ss = read.readLine().split(" ");
            int a = Integer.valueOf(ss[0]);
            int b = Integer.valueOf(ss[1]);
            int s = Integer.valueOf(ss[2]);
            /*
            a = 3; b = 4; s = 17;
            表示这个物品有17个，体积为3，价值为4
            17 = 1 + 2 + 4 + 8 + 2
            把17分成五份，分别有 1个，2个，4个，8个，2个，乘以对应的体积和价值，填到数组中
        下标   0  1    2   3
        v[i]    3*1  3*2
             */
            int k = 1;
            while(k <= s){
                cnt++;
                v[cnt] = a * k;
                w[cnt] = b * k;
                s -= k;
                k *= 2;
            }
            if(s > 0){
                cnt++;
                v[cnt] = a * s;
                w[cnt] = b * s;
            }
        }

        n = cnt;
        int[] dp = new int[m + 1];
        for(int i = 1; i <= n; i++){
            for(int j = m; j >= v[i]; j--){
                dp[j] = Math.max(dp[j], dp[j - v[i]] + w[i]);
            }
        }

        System.out.println(dp[m]);
    }
}

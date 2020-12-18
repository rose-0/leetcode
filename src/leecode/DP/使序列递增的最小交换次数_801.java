package leecode.DP;

import java.util.Arrays;

public class 使序列递增的最小交换次数_801 {
    /*
    理解题意很重要：
    https://leetcode-cn.com/problems/minimum-swaps-to-make-sequences-increasing/solution/leetcode-801-wo-gan-jio-ying-gai-jiang-de-hen-tou-/
     */
    /*
    可以看dfs的解法
    https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-801-minimum-swaps-to-make-sequences-increasing/
     */
    public int minSwap(int[] A, int[] B) {
        int len=A.length;
        //
        int[]keep=new int[len];
        //
        int[]swap=new int[len];
        Arrays.fill(keep,Integer.MAX_VALUE);
        Arrays.fill(keep,Integer.MAX_VALUE);

        keep[0]=0;
        swap[0]=1;
        for (int i = 1; i <len ; i++) {
            /*
            如果满足两种情况
            i 交换的情况下，可以有 i - 1 不交换 和 i - 1 交换，选择最优情况
            i 不交换的情况下，可以有 i - 1 交换和 i - 1 不交换，选择最优情况
             */
            if((A[i]>A[i-1]&&B[i]>B[i-1])&&
                    (A[i]>B[i-1]&&B[i]>A[i-1])){
                //
                keep[i]=Math.min(keep[i-1],swap[i-1]);
                //
                swap[i]=Math.min(keep[i-1],swap[i-1])+1;

                continue;
            }
            /*

             */
            if(A[i]>A[i-1]&&B[i]>B[i-1]){
                //i不交换
                keep[i]=keep[i-1];
                //i 交换，那么意味着 i - 1 也交换
                swap[i]=swap[i-1]+1;
            }
            /*

             */
            if(A[i]>B[i-1]&&B[i]>A[i-1]){
                //i 不交换，那么就是交换 i - 1
                keep[i] = swap[i - 1];
                //i 交换，那么就是 i - 1 不交换
                swap[i] = keep[i - 1] + 1;
            }
        }
        return Math.min(keep[len-1],swap[len-1]);
    }
}

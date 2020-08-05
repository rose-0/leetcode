package leecode.DP;

import zuoshen.输入输出练习.I;

public class 买卖股票的最佳时机 {
    public int maxProfit(int[] prices) {
        if(prices==null||prices.length==0){
            return 0;
        }
        int min= Integer.MAX_VALUE;
        int max=Integer.MIN_VALUE;
        for (int i = 0; i <prices.length ; i++) {
            min=Math.min(min,prices[i]);
            max=Math.max(max,prices[i]-min);//当前价格- [0..i]最小值
            /*这个是错的 [7,1,5,3,6,4]   max为7   7-1=6；不正确； 最大利润=当前价格-前面最小价格！！
            max=Math.max(max,prices[i]);    // [0..i]上最大值   可能是中间一个值
            profit=Math.max(profit,max-prices[i]);  最大值减去当前值！
             */
        }
        return max;
    }
}

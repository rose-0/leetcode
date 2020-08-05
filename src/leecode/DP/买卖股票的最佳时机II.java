package leecode.DP;
//https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/solution/best-time-to-buy-and-sell-stock-ii-zhuan-hua-fa-ji/
//贪心解析
public class 买卖股票的最佳时机II {
    public static int maxProfit(int[] prices) {

        if(prices==null||prices.length==0){
            return 0;
        }
        int temp=-1;
        int res=0;
        //解决 1 2 4 ：第一天买，最后一天卖！也是两天之间的差值和！
        for (int i = 0; i <prices.length-1 ; i++) {
            temp=prices[i+1]-prices[i];
            res+=temp>0?temp:0; //sum=sum+temp>0?temp:0;不对，要加括号 sum=sum+(temp>0?temp:0);
        }
        return res;


    }

    public static void main(String[] args) {
        int[]num={1,2,4};
        int[]num1={7,1,5,3,6,4};
        System.out.println(maxProfit(num1));
    }
}

package leecode.DP;
/*
dp[i][k][0 or 1]
i表示天数，k表示允许最大交易次数 0/1代表是否有股票

dp[-1][k][0]=0;
i=-1 意味着还没有开始 利润为0
dp[-1][k][1]=负无穷
还没有开始的时候是不可能持有股票的 所以设置为负无穷表示不可能

dp[i][0][0]=0;
k=0 意味着不允许交易 利润为0
dp[i][0][1]=负无穷
k=1 不允许交易情况下 不可能持有股票 用负无穷表示不可能

根据状态方程化简basecase
dp[0][k][0]=Math.max(dp[-1][k][0],prices[0]+dp[-1][k][1])=0
dp[0][k][1]=Math.max(dp[-1][k][1],-prices[0]+dp[-1][k-1][0])=-price[0]//这个不是0！！
 */
public class 买卖股票的最佳时机III {
    //base case 为i=-1，意味着还没有开始 k=0 意味着不允许交易
    public int maxProfit(int[] prices) {
        if(prices==null||prices.length==0){
            return 0;
        }
        int[][][]dp=new int[prices.length][3][2];
        for (int i = 0; i <prices.length ; i++) {
            for (int k = 2; k >=1 ; k--) {//k==2意味着允许交易两次，k为0意味着不允许交易
                if(i==0){
                    /*
                    不处理k的basecase
                    k的base有两个：dp[i][0][0]=0;   dp[i][0][1]=负无穷
                    下面代码只用到了dp[i][0][0]=0;没有用到dp[i][0][1]
                     */
                    dp[0][k][0]=0;
                    dp[0][k][1]=-prices[i];
                    continue;
                }
                //第i天不持有股票的状态由第i-1天不持有股票，或者持有卖出（卖出k不变，买入才变）转化而来
                dp[i][k][0]=Math.max(dp[i-1][k][0],prices[i]+dp[i-1][k][1]);
                dp[i][k][1]=Math.max(dp[i-1][k][1],-prices[i]+dp[i-1][k-1][0]);
            }
        }
        return dp[prices.length-1][2][0];
    }

    /*
     *
     * @param prices
     * @param i 当前考虑第几天
     * @param hasStock 是否有股票在手
     * @param counts 已经交易的次数（每买一次股票就加一）
     * @return
    private int f(int[] prices, int i, int hasStock, int counts) {
        // 如果已经买了两次股票，并且手里已经没有股票了，那么后面的天数不需要考虑
        if(i >= prices.length || (counts >= 2 && hasStock < 1))
            return 0;
        // 如果手里有股票，我可以选择卖或者不卖
        if(hasStock > 0)
            return  Math.max(prices[i] + f(prices, i+1, 0, counts), f(prices, i+1, 1, counts));
        // 如果没有股票，我可以选择买或者不买
        return Math.max(-prices[i] + f(prices, i+1, 1, counts+1), f(prices, i+1, 0, counts));
    }
     */
    public static int baoli(int[] prices,int i,int count,int hasStock){
        if(i>=prices.length||count>=2&&hasStock<1){
            return 0;
        }
        if(hasStock>0){
            return Math.max(prices[i]+baoli(prices,i+1,count,0),baoli(prices,i+1,count,1));
        }
        return Math.max(-prices[i]+baoli(prices,i+1,count+1,1),baoli(prices,i+1,count,0));
    }
    /*
    public int maxProfit(int[] prices) {
        int m = prices.length;
        int[][][] dp = new int[m+1][2][3];
        for(int i = m-1; i >= 0; i--) {//i代表数组下标，第几天
            for(int j = 1; j >= 0; j--) {//j代表有无股票，0无，1有
                for(int k = 2; k >= 0; k--) {//k代表买入次数
                    if(k == 2 && j == 0)
                        continue;
                    if(j > 0)//还持有股票
                        dp[i][j][k] = Math.max(prices[i] + dp[i+1][0][k],
                                dp[i+1][1][k]);
                    else
                        dp[i][j][k] = Math.max(-prices[i] + dp[i+1][1][k+1],
                                dp[i+1][0][k]);
                }
            }
        }
        return dp[0][0][0];
    }
     */
    public static int dp_method(){
        return 0;
    }
}

package leecode.DP;

public class 爬楼梯_70 {
    public int climbStairs(int n) {
        if(n<1){
            return 0;
        }
        if(n==1){
            return 1;
        }
        if(n==2){
            return 2;
        }
        int[]dp=new int[n];
        dp[0]=1;
        dp[1]=2;
        for (int i = 2; i <n ; i++) {
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[n-1];
    }
    public int climbStairs2(int n) {

        int i1=1;
        int i2=2;//2个台阶
        /*
         i1  i2  temp(i1+i2)
         */
        for (int i = 3; i <n ; i++) {
            int temp=i1+i2;
            i1=i2;
            i2=temp;
        }
        return i2;
    }
}

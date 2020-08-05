package leecode.DP;

public class 打家劫舍II_213 {
    //labuladong
    /*
    循环数组打劫分三种情况[0...nums.length-1]
    1 不打劫首尾[1..nums.length-2]
    2 不打劫首[1..nums.length-1]
    3 不打劫尾[0..nums.length-2]
    2，3肯定比1大，所以求出2和3选最大即可
     */
    public static int rob(int[] nums) {
        if(nums.length==1){
            return nums[0];
        }
        //求两个
        return Math.max(dp(nums,0,nums.length-2),
                dp(nums,1,nums.length-1));
    }
    //计算闭区间[start,end]的结果,和lee198不同的是要传入start，end
    public static int dp(int[]nums,int start,int end){
        int[]dp=new int[end+3];//长度为end+3的原因是 i=end dp[i+2]=dp[end+2],保证不会越界，长度声明为end+2会越界的！！
        // 如果长度为[end-start+1+2]则会越界
        for (int i = end; i >=start ; i--) {//由 end 到 start 保证和nums的下标保持一致
            dp[i]=Math.max(dp[i+1],dp[i+2]+nums[i]);
        }
        return dp[start];//返回[start]不是0
    }

    public int dp_youhua(int[]nums,int start,int end){
        int dp_i=0;//dp[i]
        int dp_i_1=0;//dp[i+1]
        int dp_i_2=0;//dp[i+2]
        for (int i = end; i >=start ; i--) {
            dp_i=Math.max(dp_i_1,dp_i_2+nums[i]);
            dp_i_2=dp_i_1;
            dp_i_1=dp_i;
        }
        return dp_i;
    }

    public static void main(String[] args) {
        int[]num=new int[]{2,3,2};
        System.out.println(rob(num));
    }
}

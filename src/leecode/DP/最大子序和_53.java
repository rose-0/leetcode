package leecode.DP;
//自己写个例子就有思路了
public class 最大子序和_53 {
    public int maxSubArray(int[] nums) {
        if(nums==null){
            return 0;
        }
        int[]dp=new int[nums.length];
        dp[0]=nums[0];
        int max=Integer.MIN_VALUE;
        for (int i = 1; i <nums.length ; i++) {
            dp[i]=Math.max(nums[i],dp[i-1]+nums[i]);
            max=Math.max(dp[i],max);
        }
        return max;
    }
}

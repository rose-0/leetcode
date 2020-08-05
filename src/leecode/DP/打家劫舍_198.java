package leecode.DP;

import java.util.Arrays;

//一维dp:从后向前填充数组
//边界条件的处理很重要 n>2时 dp[i]表示后面的最大值，
// 所以倒数第二位取后两位的最大值，或者多申请一个空间
//就不再需要对边界进行单独处理每个元素都是两者取值最大
public class 打家劫舍_198 {
    private static int[]memo;//备忘录
    public static int rob(int[] nums) {
        memo = new int[nums.length];//初始化备忘录
        Arrays.fill(memo,-1);//初始化为-1
         return method_dp2(nums);
    }
    //labuladong
    //纯递归
    public static int baoli(int[]nums,int start){
        if(start>=nums.length){
            return 0;
        }
        int res=Math.max(baoli(nums,start+1), //不抢start，从start+1开始抢
                baoli(nums,start+2)+nums[start]); //抢start，接下来只能从start+2开始抢
        return res;
    }

    //备忘录 memo
    public static int rob_with_memo(int[]nums,int start){
        if(start>=nums.length){
            return 0;
        }
        if(memo[start]!=-1){
            return memo[start];
        }
        int res=Math.max(rob_with_memo(nums,start+1),
                rob_with_memo(nums,start+2)+nums[start]);
        memo[start]=res;
        return res;
    }
    //dp 自底向上，这个题先算后面，再算前面
    public static int rob_dp(int[]nums,int start){
        int[]dp=new int[nums.length+2];//dp[i]=x 表示从第i间房子开始，最多可以抢到的钱为x
        //状态方程：dp[i]= Math.max{dp[i+1],dp[i+2]+nums[i]} 对于i选择抢或不抢
        // dp[nums.length-1]=Math.max{dp[nums.length],dp[nums.length+1]+nums[nums.length-1]}
        // 保证不越界 使得长度为nums.length+2
        for (int i = nums.length-1; i >=0 ; i--) {
            dp[i]=Math.max(dp[i+1],dp[i+2]+nums[i]);
        }
        return dp[0];
    }
    //优化dp 从上面可以看出，每个值只和两个值有关,所以使用两个变量即可
    public static int rob_dp_youhua(int[]nums){
        int dp_i_1=0;//dp[i+1]
        int dp_i_2=0;//dp[i+2]
        int dp_i=0;//dp[i]
        for (int i = nums.length-1; i >=0 ; i--) {
            dp_i=Math.max(dp_i_1,nums[i]+dp_i_2);
            dp_i_2=dp_i_1;
            dp_i_1=dp_i;
        }
        return dp_i;
    }

    public static int method(int[]nums,int index,int length){
        if(index==length-1){
            return nums[index];
        }
        if(index==length-2){
            return nums[index];
        }
        int max=0;
        for (int i = 0; i <length-2 ; i++) {
            max=Math.max(max,nums[i]+method(nums,i+2,length));
        }
        return max;
    }
    public  static int method_dp2(int[]num){
        int[]dp=new int[num.length+1];
        int length=dp.length;
        int n=num.length;
        dp[length-2]=num[n-1];
        dp[length-3]=num[n-2];
        for (int i = dp.length-4; i >=0 ; i--) {
            dp[i]=Math.max(dp[i+1],dp[i+2]+num[i]);
        }
        return dp[0];
    }
    public static void main(String[] args) {
        int[]nums={1,2,3,1};

        System.out.println(rob(nums));
        System.out.println(method_dp2(nums));
    }

}

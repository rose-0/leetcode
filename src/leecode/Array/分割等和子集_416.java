package leecode.Array;
//背包问题
// labuladong的解析  https://leetcode-cn.com/problems/partition-equal-subset-sum/solution/0-1-bei-bao-wen-ti-bian-ti-zhi-zi-ji-fen-ge-by-lab/
// liweiwei的解析 https://leetcode-cn.com/problems/partition-equal-subset-sum/solution/0-1-bei-bao-wen-ti-xiang-jie-zhen-dui-ben-ti-de-yo/
//dp[0][0]为true的讨论，liweiwei写了两张情况，
//dp[0][0]为false，需要专门讨论j==nums[i]时的情况
public class 分割等和子集_416 {
    public boolean canPartition(int[] nums) {
        int sum=0;
        for (int i = 0; i <nums.length ; i++) {
            sum+=nums[i];
        }
        sum=sum/2;
        boolean[][]dp=new boolean[nums.length][sum+1];
        for (int i = 0; i <dp[0].length ; i++) {
            if(i==nums[0]){
                dp[0][i]=true;
            }
        }
        for (int i = 1; i <dp.length ; i++) {
            for (int j = 0; j <dp[0].length; j++) {
                if(j-nums[i]<0){
                    dp[i][j]=dp[i-1][j];
                }else {
                    dp[i][j]=dp[i-1][j]|dp[i-1][j-nums[i]];
                }
            }
        }
        return dp[nums.length-1][sum];
    }

    public boolean dfs(int[]nums,int start,int remain){
        if(remain==0){
            return true;
        }
        if(start==nums.length){
            return false;
        }
        if(nums[start]==remain){//当前数 = 目标数
            return true;
        }
        if(nums[start]<remain){//当前数 < 目标数
            //选或者不选
            return dfs(nums,start+1,remain-nums[start])||dfs(nums,start,remain);
        } else  { // 当前数 > 目标数
            return dfs(nums,start+1,remain);
        }
    }
}

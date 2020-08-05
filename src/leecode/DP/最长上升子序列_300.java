package leecode.DP;

public class 最长上升子序列_300 {
    public static int lengthOfLIS(int[] nums) {
        int[]dp=new int[nums.length];
        for (int i = 0; i <dp.length ; i++) {
            dp[i]=1;//一定要初始化为1！！！
        }
        int max=0;
        for (int i = 0; i <dp.length ; i++) {
            for (int j = 0; j <i ; j++) {
                if(nums[j]<nums[i]){
                    dp[i]=Math.max(dp[i],dp[j]+1);//最后一个值不一定是整个数组最大的
                }
            }
            max=Math.max(max,dp[i]);//max一定要放在第二个循环的外面！！
        }
        for (int i = 0; i <dp.length ; i++) {
            System.out.print(dp[i]+" ");
        }
        System.out.println();
        huanyaun(dp,nums);
        return dp[nums.length-1];
    }
    public static int[] huanyaun(int[]dp,int[]num){
        int max=0;
        int index=0;
        for (int i = 0; i <dp.length ; i++) {
            if(dp[i]>max){
                max=dp[i];
                index=i;
            }
        }
        System.out.println("index="+index);
        int[]lis=new int[max];
        lis[--max]=num[index];
        for (int i = index-1; i >=0 ; i--) {
            if(num[i]<num[index]&&dp[i]==dp[index]-1){
                lis[--max]=num[i];
                index=i;
            }
        }
        System.out.println("---");
        for (int i = 0; i <lis.length ; i++) {
            System.out.print(lis[i]+" ");
        }
        System.out.println("---");
        return lis;
    }

    public static int method_dp(int[]nums){
        int[]dp=new int[nums.length];
        int max=0;
        int res=0;
        for (int i = 0; i <nums.length ; i++) {
            dp[i]=1;
            for (int j = 0; j <i ; j++) {
                if(nums[j]<nums[i]){
                    res=Math.max(dp[j],res);
                }
            }
            dp[i]=res+1;
            max=Math.max(dp[i],max);
        }
        return max;
    }

    public static void main(String[] args) {
        int[]num={1,5,2,3,6,4,1};
        System.out.println(lengthOfLIS(num));
//        System.out.println(method_dp(num));
    }
}

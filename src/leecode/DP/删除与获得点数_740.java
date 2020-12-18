package leecode.DP;

public class 删除与获得点数_740 {
    /*
    对比打家劫舍：打劫了一间房子，则左右相邻的房子不能取。
    本题：若取一个数，则左右相邻的数(绝对值差1，而不是位置相邻)不能取，是不是很相似，却又有那么点不一样，
    因为本题中“本该相邻的房子”并不相邻， 即数字是乱序的；另外，存在“同样的房子”，即有相同的数字。
    https://leetcode-cn.com/problems/delete-and-earn/solution/ru-guo-ni-li-jie-liao-da-jia-jie-she-zhe-ti-ni-ken/
    将数组转换后就变成来打家劫舍问题
     */
    public int deleteAndEarn(int[] nums) {
        int max=0;
        for (int i = 0; i <nums.length ; i++) {
            max=Math.max(max,nums[i]);
        }
        int[]help=new int[max+1];
        for (int i = 0; i <nums.length ; i++) {
            help[nums[i]]++;
        }
        int[]dp=new int[max+1];
        dp[1]=help[1]*1;
        for (int i = 2; i <dp.length ; i++) {
            dp[i]=Math.max(help[i]*i+dp[i-2],dp[i-1]);
        }
        return dp[max];
    }
}

package leecode.DP;

public class 乘积最大的子序列_152 {
    public static int maxProduct(int[] nums) {
        int res=nums[0];
        int max=nums[0];//i位置前面累乘最大值
        int min=nums[0];//i位置前面累乘最小值
        int maxend=0;
        int minend=0;
        for (int i = 1; i <nums.length ; i++) {//从1开始
            maxend=max*nums[i];
            minend=min*nums[i];
            max=Math.max(Math.max(maxend,minend),nums[i]);//更新i位置的max，min
            min=Math.min(Math.min(maxend,minend),nums[i]);
            res=Math.max(res,max);
        }
        return res;
    }
    public static int method_dp(int[]num){
        int max=1;
        int min=1;
        int maxend=1;
        int minend=1;
        int x=0;
        for (int i = 0; i <num.length; i++) {
            max=num[i]*maxend;
            min=num[i]*minend;
            maxend=Math.max(num[i],Math.max(max,min));
            minend=Math.min(num[i],Math.min(max,min));
            x=Math.max(maxend,x);
        }
        return x;
    }

    public static void main(String[] args) {
        int[]num={2,3,-2,4};
        System.out.println(maxProduct(num));
        System.out.println(method_dp(num));
    }
}

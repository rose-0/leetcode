package leecode.Array;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.HashMap;

public class 和为k的子数组_560 {
    //前缀和技巧，labuladong
    public int subarraySum(int[]nums,int k){
        int[]preSum=new int[nums.length+1];//preSum[0]初始化为0
        for (int i = 1; i <nums.length ; i++) {
            //preSum[i]代表nums[0..i-1]的和, 求nums[i..j]的和:preSum[j+1]-preSum[i]
            preSum[i+1]=preSum[i]+nums[i];
        }
        int ans=0;
        //穷举所有的子数组
        for (int i = 1; i <=nums.length ; i++) {
            for (int j = 0; j <i ; j++) {
                //[0..i-1]-[0..j-1]  =  nums[j..i-1]
                if(preSum[i]-preSum[j]==k){//preSum[j]==preSum[i]-k 我只需要记录有几个preSum[j]满足条件即可
                    ans++;
                }
            }
        }
        return ans;
    }

    //优化！
    public int subarraySum_hash(int[]nums,int k){
        //map:前缀和 - 前缀和出现次数
        HashMap<Integer,Integer>preSum=new HashMap<>();
        //base case
        preSum.put(0,1);
        int ans=0, sum0_i=0;//sum0_i 表示[0,i]的和
        for (int i = 0; i <nums.length ; i++) {
            sum0_i=sum0_i+nums[i];
            //和nums[0..j]
            int sum0_j=sum0_i-k;

            if(preSum.containsKey(sum0_j)){
                ans=ans+preSum.get(sum0_j);
            }

            preSum.put(sum0_i,preSum.getOrDefault(sum0_i,0)+1);
        }
        return ans;
    }
}

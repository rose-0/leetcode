package leecode.other;

public class 寻找数组的中心索引_724 {
    public int pivotIndex(int[] nums) {
        int sum=0;
        for (int i = 0; i <nums.length ; i++) {
            sum+=nums[i];
        }
        int preSum=0;
        for (int i = 0; i <nums.length ; i++) {
            int cur=nums[i];
            if(preSum*2==sum-cur){//preSum==(sum-cur)/2  就不对了
                return i;
            }
            preSum+=cur;
        }
        return -1;
    }
}

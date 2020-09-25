package leecode.Array;

public class 寻找旋转排序数组中的最小值II_154 {
    //liweiwei 二分 在数组中查找符合条件的元素的下标
    //对比 154
    //有重复元素时，都要把相等的情况单独讨论，比如 81题 vs 33
    public int findMin(int[] nums) {
        if(nums.length==0){
            return 0;
        }
        int left=0;
        int right=nums.length-1;
        while (left<right){
            int mid=(left+right)>>>1;
            if(nums[mid]<nums[right]){
                right=mid;
            }else if(nums[mid]>nums[right]){
                left=mid+1;
            }else {
                /*
                相等时，最小值可能在左边，或者右边
                5 1 2 2 2
                3 4 5 5 5
                去掉了右边界，中间数还在
                 */
                right--;
            }
        }
        return nums[left];
    }
}

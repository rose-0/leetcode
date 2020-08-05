package leecode.Array;
//https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/solution/zai-javazhong-ji-bai-liao-100de-yong-hu-by-reedfan/
public class 搜索旋转排序数组II_81 {
    public boolean search(int[] nums, int target) {
        int left=0;
        int right=nums.length-1;
        while (left<=right){
            int mid=(left+right)/2;
            if(nums[mid]==target){
                return true;
            }
            //相比较33 ，多了这个
            if(nums[left]==nums[mid]){//是left和mid比较
                left++;
                continue;
            }
            if(nums[left]<=nums[mid]){
                if(nums[left]<=target&&target<nums[mid]){
                    right=mid-1;
                }else {
                    left=mid+1;
                }
            }else {
                if(nums[mid]<target&&target<=nums[right]){
                    left=mid+1;
                }else {
                    right=mid-1;
                }
            }
        }
        return false;
    }
}

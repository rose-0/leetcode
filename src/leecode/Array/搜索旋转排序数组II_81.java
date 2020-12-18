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
    //liweiwei  二分 在数组中查找符合条件的元素的下标 对比33
    //这个是数组中会存在重复元素，33是不存在重复元素
    //https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/solution/er-fen-cha-zhao-by-liweiwei1419/
    public boolean search2(int[] nums, int target) {
        if(nums.length==0){
            return false;
        }
        int left=0;
        int right=nums.length-1;
        while (left<right){
            int mid=(left+right)>>>1;
            if(nums[left]<nums[mid]){//存在重复元素，所以这里不能使用nums[left]<=nums[mid]了
                //前半部分有序
                if(nums[left]<=target&&target<=nums[mid]){
                    //[left,mid]
                    right=mid;
                }else {
                    //[mid+1,right]
                    left=mid+1;
                }
            }
            else if(nums[mid]<nums[right]){//后半部分有序

                if(nums[mid+1]<=target&&target<=nums[right]){
                    left=mid+1;//这样写好一些 使用左中位数时候 一定要保证左边界收缩
                }else {
                    right=mid;
                }
            }
            /*
            else if(nums[left]>nums[mid]){
                //前半部分无序
                //nums[left]>nums[mid]
                if(nums[mid]<=target&&target<=nums[right]){
                    left=mid;
                }else {
                    right=mid-1;
                }
            }
            */
            else {
                // nums[left]==nums[mid]
                if(nums[left]==target){
                    return true;
                }
                left=left+1;
            }
        }
        if(nums[left]==target){
            return true;
        }
        return false;
    }
}

package leecode.Array;

public class 搜索插入位置_35 {
    //二分查找_liweiwei模版
    public int searchInsert(int[] nums, int target) {
        int left=0;
        int right=nums.length-1;
        //特殊
        ////将right初始化为len，就不需要判断特例
        if(nums[nums.length-1]<target){
            return nums.length;
        }
        while (left<right){
            int mid=left+(right-left)/2;

            if(nums[mid]<target){//nums[mid]<target  mid肯定不是插入位置
                left=mid+1;
            }else {
                right=mid;
            }
            //区间被分为 [left,mid] [mid+1,right]
            //所以要选择左中位数
        }


        return left;

    }
}

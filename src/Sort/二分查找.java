package Sort;
//https://github.com/labuladong/fucking-algorithm/blob/master/%E7%AE%97%E6%B3%95%E6%80%9D%E7%BB%B4%E7%B3%BB%E5%88%97/%E4%BA%8C%E5%88%86%E6%9F%A5%E6%89%BE%E8%AF%A6%E8%A7%A3.md
//针对二分的框架
public class 二分查找 {
    //最基本的二分查找
    public int binarySearch(int[]nums,int target){
        int left = 0;
        int right = nums.length - 1; // 注意

        while(left <= right) {  //为什么是<=而不是< 因为right初始值为nums.length - 1，每次搜索的区间[left, right]
//                                                 如果right初始值为nums.length，则每次搜索的区间[left, right)
            // left <= right 终止条件是 left == right + 1 写成区间的形式就是 [right + 1, right] 区间为空终止
            // left < right 终止条件是 left == right 写成区间的形式就是 [right, right] 区间非空就终止了，所以不对
            int mid = left + (right - left) / 2;
            if(nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                //为什么不是left = mid，明确搜索区间[left, right],
                // 所以当mid!=target时候，应该将mid从搜索区间移除，搜索[left, mid-1]或[mid+1, right]
                left = mid + 1; // 注意
            else if (nums[mid] > target)
                right = mid - 1; // 注意
        }
        return -1;
    }

    //寻找左侧边界的二分搜索
    public int find_left(int[]nums,int target){
        int left=0;
        int right=nums.length-1;
        while (left<=right){
            int mid=left+(right-left)/2;
            if(nums[mid]==target){
                right=mid-1;//锁定左边界
            }
            else if(nums[mid]<target){
                left=mid+1;
            }
            else if(nums[mid]>target){
                right=mid-1;
            }
        }
        //while退出的条件是left=right+1,当target大于所有元素时，left会越界
        if(left>=nums.length||nums[left]!=target){
            return -1;
        }
        return left;//如果num[mid]==target,right=mid-1，而targrt只有一个，所以应该返回right+1，即left
    }
    //寻找右侧边界的二分搜索
    public int right_bond(int[]nums,int target){
        int left=0;
        int right=nums.length-1;
        while (left<=right){
            int mid=left+(right-left)/2;
            if(nums[mid]==target){
                left=mid+1;
            }
            else if(nums[mid]<target){
                left=mid+1;
            }
            else if(nums[mid]>target){
                right=mid-1;
            }
        }
        if(right<0||nums[right]!=target){
            return -1;
        }
        return right;
    }
}

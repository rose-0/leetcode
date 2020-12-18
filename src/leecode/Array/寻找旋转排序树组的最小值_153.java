package leecode.Array;

import java.util.ArrayList;
import java.util.List;

public class 寻找旋转排序树组的最小值_153 {
    public static int findMin(int[] nums) {
        int start=0;
        int end=nums.length-1;
        while (start<=end){
            if(nums[start]<=nums[end]){//数组没有旋转
                return nums[start];
            }
            int mid=(start+end)/2;
            if(nums[start]<=nums[mid]){//[start,mid]连续递增，则最小值在[mid+1,end]
                start=mid+1;
            }else {
                end=mid;//不是mid-1  nums[start]>nums[mid] 最小值在[start,mid]
            }
        }
        return -1;
    }

    //liweiwei 二分 在数组中查找符合条件的元素的下标
    public int findMin2(int[] nums) {
        int left=0;
        int right=nums.length-1;
        while (left<right){
            int mid=(left+right)>>>2;//>>>1 不是 >>>2  !!!
            //找统一的判断方法，需要自己推论
            //https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/solution/er-fen-fa-fen-zhi-fa-python-dai-ma-java-dai-ma-by-/
            //统一规律
            /*
            1 2 3 4 5
            列出如下情况：移动1的开始位置

            1 2 3 4 5
            5 1 2 3 4
            4 5 1 2 3
            3 4 5 1 2
            2 3 4 5 1

            中间>左边 min可能在左边或者右边
            中间<左边 [left,mid]

            中间>右边 [mid+1,right] //中间数左边的数（包括中间数）都不是「旋转排序数组」的最小值
            中间<右边 [left,mid] //中间数到右边界是递增的，所以中间的右边一定不是

            所以对于两个if分支，选择中间位置和右边比较,这样的话，两个分支可以把数组分为[left,mid]和[mid+1,right]两个区间
                            如果选择中间位置和左边比较，因为 中间>左边 min可能在左边或者右边 所以不能分成两个区间

             */
            if(nums[mid]<=nums[right]){
                //[mid，right]局部有序
                right=mid;
            }else {
                left=mid+1;
            }
        }
        return nums[left];
    }

    public static void main(String[] args) {
        int[]arr={4,5,6,7,1,2,3};
        List<Integer>list=new ArrayList<>();
        list.add(1);
        list.add(1);
        list.remove(1);
        int[]num=new int[list.size()];
//        list.contains()
        for(int i:list){
            System.out.println(i);
        }
//        System.out.println(findMin(arr));
    }
}

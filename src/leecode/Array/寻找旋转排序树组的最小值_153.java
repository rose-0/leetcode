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

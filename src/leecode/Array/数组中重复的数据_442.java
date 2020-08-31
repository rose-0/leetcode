package leecode.Array;

import java.util.ArrayList;
import java.util.List;
//另外那个题目是一个数出现1次，其他都是2次，这个题目是有好几个出现1次，其他出现两次
public class 数组中重复的数据_442 {
    //题目有个限制条件 1<= a[i] <=n ,所以可以使用这个方法
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer>list=new ArrayList<>();
        int len = nums.length;
        for (int i = 0; i <len ; i++) {
            //注意加上 nums[i]>0&&nums[i]<=len (是小于等于)
            // 没有 nums[i]!=i 这个条件
            // nums[i]!=nums[nums[i]-1] 相差1 不是 nums[i]!=nums[nums[i]]  ！！
            while (nums[i]>0&&nums[i]<=len&&nums[i]!=nums[nums[i]-1]){
                swap(nums,i,nums[i]-1);
            }
        }
        for (int i = 0; i <len ; i++) {
            if(nums[i]!=i+1){ //要加 1
                list.add(nums[i]);
            }
        }
        return list;
    }
    public void swap(int[]nums,int index1,int index2){
        int temp=nums[index1];
        nums[index1]=nums[index2];
        nums[index2]=temp;
    }
}

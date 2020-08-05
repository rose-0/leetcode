package leecode.other;
//https://www.cnblogs.com/gjmhome/p/11370930.html
import zuoshen.输入输出练习.I;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class 全排列_46 {
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>>lists=new ArrayList<>();
        List<Integer>list=new ArrayList<>();
        method(nums,lists,list,0);
//        for (List<Integer>list1:lists) {
//            for (Integer i:list1) {
//                System.out.print(i+" ");
//            }
//            System.out.println();
//        }
        return lists;
    }
    public static void method(int[]nums,List<List<Integer>>lists,List<Integer>list,int index){
        if(index==nums.length){
            lists.add(new LinkedList<>(list));//这里一定要使用new，这个方法是对一个list操作
        }
        for (int i = index; i <nums.length ; i++) {
            list.add(nums[i]);
            swap(nums,index,i);
            method(nums,lists,list,index+1);
            swap(nums,index,i);
            list.remove(list.size()-1);
        }
    }
    public static void swap(int[]nums,int i,int j){
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }

    public static void main(String[] args) {
        int[]m={1,2,3,4};
        permute(m);
    }
}

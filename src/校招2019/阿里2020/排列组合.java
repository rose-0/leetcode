package 校招2019.阿里2020;

import leecode.链表.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 排列组合 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        List<List<Integer>> res=new ArrayList<>();
        List<Integer>list=new ArrayList<>();
        int[]nums=new int[n];
        for (int i = 0; i <n ; i++) {
            nums[i]=i+1;
        }
        huisu(res,list,nums);
        for(List<Integer>temp:res){
            for(int i:temp){
                System.out.print(i+" ");
            }
            System.out.println();
        }
    }
    public static void huisu(List<List<Integer>> res,List<Integer>list,int[]nums){
        if(list.size()==nums.length){
            for (int i = 0; i <list.size()-1 ; i++) {
                if(Math.abs(list.get(i)-list.get(i+1))==1){
                    return;
                }
            }
            res.add(new ArrayList<>(list));
        }
        for (int i = 0; i <nums.length ; i++) {
            if(list.contains(nums[i])){
                continue;
            }

            list.add(nums[i]);
            huisu(res,list,nums);
            list.remove(list.size()-1);
        }
    }
}

package leecode.Array;

import java.util.ArrayList;
import java.util.List;

public class 两个数组的交集II_350 {
    public static int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> list=new ArrayList<>();
        for(int i=0;i<nums1.length;i++){
            list.add(nums1[i]);
        }
        List<Integer>res=new ArrayList<>();
        for(int i=0;i<nums2.length;i++){
            if(list.contains(nums2[i])){
                System.out.println(nums2[i]);
                res.add(nums2[i]);
                list.remove(Integer.valueOf(nums2[i]));//一定要加interger！！不然报错
            }
        }
        int[]num=new int[res.size()];
        int index=0;
        for(int i:res){
            num[index++]=i;
        }
        return num;
    }

    public static void main(String[] args) {
        int[]num1={4,9,5};
        int[]num2={9,4,9,8,4};
        intersect(num1,num2);
    }
}

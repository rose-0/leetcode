package leecode.Array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 两个数组的交集_349 {
    public int[] intersection(int[] nums1, int[] nums2) {
        Map<Integer,Integer> map=new HashMap<>();
        for (int i = 0; i <nums1.length ; i++) {
            map.put(nums1[i],map.getOrDefault(nums1[i],0)+1);
        }
        List<Integer>list=new ArrayList<>();
        for (int i = 0; i <nums2.length ; i++) {
            if(map.containsKey(nums2[i])){
                list.add(nums2[i]);
                map.remove(nums2[i]);
            }
        }
        int[] num=new int[list.size()];
        int index=0;
        for(int i:list){
            num[index++]=i;
        }
        return num;
    }
}

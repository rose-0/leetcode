package leecode.Array;

import java.util.HashMap;
import java.util.Map;

public class 数组的度_697 {
    public static int findShortestSubArray(int[] nums) {
        int degree=0;
        Map<Integer,Integer>map=new HashMap<>();
        for (int i = 0; i <nums.length ; i++) {
            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
        }
        for(int key:map.keySet()){
            degree=Math.max(degree,map.get(key));
        }
        
        int left =0;
        int right = 0;//right是初始化为0
        int curDegree =0;
        Map<Integer,Integer> window = new HashMap<>();
        int len=Integer.MAX_VALUE;
        while (right<nums.length){ //条件不是 left<right
            int numRight = nums[right];
            window.put(numRight,window.getOrDefault(numRight,0)+1);
            curDegree=Math.max(curDegree,window.get(numRight));
            right++;
            while (curDegree==degree){
                len = Math.min(len,right-left);
                window.put(nums[left],window.get(nums[left]-1));
                left++;
                curDegree=Math.max(curDegree,window.get(numRight));
            }
        }
        return len;
    }

    public static void main(String[] args) {
        int[]nums=new int[]{1,2,2,3,1};
        findShortestSubArray(nums);
    }
}

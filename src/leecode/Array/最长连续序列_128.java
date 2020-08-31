package leecode.Array;


import java.util.HashMap;
import java.util.Map;

public class 最长连续序列_128 {
    public static int longestConsecutive(int[] nums) {
        Map<Integer,Integer>map=new HashMap<>();
        for (int i = 0; i <nums.length ; i++) {
            map.put(nums[i],i);
        }
        int len=1;
        int max=Integer.MIN_VALUE;
        for (int i = 0; i <nums.length ; i++) {
            int target=nums[i]-1;
            while (map.containsKey(target)) {
                len++;
                target--;
            }
            if(len>max){
                max=len;
            }
            len=1;
        }
        return max;
    }

    public static void main(String[] args) {
        int[]num=new int[]{100,4,200,1,3,2};
        System.out.println(longestConsecutive(num));
    }
}

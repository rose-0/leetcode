package leecode.Array;
//数组中出现次数超过一半的数字，这个次数一定要超过一半，如果求出现次数最多的元素，这个元素次数不一定超过一半，则不能用摩尔投票法则
public class 多数元素_169 {
    //https://leetcode-cn.com/problems/majority-element/solution/tu-jie-mo-er-tou-piao-fa-python-go-by-jalan/
    public static int majorityElement(int[] nums) {

        int major=0;
        int count=0;
        for (int i = 0; i <nums.length ; i++) {
            //计数阶段：若 count == 0，代表当前 major 空缺，直接将当前候选人赋值给 major
            if(count==0){
                major=nums[i];
            }
            //对抗阶段 两两抵消
            if(major==nums[i]){
                count++;
            }else {
                count--;
            }
        }


//        System.out.println(count);
        return major;
        //或者排序

    }

    public static void main(String[] args) {
        int[]num={1,2,3};
        System.out.println(majorityElement(num));
    }

}

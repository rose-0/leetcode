package leecode.Array;
//对比26 删除有序数组的重复元素！
public class 移动零_283 {
    public void moveZeroes(int[] nums) {
        int slow=0;
        for (int fast = 0; fast <nums.length ; fast++) {
            if(nums[fast]!=0){//快指针前面探路，找到不为0的元素，慢指针前进
                nums[slow]=nums[fast];
                slow++;
            }
        }
        for (int i = slow; i <nums.length ; i++) {
            nums[i]=0;
        }
    }
}

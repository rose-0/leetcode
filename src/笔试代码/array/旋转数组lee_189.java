package 笔试代码.array;
//https://leetcode-cn.com/problems/rotate-array/solution/man-hua-san-ci-xuan-zhuan-de-fang-fa-shi-ru-he-x-2/
/*
反转3次

 */
public class 旋转数组lee_189 {
    public void rotate(int[] nums, int k) {
        k=k%nums.length;
        /*
        反转所有元素
        反转前k个
        反转后面的元素

         */
        reverse2(nums,0,nums.length-1);
        reverse2(nums,0,k-1);//反转前k个，下标是 0，k-1 不是 0，k  ！！！
        reverse2(nums,k,nums.length-1);
    }
    public void reverse2(int[]nums,int i,int j){
        while (i<j){
            int temp=nums[i];
            nums[i]=nums[j];
            nums[j]=temp;
            i++;
            j--;
        }
    }
}

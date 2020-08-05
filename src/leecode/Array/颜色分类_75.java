package leecode.Array;
//0，1，2 排序。一次遍历，如果是0，则移动到表头，如果是2，则移动到表尾，不用考虑1。0和2处理完，1还会有错吗
public class 颜色分类_75 {
    public static void sortColors(int[] nums) {
        int left=0;
        int right=nums.length-1;
        for (int i = 0; i <nums.length&&left<right; i++) {
            if(nums[i]==0){
                swap(nums,left,i);
                left++;
            }

            if(nums[i]==2){
                swap(nums,i,right);
                right--;
            }
        }
    }

    public static void swap(int[]nums,int left,int right){
        if (left<=right) {//要加这个
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
        }
    }

    public static void main(String[] args) {
        int[]num={0,0,1,2,0,2,1};
        sortColors(num);
        for (int i = 0; i < num.length; i++) {
            System.out.println(num[i]);
        }
    }
}

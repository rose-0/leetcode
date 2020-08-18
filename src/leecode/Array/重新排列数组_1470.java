package leecode.Array;

public class 重新排列数组_1470 {
    public int[] shuffle(int[] nums, int n) {
        int[]arr=new int[n*2];
        int index=0;
        for (int i = 0; i <n ; i++) {
            arr[index++]=nums[i];
            arr[index++]=nums[n+i];
        }
        return arr;
    }
}

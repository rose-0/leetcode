package 笔试代码.array;

public class 无序数组找中位数_lin80 {
    public static int patition(int start,int end,int[]arr){
        int left=start;
        int temp=arr[start];
        while (start!=end){
            while (start<end&&arr[end]>=temp){
                end--;
            }
            while (start<end&&arr[start]<=temp){
                start++;
            }
            if(start<end){
                int i=arr[start];
                arr[start]=arr[end];
                arr[end]=i;
            }
        }
        int j=arr[start];
        arr[start]=temp;
        arr[left]=j;
        return start;
    }
    public static int median(int[] nums) {
        int k=patition(0,nums.length-1,nums);
        int mid=(nums.length-1)/2;
        while (k!=mid){
            if(k>mid){
                k=patition(0,k-1,nums);
            }else {
                k=patition(k+1, nums.length-1,nums);
            }
        }
        return nums[mid];
    }

    public static void main(String[] args) {
        int[] num = {4, 5, 1, 2, 3};
        System.out.println(median(num));
    }
}

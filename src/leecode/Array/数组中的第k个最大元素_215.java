package leecode.Array;
//https://leetcode-cn.com/problems/kth-largest-element-in-an-array/solution/partitionfen-er-zhi-zhi-you-xian-dui-lie-java-dai-/
public class 数组中的第k个最大元素_215 {
    public static int findKthLargest(int[] nums, int k) {
        //转换一下，第 k 大元素的索引是 len - k（因为升序）
        int index = quickSort(nums,0,nums.length-1);
        while (index!=nums.length-k){
            if(index<nums.length-k){
                index=quickSort(nums,index+1,nums.length-1);
            }else {
                index=quickSort(nums,0,index-1);
            }
        }
        return nums[index];
    }
    public static int quickSort(int[]nums,int start,int end){
        if(start>end){
            return -1;
        }
        int left=start;
        int right=end;
        int privot=nums[start];
        while (left!=right){
            while (left<right&&nums[right]>=privot){
                right--;
            }
            while (left<right&&nums[left]<=privot){
                left++;
            }
            if(left<right){
                int i=nums[left];
                nums[left]=nums[right];
                nums[right]=i;
            }
        }
        nums[start]=nums[left];
        nums[left]=privot;
        return left;
//        quickSort(nums,k,start,left-1);
//        quickSort(nums,k,left+1,end);
    }

    public static void main(String[] args) {
        int[]nums={1,3,4,5,2};
//        quickSort(nums,0,nums.length-1);
//        for (int i = 0; i <nums.length ; i++) {
//            System.out.println(nums[i]);
        System.out.println(findKthLargest(nums,2));
        }
    }


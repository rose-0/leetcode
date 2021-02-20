package leecode.Array;
// liweiwei写了很多解法 可以看看
// https://leetcode-cn.com/problems/kth-largest-element-in-an-array/solution/partitionfen-er-zhi-zhi-you-xian-dui-lie-java-dai-/
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

    public int findKthLargest2(int[] nums, int k) {
        int len=nums.length;
        int left=0;
        int right=len-1;
        int target=len-k;
        while (true){
            int index=partition(nums,left,right);
            if(index==target){
                return nums[index];
            }else if(index<target){
                left=index+1;
            }else {
                right=index-1;
            }
        }
    }


    public int partition(int[]nums,int left,int right){
        int pivot = nums[left];
        
        int j=left;
        /*
         * 在遍历过程中保持循环不变量的语义
             1、[left + 1, j] < nums[left]
             2、(j, i] >= nums[left]
         */
        for (int i = left+1; i <=right ; i++) {
            if(nums[i]<pivot){
                //j 始终指向 < pivot的最后一个元素
                j++;
                //小于 pivot 的元素都被交换到前面
                swap(nums,i,j);
            }
        }
        // 在之前遍历的过程中，满足 [left + 1, j] < pivot，并且 (j, i] >= pivot
        swap(nums,j,left);
        // 交换以后 [left, j - 1] < pivot, nums[j] = pivot, [j + 1, right] >= pivot
        return j;
    }
    
    public void swap(int[]nums,int i,int j){
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }
    

    public static void main(String[] args) {
        int[]nums={1,3,4,5,2};
//        quickSort(nums,0,nums.length-1);
//        for (int i = 0; i <nums.length ; i++) {
//            System.out.println(nums[i]);
        System.out.println(findKthLargest(nums,2));
        }
    }


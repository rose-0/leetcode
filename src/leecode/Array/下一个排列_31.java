package leecode.Array;

public class 下一个排列_31 {
    /*
    其实就是从数组倒着查找，找到nums[i] 比nums[i+1]小的时候，
    就将nums[i]跟nums[i+1]到nums[nums.length - 1]
    当中找到一个最小的比nums[i]大的元素交换。
    交换后，再把nums[i+1]到nums[nums.length-1]排序，就ok了

    找到后先排序再交换也可以！，这个题目 排序是通过反转数组实现的。先找到逆序的部分
    //（以3 4 5 2 1 为例）
    //从后往前寻找第一次出现的正序对：（找到 4,5）
    //之后因为从5 开始都是逆序，所以把他们反转就是正序：3 4 1 2 5
    //之后4 的位置应该是：在它之后的，比他大的最小值（5）
    //交换这两个值：得到 3 5 1 2 4
    // 对于初始即为逆序的序列，将在反转步骤直接完成
     */
    public static void nextPermutation(int[]nums){
        int len=nums.length;
        int i=len-1;
        while (i>0&&nums[i-1]>=nums[i]){
            i--;
        }
        System.out.println("i="+i);
        reverse(nums,i,len-1);//i , len-1是降序的，反转
        if(i==0){//题目说 如果不存在下一个排列，则数字重新排列成最小的排列
            //当i==0时，说明没找到正序排列退出的循环，在翻转后，return
            return;
        }
        System.out.println("111");
        int j=i;
        //找比 i-1 大的数字
        for (j = i; j <len ; j++) {//正着找，因为后面已经升序了
            if(nums[j]>nums[i-1]){//  >=不对 1，5，1可以试下
                break;
            }
        }
        System.out.println("j="+j);
        //交换
        int temp=nums[j];
        nums[j]=nums[i-1];
        nums[i-1]=temp;
    }
    public static void reverse(int[]nums,int start,int end){
        while (start<end){
            int temp=nums[start];
            nums[start]=nums[end];
            nums[end]=temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        int[]nums={1,5,1};
        nextPermutation(nums);

    }
}

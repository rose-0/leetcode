package leecode.Array;

public class 山脉数组的峰顶索引_852 {
    //其实就是寻找最大值的下标
    //和寻找峰值 lee162 的代码一样
    public int peakIndexInMountainArray(int[] arr) {
        int left=0;
        int right=arr.length-1;
        while (left<right){
            int mid=(left+right)/2;
            if(arr[mid]<arr[mid+1]){
                left=mid+1;
            }else {
                right=mid;
            }
        }
        return left;
    }
    //方法二 有点区别
    public int peakIndexInMountainArray1(int[] arr) {
        int left=0;
        int right=arr.length-1;
        while (left<right){
            int mid=(left+right+1)/2;//右中位数
            if(arr[mid]>arr[mid-1]){
                left=mid;
            }else {
                right=mid;
            }
        }
        return left;
    }
}

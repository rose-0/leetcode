package leecode.Array;

public class 在排序数组中查找元素的第一个和最后一个位置_34 {
    public int[] search(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        int[] res = new int[2];
        while (low < high) {//找左侧
            mid = (low + high) / 2;
            if (arr[mid] >= target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        if (arr[low] != target) return res;
        res[0] = low;
        high = arr.length - 1;
        while (low < high) {//找右侧
            mid = (low + high) / 2;
            if (arr[mid] <= target) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        res[1] = high;
        return res;
    }

    //liweiwei 二分
    public int[] search2(int[] arr, int target) {
        if(arr.length==0){
            return new int[]{-1,-1};
        }


        int left=0;
        int right=arr.length-1;
        int[]res=new int[2];
        //寻找target的起始位置，可以封装为函数
        while (left<right){
            int mid=(left+right)>>>1;

            if(arr[mid]<target){
                //左中位数 左区间收缩
                // 下一轮搜索区间是 [mid+1,right]
                left=mid+1;
            }else {
                //arr[mid]>=target
                // 下一轮的搜索区间是 [left,mid]
                right=mid;
            }
        }

        if(arr[left]!=target){
            return new int[]{-1,-1};
        }

        res[0]=left;

        //寻找target的结束位置,可以封装为函数
        left=0;
        right=arr.length-1;
        while (left<right){
            int mid=(left+right+1)>>>1;
            if(arr[mid]>target){
                //左中位数 左区间收缩
                // 下一轮搜索区间是 [left,mid-1]
                right=mid-1;//不是mid
            }else {
                //arr[mid]<=target
                // 下一轮搜索区间是 [mid,right]，左区间没有收缩，所以使用右中位数
                left=mid;
            }
        }

        res[1]=left;

        return res;

    }
}

package leecode.Array;

public class 在排序数组中查找元素的第一个和最后一个位置_34 {
    public int[] search(int[]arr,int target){
        int low=0;
        int high=arr.length-1;
        int mid=0;
        int[]res=new int[2];
        while (low<high){//找左侧
            mid=(low+high)/2;
            if(arr[mid]>=target) {high=mid;}
            else {low=mid+1;}
        }
        if(arr[low]!=target) return res;
        res[0]=low;
        high=arr.length-1;
        while (low<high){//找右侧
            mid=(low+high)/2;
            if(arr[mid]<=target){
                low=mid;
            }else {
                high=mid-1;
            }
        }
        res[1]=high;
        return res;
    }
}

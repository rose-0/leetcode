package Sort;

import java.util.Arrays;

public class quickSort {
    public static void quick_sort(int []arr,int low,int high){
        if(low<high){
            int privot=partition(arr,low,high);
            for (int i = 0; i <arr.length ; i++) {
                System.out.println(arr[i]);
            }
//            quick_sort(arr,low,privot-1);
//            quick_sort(arr,privot+1,high);

        }
    }
    public static int partition(int[]arr,int low,int high){
        int privot=arr[low];
        int i=low;
        for (int j = low+1; j <=high ; j++) {
            if(arr[j]<=privot){
                i++;
                swap(arr,i,j);
            }
        }
        swap(arr,low,i);
        return i;
    }
    public static void swap(int[]arr,int i,int j){
        if(i<j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    public static void main(String[] args) {
        int arr[]={6,1,2,7,9,3,4,5,10,8};
        quick_sort(arr,0,arr.length-1);
        for (int i = 0; i <arr.length ; i++) {
            System.out.print(arr[i]+" ");
        }
    }
}


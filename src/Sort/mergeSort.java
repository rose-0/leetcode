package Sort;
/*
快速排序是先找基准，利用基准划分为两个子数组，再递归调用自己，注意递归调用时
quick(left, start - 1);
quick(start + 1, right);
下标要一个加一，一个减一
归并排序是直接递归调用自己两次，边界和快速排序不同！
mergeSort(arr,l,mid);//这里是mid，不是mid-1！！！mergeSort(arr,l,mid-1)
mergeSort(arr,mid+1,r);
最后再调用merge合并两个数组
 */
//归并排序 图解 https://www.cnblogs.com/chengxiao/p/6194356.html
public class mergeSort {
    public void mergeSort(int[]arr,int l,int r){
        if(l==r){//注意临界条件,和快排不同,l==r剩一个数不需要递归
            return;
        }
        int mid=(l+r)/2;
        mergeSort(arr,l,mid);//这里是mid，不是mid-1！！！mergeSort(arr,l,mid-1)
        mergeSort(arr,mid+1,r);
        merge(arr,l,mid,r);
    }
    public void merge(int[]arr,int l,int mid,int r){//对比合并k个排序数组
        int[]help=new int[r-l+1];
        int p1=l;
        int p2=mid+1;
        int i=0;
        while (p1<=mid&&p2<=r){
            help[i++]=arr[p1]<arr[p2]?arr[p1++]:arr[p2++];
        }
        while (p1<=mid){
            help[i++]=arr[p1++];
        }
        while (p2<=r){
            help[i++]=arr[p2++];
        }
        for (int j = 0; j <help.length ; j++) {
            arr[l+j]=help[j];
        }

    }
    public void quick(int[]arr,int l,int r){
        int start=l;
        int end=r;
        int temp=arr[l];
        while (start<end){
            while (start<end&&arr[end]>=temp){
                end--;
            }
            while (start<end&&arr[start]<=temp){
                start++;
            }
            if(start<end){

            }
        }

    }
}

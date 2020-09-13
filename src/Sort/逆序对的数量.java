package Sort;
//类似还有小和问题
//https://blog.csdn.net/ARPOSPF/article/details/81297100?utm_source=blogxgwz4&utm_medium=distribute.pc_relevant.none-task-blog-baidujs-1&spm=1001.2101.3001.4242
public class 逆序对的数量 {
    public static int mergeSort(int[]arr,int l,int r){
        if(l==r){//注意临界条件,和快排不同,l==r剩一个数不需要递归
            return 0;
        }
        int mid=(l+r)/2;
        int leftnum=mergeSort(arr,l,mid);//这里是mid，不是mid-1！！！mergeSort(arr,l,mid-1)
        int rightnum=mergeSort(arr,mid+1,r);
        return leftnum+rightnum + merge(arr,l,mid,r);
    }
    public static int merge(int[]arr,int left,int mid,int right){//对比合并k个排序数组
        int[]help=new int[right-left+1];
        int p1=left;
        int p2=mid+1;
        int index=0;
        int count=0;
        while (p1<=mid&&p2<=right){
//            help[index++]=arr[p1]<arr[p2]?arr[p1++]:arr[p2++];
            //计算逆序对在这里更改
            if(arr[p1]>arr[p2]){
                // p1>p2，因为两边局部有序，所以p1之后[p1~mid]的所有值都大于p2  p1跟[p1..mid]和p2  所有值构成逆序对
                for (int j = p1; j <=mid ; j++) {
                    System.out.println(arr[j]+" "+arr[p2]);
                }
                count=count+(mid-p1+1);//是right-p2！！
                help[index++]=arr[p2++];
            }else {
                help[index++]=arr[p1++];
            }
        }
        while (p1<=mid){
            help[index++]=arr[p1++];
        }
        while (p2<=right){
            help[index++]=arr[p2++];
        }
        for (int j = 0; j <help.length ; j++) {
            arr[left+j]=help[j];
        }
        return count;
    }

    public static void main(String[] args) {
        int[]num={1,2,3,4,5,6,7,0};
        System.out.println(mergeSort(num,0,num.length-1));
    }
}

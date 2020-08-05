package zuoshen.array;
//二分查找设置high一定要是high-1，left一定要是mid+1，否则是死循环
import java.util.Scanner;

public class 未排序数组中累加和为小于或等于给定值的最长子数组长度 {
    public static int low_k(int[]arr,int k){
        if(arr==null||arr.length==0){
            return 0;
        }
        int length=arr.length;
        int sum=0;
        int[] help=new int[length+1];
        help[0]=sum;
        for (int i = 0; i <length ; i++) {
            sum+=arr[i];
            help[i+1]=Math.max(help[i],sum);
        }
        sum=0;
        int max=0;
        int pre=0;
        int len=0;
        for (int i = 0; i <length ; i++) {
            sum+=arr[i];
            pre=getless(help,sum-k);
            len=pre==-1?0:i-pre+1;
            max=Math.max(len,max);
        }
        return max;

    }
    public static int getless(int[]help,int num){
        int low=0;
        int high=help.length-1;
        int res=-1;
        int mid=0;
        while (low<=high){
            mid=(low+high)/2;
            if(help[mid]>=num){
                res=mid;
                high=mid-1;
            }else {
                low=mid+1;
            }
        }
        return res;
    }




    public static int method_dp2(int[]arr,int aim){
        int[]help=new int[arr.length];//help一定要多一个！！！
        int sum=arr[0];
        help[0]=arr[0];
        for (int i = 1; i <arr.length ; i++) {
            sum=sum+arr[i];
            help[i]=sum>help[i-1]?sum:help[i-1];
        }
        sum=0;
        int max=0;
        for (int i = 0; i <arr.length ; i++) {
            sum+=arr[i];
            int j=search(help,sum-aim);
            max=j==-1?max:Math.max(i-j+1,max);
        }
        return max;
    }
    public static int search(int[]help,int aim){
        int left=0;
        int right=help.length;
        int mid=(left+right)/2;
        int res=-1;
        while (left<=right){
            if(help[mid]<aim){
                left=mid+1;
            }else {
                res=mid;
                right=mid-1;
            }
            mid=(left+right)/2;
        }
        return res;
    }
    public static void main(String[] args) {
//        Scanner sc=new Scanner(System.in);
//        while (sc.hasNext()){
//            int n=sc.nextInt();
//            int[]arr=new int[n];
//            int k=sc.nextInt();
//            for (int i = 0; i <n ; i++) {
//                arr[i]=sc.nextInt();
//            }
        int[]arr={1,2,-1,5,-2};
            System.out.println(low_k(arr,3));
        System.out.println(method_dp2(arr,2));
//        }
    }
}

package zuoshen.array;

import java.util.Scanner;

public class 未排序正数数组中累加和为给定值的最长子数组长度 {
    public static int longsubarr(int sum,int[]arr){
        int max=0;
        int left=0;
        int right=0;
        int cur=arr[0];
        while (right<arr.length){
            if(cur<sum){
                right++;
                if(right==arr.length){
                    break;
                }
                cur+=arr[right];
            }else if(cur>sum){
                cur-=arr[left];
                left++;
            }else {
                max=Math.max(max,right-left+1);
                cur-=arr[left];
                left++;
            }
        }
        return max;
    }

    public static int method_dp(int[]num,int aim){
        int max=0;
        int left=0;
        int right=0;
        int sum=num[0];
        while (right<num.length){
            if(sum<aim){
                right++;
                if(right==num.length){
                    break;
                }
                sum+=num[right];
            }
            else if(sum>aim){
                sum-=num[left];
                left++;
            }else {
                max=Math.max(max,right-left+1);
                sum-=num[left];
                left++;
            }
        }
        return max;
    }
    public static void main(String[] args) {
//        Scanner sc=new Scanner(System.in);
//        while (sc.hasNext()){
//            int n=sc.nextInt();
//            int aim=sc.nextInt();
//            int[]arr=new int[n];
//            for (int i = 0; i <n ; i++) {
//                arr[i]=sc.nextInt();
//            }
//            System.out.println(longsubarr(aim,arr));
//        }
        int[]num={1,2,1,1,1};
        System.out.println(longsubarr(3,num));
        System.out.println(method_dp(num,3));
    }
}

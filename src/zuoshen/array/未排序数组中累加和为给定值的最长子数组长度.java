package zuoshen.array;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class 未排序数组中累加和为给定值的最长子数组长度 {
    public static int long_subarr(int[]arr,int aim){
        if(arr==null||arr.length==0){
            return 0;
        }
        Map<Integer,Integer>map=new HashMap<>();
        int sum=0;
        int max=0;
        map.put(0,-1);
        for (int i = 0; i <arr.length ; i++) {
            sum+=arr[i];
            if(map.containsKey(sum-aim)){
                max=Math.max(max,i-map.get(sum-aim));
            }
            if(!map.containsKey(sum)){
                map.put(sum,i);
            }
        }
        return max;
    }



    public static int method_dp2(int[]arr,int aim){
        Map<Integer,Integer>hashmap=new HashMap();//和 下标
        hashmap.put(0,-1);
        int cursum=0;
        int len=0;
        for (int i = 0; i <arr.length ; i++) {
            cursum+=arr[i];
            if(hashmap.containsKey(cursum-aim)){
                len=Math.max(len,i-hashmap.get(cursum-aim));
            }
            if(!hashmap.containsKey(cursum)){
                hashmap.put(cursum,i);
            }
        }
        return len;
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
            int[]arr={1,3,7,4,8,2};
            System.out.println(long_subarr(arr,11));
            System.out.println(method_dp2(arr,11));
//        }
    }
}

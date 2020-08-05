package zuoshen.array;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class 正数与负数个数相等的最长子数组长度 {
    public static int sub_sum0(int[]arr){
        if(arr==null||arr.length==0){
            return 0;
        }
        for (int i = 0; i <arr.length ; i++) {
            if(arr[i]>0){
                arr[i]=1;
            }
            if(arr[i]<0){
                arr[i]=-1;
            }
        }
        Map<Integer,Integer>map=new HashMap<>();
        int sum=0;
        int max=0;
        map.put(0,-1);
        for (int i = 0; i <arr.length ; i++) {
            sum+=arr[i];
            if(map.containsKey(sum)){
                max=Math.max(i-map.get(sum),max);
            }
            if(!map.containsKey(sum)){
                map.put(sum,i);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while (sc.hasNext()){
            int n=sc.nextInt();
            int[]arr=new int[n];
            for (int i = 0; i <n ; i++) {
                arr[i]=sc.nextInt();
            }
            System.out.println(sub_sum0(arr));
        }
    }
}

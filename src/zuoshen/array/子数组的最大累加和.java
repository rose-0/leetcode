package zuoshen.array;

public class 子数组的最大累加和 {
    public static int sum_arr(int[]arr){
        int cur=0;
        int max=0;
        for (int i = 0; i <arr.length ; i++) {
            cur+=arr[i];
            max=Math.max(max,cur);
            cur=cur>0?cur:0;
        }
        return max;
    }

    public static void main(String[] args) {
        int[]arr={1,-2,3,5,-2,6,-1};
        System.out.println(sum_arr(arr));
    }
}

package leecode.Array;

import java.util.Arrays;

public class 三角形的最大周长_976 {
    public int largestPerimeter(int[] A) {
        Arrays.sort(A);
        int max=0;
        for (int i = A.length-1; i >=2 ; i--) {
            if(A[i]<A[i-1]+A[i-2]){
                max=Math.max(max,A[i-1]+A[i-2]+A[i]);
                break;
            }
        }
        return max;
    }
}

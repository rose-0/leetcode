package zuoshen.DP;

import java.util.HashMap;

public class 背包01 {
    public static int method(int []w,int []v,int c,int i){
        int result=0;
        if(i==0||c==0){
            result=0;
        }else if(c<w[i]){
            result=method(w,v,c,i-1);
        }else {
            int temp1=method(w,v,c,i-1);
            int temp2=method(w,v,c-w[i],i-1)+v[i];
            result=Math.max(temp1,temp2);
        }
        return result;
    }
    public static int method_map(int[]w,int[]v,int c,int i){
        Integer[][]map=new Integer[w.length+1][c+1];
        int result=0;
        if(i==0||c==0){
            result=0;
        }else if(c<w[i]){
            result=map[i-1][c]==null?method_map(w,v,c,i-1):map[i-1][c];
        }else {
            int temp1=map[i-1][c]==null?method_map(w,v,c,i-1):map[i-1][c];
            int temp2=map[i-1][c-w[i]]==null?method_map(w,v,c-w[i],i-1)+v[i]:map[i-1][c-w[i]];
            result=Math.max(temp1,temp2);
            map[i][c]=result;
        }
        return result;
    }
    public static int method_dp(int[]w,int[]v,int c ){
        int[][]dp=new int[w.length][c+1];
        int row=dp.length;
        int col=dp[0].length;
        System.out.println(row+" "+col);
        for (int j = 0; j <dp.length ; j++) {
            dp[j][0]=0;
        }
        for (int j = 0; j <col ; j++) {
            dp[0][j]=j>=v[0]?v[0]:0;
        }
        for (int j = 1; j <row ; j++) {
            for (int k = 1; k <col ; k++) {
                if(k<w[j]){
                    dp[j][k]=dp[j-1][k];
                }else {
                    dp[j][k] = Math.max(dp[j - 1][k], dp[j - 1][k - w[j]] + v[j]);
                }
            }
        }
        for (int j = 0; j <row ; j++) {
            for (int k = 0; k <col ; k++) {
                System.out.print(dp[j][k]+" ");
            }
            System.out.println();
        }
        return dp[row-1][col-1];
//        return 0;
    }
    public static void main(String[] args) {
        int c=10;
        int[]w={0,2,3,5,5};
        int[]v={0,2,4,3,7};
        System.out.println(method(w,v,10,4));
        System.out.println(method_map(w,v,10,4));
        System.out.println(method_dp(w,v,10));
    }
}

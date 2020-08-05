package leecode.DP;

import java.util.List;
//一般会多一行或者一列，这样可以方便初始化和处理边界
public class 三角形最小路径和_120 {
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle==null||triangle.size()==0){
            return 0;
        }
        int n=triangle.size();
        int[][]dp=new int[n][n];
        for (int j = 0; j <n ; j++) {
            List<Integer>last=triangle.get(n-1);
            dp[n-1][j]=last.get(j);
        }
        for (int i = n-2; i >=0 ; i--) {
            List<Integer>temp=triangle.get(i);
            for (int j = 0; j <temp.size() ; j++) {
                dp[i][j]=Math.min(dp[i+1][j],dp[i+1][j+1])+temp.get(j);
            }
        }
        return dp[0][0];
    }

    public int minimumTotal2(List<List<Integer>> triangle) {
        int n=triangle.size();
        int[][]dp=new int[n][n];
        for (int i = n-1; i >=0; i--) {
            dp[n-1][i]=triangle.get(n-1).get(i);
        }
        for (int i = n-2; i >=0 ; i++) {
            int j=triangle.get(i).size();
            for (int k = 0; k <j ; k++) {
                dp[i][k]=Math.min(triangle.get(i).indexOf(k)+dp[i+1][k],triangle.get(i).indexOf(k)+dp[i+1][k+1]);
            }
        }
        return dp[0][0];
    }
}

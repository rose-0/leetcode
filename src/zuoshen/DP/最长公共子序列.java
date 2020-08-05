package zuoshen.DP;

public class 最长公共子序列 {
    String res="";
    public static int method_re(char[]c1,int m,char[]c2,int n){
        if(m==0||n==0){

            return 0;
        }else if(c1[m-1]==c2[n-1]){
            return method_re(c1,m-1,c2,n-1)+1;
        }else {
            return Math.max(method_re(c1,m-1,c2,n),method_re(c1,m,c2,n-1));
        }
    }
    public static int[][] method_dp(char[]c1,char[]c2){
        int m=c1.length+1;
        int n=c2.length+1;
        int[][]dp=new int[m][n];
        for (int i = 1; i <m ; i++) {
            for (int j = 1; j <n ; j++) {
                dp[i][j]=Math.max(dp[i][j-1],dp[i-1][j]);
                if(c1[i-1]==c2[j-1]){
                    dp[i][j]=Math.max(dp[i-1][j-1]+1,dp[i][j]);//是三者里面的最大值
                }
            }
        }
        System.out.println(Lcs(dp,c1,c2));
        return dp;
    }
    public static String Lcs(int[][]dp,char[]c1,char[]c2){
        int m=c1.length;
        int n=c2.length;
        int length=dp[m][n];
        char[]res=new char[length];
        int index=length-1;
        while (index>=0){
            if(n>0&&dp[m][n]==dp[m][n-1]){
                n--;
            }else if(m>0&&dp[m][n]==dp[m-1][n]){
                m--;
            }else {
                res[index--]=c1[m-1];
                m--;
                n--;
            }
        }
//        System.out.println(res);
        return String.valueOf(res);
    }
    public static int method_dp3(String str1,String str2){
        int m=str1.length()+1;
        int n=str2.length()+1;
        int[][]dp=new int[m][n];
        char[]chars1=str1.toCharArray();
        char[]chars2=str2.toCharArray();
        for (int i = 1; i <m ; i++) {
            for (int j = 1; j <n ; j++) {
                dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
                if(chars1[i-1]==chars2[j-1]){
                    dp[i][j]=Math.max(dp[i-1][j-1]+1,dp[i][j]);
                }
            }
        }
        char[]res=new char[dp[m-1][n-1]];
        int index=res.length-1;
        m--;
        n--;
        while (index>=0){
            if (m>0&&dp[m][n]==dp[m-1][n]){
                m--;
            }
            else if (n>0&&dp[m][n]==dp[m][n-1]){
                n--;
            }
            else {
                res[index--]=chars1[m-1];
                m--;
                n--;
            }
        }
        System.out.println(res);
        return dp[m-1][n-1];
    }

    public static void main(String[] args) {
        String s1="program";
        String s2="algorithm";

        System.out.println(method_re(s1.toCharArray(),7,s2.toCharArray(),9));
        System.out.println(method_dp3(s1,s2));
//        System.out.println(method_dp(s1.toCharArray(),s2.toCharArray()));

    }
}

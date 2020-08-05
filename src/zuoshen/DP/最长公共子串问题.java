package zuoshen.DP;

public class 最长公共子串问题 {
    public static int method_re(char[]c1,int m,char[]c2,int n){
        if(m==0||n==0){
            return 0;
        }else if(c1[m-1]==c2[n-1]){
            int length=0;
            for (int i = m-1, j=n-1; i >=0&&j>=0 ; i--,j--) {
                if(c1[i]==c2[j]){
                    length++;
                }else {
                    break;
                }
            }
            return length;
        }else {
            return Math.max(method_re(c1,m-1,c2,n),method_re(c1,m,c2,n-1));
        }
    }
    public static int method_dp(char[]c1,char[]c2){
        int m=c1.length+1;
        int n=c2.length+1;
        int[][]dp=new int[m][n];
        for (int i = 1; i <m ; i++) {
            for (int j = 1; j <n ; j++) {
                if(c1[i-1]==c2[j-1]){
                    dp[i][j]=dp[i-1][j-1]+1;
                }
            }
        }
        return dp[m-1][n-1];
    }
    public String lis(int[][]dp,char[]c1,char[]c2){
        int row=dp.length;
        int col=dp[0].length;
        int max=0;
        int end=0;
        for (int i = 1; i <row ; i++) {
            for (int j = 1; j <col ; j++) {
                if(dp[i][j]>max){
                    end=i-1;
                    max=dp[i][j];
                }
            }
        }
        String str=String.valueOf(c1);
        return str.substring(end-max+1,end+1);
    }
    public static void main(String[] args) {
        String s1="program";
        String s2="algorithm";
//        System.out.println(method_re(s1.toCharArray(),7,s2.toCharArray(),9));
        System.out.println(method_dp(s1.toCharArray(),s2.toCharArray()));
    }
}

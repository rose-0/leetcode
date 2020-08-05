package leecode.DP;
//https://leetcode-cn.com/problems/wildcard-matching/solution/dong-tai-gui-hua-si-yao-su-by-a380922457-4/
//f[i][j] = f[i - 1][j]而不是 f[i - 1][j - 1]
//https://blog.csdn.net/qq_41231926/article/details/82732623
//f(x, y) = f(x, y - 1) || f(x - 1, y - 1) || f(x - 2, y - 1) || ... || f(0, y - 1)
// 简化为f(x, y) = f(x, y - 1) || f(x - 1, y)呢
//可以理解为f(x,y)=f(x,y-1),空字符和f(x-1,y)所以f(x-1,y)=f(x-1,y-1)和f(x-2,y)
//f[i][j] = f[i - 1][j]    (abc, a*) f[3][2] = f[2][2]
//x为源字符串，y为匹配字符串，当y的下标为*，如果匹配空串，说明看y-1前面和x是否匹配则y-1,
// 如果匹配任意个，看y是否匹配x-1则x-1
public class 通配符匹配_44 {//列是模式串
    public boolean isMatch(String s, String p) {
        char[]chars=s.toCharArray();
        char[]charp=p.toCharArray();
        int m=chars.length+1;
        int n=charp.length+1;
        boolean[][]dp=new boolean[m][n];
        dp[0][0]=true;
        for (int i = 1; i <n ; i++) {
            dp[0][i]=dp[0][i-1]&&charp[i-1]=='*';
        }
        for (int i = 1; i <m ; i++) {
            for (int j = 1; j <n ; j++) {
                if(chars[i-1]==charp[j-1]||charp[j-1]=='?'){//注意charp永远是下标j，chars是下标i
                    dp[i][j]=dp[i-1][j-1];
                }
                if(charp[j-1]=='*'){
                    dp[i][j]=dp[i][j-1]||dp[i-1][j];
                }
            }
        }
        return dp[m-1][n-1];
    }
}

package leecode.DP;
//https://blog.csdn.net/qq_28114615/article/details/86483709#2.1%20
//dp的边界条件是长度为1和2的子串，这个不是开头两个字符，而是所有长度为1和2的子串
public class 最长回文子串_5 {
    public String longestPalindrome1(String s) {
        int max=0;
        int len=0;
        String sub="";
        for (int i = 0; i <s.length() ; i++) {
            for (int j = i+1; j <=s.length() ; j++) {
                if(is_string(s.substring(i,j).toCharArray())){
                    len=s.substring(i,j).length();
                    if(max<len){
                        max=len;
                        sub=s.substring(i,j);
                    }
                }
            }
        }
        return sub;
    }
    public boolean is_string(char[]res){
        int len=res.length;
        for (int i = 0; i <len/2 ; i++) {
            if(res[i]!=res[len-i-1]){
                return false;
            }
        }
        return true;
    }

    // github 解法 双指针 https://github.com/labuladong/fucking-algorithm/blob/master/%E9%AB%98%E9%A2%91%E9%9D%A2%E8%AF%95%E7%B3%BB%E5%88%97/%E6%9C%80%E9%95%BF%E5%9B%9E%E6%96%87%E5%AD%90%E4%B8%B2.md
    // 这个是中心扩散法 也可以看liweiwei的题解
    public static String longestpalind(String s){
        String res=new String();
        for (int i = 0; i <s.length() ; i++) {//这里是i <s.length()！！ 如果i <s.length()-1，case：对于"a" 应该返回a
            String s1=panlindrome(s,i,i); //对于 aba
            String s2=panlindrome(s,i,i+1);//对于 abba 回文串长度为偶数，只能从s[i]和s[i+1]向两边扩散
            res=res.length()>s1.length()?res:s1;
            res=res.length()>s2.length()?res:s2;
        }
        return res;
    }
    public static String panlindrome(String s,int l,int r){
        while (l>=0&&r<s.length()&&(s.charAt(l)==s.charAt(r))){//s.indexOf(l)==s.indexOf(r) 这个一定要加括号
            l--;
            r++;
        }
        System.out.println("l:"+l+"r:"+r);
        //推出循环前 执行了 l-- r++
        return s.substring(l+1,r);//包括起始，不包括结束，看着不太对
    }

    /*
    动态规划 liweiwei:https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zhong-xin-kuo-san-dong-tai-gui-hua-by-liweiwei1419/

     */
    public String longestPalindrome(String s) {
        int len=s.length();

        //dp[i][j] 表示 s[i, j] 是否是回文串
        boolean[][]dp=new boolean[len][len];
        //单个字符是回文
        for (int i = 0; i <len ; i++) {
            dp[i][i]=true;
        }
        int maxLen=1;
        int begin=0;
        for (int j = 1; j <len ; j++) {
            for (int i = 0; i <j ; i++) {
                if(s.charAt(i)!=s.charAt(j)){
                    dp[i][j]=false;
                }else {//i 和 j 位置上相等
                    if(j-i<3){// dp[i+1][j-1] 即j-1-(i+1)+1<2 => [i+1,j-1]不构成区间, j-i<3 即[i,j]长度(j-i+1<4)为2 或者 3
                        dp[i][j]=true;
                    }else {
                        dp[i][j]=dp[i+1][j-1];
                    }
                }
                // 只要 dp[i][j] == true 成立，就表示子串 s[i..j] 是回文，此时记录回文长度和起始位置
                if(dp[i][j]&&j-i+1>maxLen){
                    maxLen=j-i+1;
                    begin=i;
                }
            }
        }
        return s.substring(begin,begin+maxLen);
    }

    //暴力解法 找到所有的子串 然后判断回文 更新最大长度


    public static void main(String[] args) {
        String s="a";
//        s.toCharArray().length
        System.out.println(longestpalind(s));
    }

}

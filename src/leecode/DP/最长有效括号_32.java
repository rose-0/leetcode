package leecode.DP;

public class 最长有效括号_32 {
    public static int longestValidParentheses(String s) {
        int [] dp=new int[s.length()];
        char[]chars=s.toCharArray();
        int max=0;
        for (int i = 1; i <chars.length ; i++) {//dp[0]是0 一个字符不会构成括号
            if(chars[i]==')'){
                if(chars[i-1]=='('){
                    dp[i]=2; //不要忘记这个
                    if(i-2>=0) {
                        dp[i] = dp[i - 2] + 2;//是 dp[i-2]
                    }
                }
                if(chars[i-1]==')'){
                    if(i-dp[i-1]-1>=0&&chars[i-dp[i-1]-1]=='('){
                        dp[i]=dp[i-1]+2;
                        if(i-dp[i-1]-2>0){
                            dp[i]=dp[i-1]+2+dp[i-dp[i-1]-2];
                        }
                    }
                }
            }
            max=Math.max(max,dp[i]);
        }
        return max;
    }


    public static int method(String str){
        char[]chars=str.toCharArray();
        int[]dp=new int[chars.length];
        int max=0;
        for (int i = 1; i <chars.length ; i++) {
            if(chars[i]=='('){
                dp[i]=0;
            }
//            int pre=i-dp[i-1]-1;
            if(chars[i]==')'){
                int pre=i-dp[i-1]-1;
                if(pre>=0&&chars[pre]=='(') {
                    dp[i] = dp[i - 1] + 2 + (pre-1>=0?dp[pre-1]:0);
                }
            }
            max=Math.max(max,dp[i]);
        }
        return max;
    }
    public static void main(String[] args) {
        String s="(()";
//        int len=longestValidParentheses(s);
//        System.out.println(len);
        System.out.println(method(s));
    }
}

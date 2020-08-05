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
        return s.substring(l+1,r);//包括起始，不包括结束，看着不太对
    }

    public static void main(String[] args) {
        String s="a";
//        s.toCharArray().length
        System.out.println(longestpalind(s));
    }

}

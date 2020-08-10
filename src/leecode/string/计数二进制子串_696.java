package leecode.string;
//https://leetcode-cn.com/problems/count-binary-substrings/solution/696java-zhong-xin-kuo-zhan-yu-hui-wen-chuan-xiang-/
public class 计数二进制子串_696 {
    public int countBinarySubstrings(String s) {
        //回文的思想，中心扩展
        char[]chars=s.toCharArray();
        int count=0;
        for (int i = 1; i <chars.length-1 ; i++) {//枚举中心位置
            int left=i-1;
            int right=i;

            char leftChar=chars[left];//注意这个技巧
            char rightChar=chars[right];

            if(leftChar==rightChar){
                continue;
            }

            while (left>0&&right<chars.length&&chars[left]==leftChar&&chars[right]==rightChar){
                left--;
                right++;
                count++;
            }
        }
        return count;
    }

    int count=0;
    //也可以参考这个，思路更简单点https://leetcode-cn.com/problems/count-binary-substrings/solution/dui-mei-yi-wei-jin-xing-kuo-zhan-yu-hui-wen-zi-fu-/
    public int countBinarySubstrings2(String s) {
        for(int i = 1; i < s.length(); i++) {
            // 出现 ‘01’ 的情况
            if(s.charAt(i-1) == '0' && s.charAt(i) == '1') {
                BinarySubstring(s, i-1, i);
            }
            // 出现 ‘10’ 的情况
            if(s.charAt(i-1) == '1' && s.charAt(i) == '0') {
                BinarySubstring(s, i-1, i);
            }
        }
        return count;
    }
    public void BinarySubstring(String s, int start, int end) {
        char f = s.charAt(start);
        char e = s.charAt(end);
        while(start >= 0 && end < s.length() && s.charAt(start) == f && s.charAt(end) == e) {
            start--;
            end++;
            count++;
        }
    }


}

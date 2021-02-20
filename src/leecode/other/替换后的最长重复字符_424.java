package leecode.other;

public class 替换后的最长重复字符_424 {
    //https://leetcode-cn.com/problems/longest-repeating-character-replacement/solution/tong-guo-ci-ti-liao-jie-yi-xia-shi-yao-shi-hua-don/
    public int characterReplacement(String s, int k) {
        int[]map=new int[26];
        if(s==null){
            return 0;
        }
        char[]chars=s.toCharArray();
        int left=0;
        int right=0;
        int historyCharMax = 0;
        while (right<chars.length){
            int index=chars[right]-'A';
            map[index]++;
            historyCharMax=Math.max(historyCharMax,map[index]);//当前窗口内的最多字符的个数
            if(right-left+1-historyCharMax>k){////需要替换的字符个数就是当前窗口的大小减去窗口中数量最多的字符的数量
                map[chars[left]-'A']--;
                left++;
            }
            right++;
        }
        return chars.length-left;
    }
}

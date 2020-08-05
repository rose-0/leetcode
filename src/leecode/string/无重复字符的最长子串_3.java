package leecode.string;

import zuoshen.输入输出练习.I;

import java.util.HashMap;
import java.util.Map;
//左神285 对于字符串，可以使用数组作为map使用
//不需要申请数组dp：使得dp[i]表示已str[i]结尾的长度
//可以考虑使用滑动窗口
public class 无重复字符的最长子串_3 {
    public static int lengthOfLongestSubstring(String s) {
        if(s==null||s.length()==0){
            return 0;
        }
        char[]chars=s.toCharArray();

        Map<Character,Integer>map=new HashMap<>();
        int max=0;
        int pre=-1;
        for (int i = 0; i <chars.length ; i++) {
            Character c=chars[i];
            if(!map.containsKey(c)){
                max=Math.max(max,i-pre);
                map.put(c,i);
            }else {
                int index=map.get(c);
                pre=Math.max(index,pre);
                max=Math.max(max,i-pre);
                map.put(c,i);
            }
        }
        return max;
    }


    public static int length_str(String str){
        if(str==null||str.length()==0){
            return 0;
        }
        char[]chars=str.toCharArray();
        int[]dp=new int[str.length()];
        int max=1;
        HashMap<Character, Integer>map=new HashMap<Character, Integer>();
        map.put(chars[0],0);
        dp[0]=1;
        for (int i = 1; i <str.length() ; i++) {//从1开始
            dp[i]=1;
            if(chars[i]==chars[i-1]){
                dp[i]=1;
            }
            if(chars[i]!=chars[i-1]){
                if(map.containsKey(chars[i])){
                    dp[i]=Math.min(dp[i-1]+1,i-map.get(chars[i]));
                }else {
                    dp[i]=dp[i-1]+1;
                }
            }
            map.put(chars[i],i);//每次都要更新
            max=Math.max(max,dp[i]);
        }
        for (int i = 0; i <dp.length ; i++) {
            System.out.print(dp[i]+" ");
        }
        System.out.println();
        return max;
    }

    public static int method_dp2(String str){
        if(str==null||str.length()==0){
            return 0;
        }
        int[]map=new int[256];
        for (int i = 0; i <map.length; i++) {
            map[i]=-1;
        }
        int pre=-1;
        int max=0;
        int cur=0;
        char[]chars=str.toCharArray();
        for (int i = 0; i <chars.length ; i++) {
            pre=Math.max(pre,map[chars[i]]);
            cur=i-pre;
            max=Math.max(max,cur);
            map[chars[i]]=i;
        }
        return max;
    }

    public static int windows(String s){
        int maxlen=Integer.MIN_VALUE;
        int left=0;
        int right=0;
        Map<Character,Integer> window=new HashMap<>();
        while (right<s.length()){
            Character c=s.charAt(right);//一定要把这个c存下来，
            // 因为后面right变了，while（window.get(s.charAt(right))）就变了，就错了
            window.put(c,window.getOrDefault(c,0)+1);//可能此时右指针c已经放在map了，所以不能直接赋值1
            right++;
            while (window.get(c)>1){//这个c是右指针之前的c，而不是此时右指针指向的c,把右指针字符放进去就判断一下
                // 不断对放入右边的字符进行检测，然后对左边操作
                Character m=s.charAt(left);
                window.put(m,window.get(m)-1);//写成window.remove(m)就错了！！！
                left++;
            }
            maxlen=Math.max(right-left,maxlen);
        }
        return maxlen;
    }
    public static void main(String[] args) {
        String str="abcabcbb";
//        System.out.println(lengthOfLongestSubstring(str));
//        System.out.println(length_str(str));
//        System.out.println(method_dp2(str));
        System.out.println(windows(str));
    }
}

package leecode.string;

import java.util.HashMap;
import java.util.Map;

public class 字符串的排列_567 {
    //s2是否包含s1
    public boolean checkInclusion(String s1,String s2){
        Map<Character,Integer> need=new HashMap<>();
        Map<Character,Integer> window=new HashMap<>();
        for (int i=0; i<s1.length();i++) {
            need.put(s1.charAt(i),need.getOrDefault(s1.charAt(i),0)+1);
        }
        int left =0;
        int right =0;
        int valid =0;
        while (right<s2.length()){
            char c=s2.charAt(right);
//            window.put(c,window.getOrDefault(c,0)+1);//这个写错了。包含才放进去
            right++;
            if(need.containsKey(c)){
                window.put(c,window.getOrDefault(c,0)+1);
                if(window.get(c)==need.get(c))
                    valid++;
            }
            /*
            if(right-left+1==s1.length()){
                if(valid==need.size()){
                    return true;
                }else {
                    char m=s2.charAt(left);
                    left++;
                    window.put(m,window.get(m));
                    if(need.containsKey(m)){
                        valid--;
                    }
                }
            }

             */
            //窗口大小大于s1的长度就缩小！！！
            while (right-left>=s1.length()){//这时right已经加了1，但是right字符没有放进去，这里其实每次达到长度就判断
                if(valid==need.size()){
                    return true;
                }
                char d=s2.charAt(left);
                left++;
                if(need.containsKey(d)){
                    if(need.get(d)== window.get(d)){
                        valid--;
                    }
                    window.put(d,window.get(d)-1);
                }

            }
        }
        return false;
    }
}

package leecode.string;

import zuoshen.输入输出练习.H;

import java.util.HashMap;
import java.util.Map;
//类似 205
public class 单词规律_290 {
    public boolean wordPattern(String pattern, String str) {
        String[] words=str.split(" ");
        char[]chars=pattern.toCharArray();
        Map<Character,String> map=new HashMap<>();

        for (int i = 0; i <chars.length ; i++) {
            char c=chars[i];
            if(map.containsKey(c)){
               if(!map.get(c).equals(words[i])){
                   return false;
               }
            }else {
                if(map.containsValue(words[i])){
                    return false;
                }else {
                    map.put(c,words[i]);
                }
            }
        }
        return true;
    }
}

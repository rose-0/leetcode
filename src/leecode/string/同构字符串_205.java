package leecode.string;

import zuoshen.输入输出练习.H;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//类似 209
public class 同构字符串_205 {
    public boolean isIsomorphic(String s, String t) {
        if(s.length()!=t.length()){
            return false;
        }
        Map<Character,Character>map=new HashMap<>();
        for (int i = 0; i <s.length() ; i++) {
            if(map.containsKey(s.charAt(i))){
                if(map.get(s.charAt(i))!=t.charAt(i)){
                    return false;
                }
            }else {
                // 这里也要反向判断 如 s=ab  t=cc    a b映射到同一个字符c上。
                if(map.containsValue(t.charAt(i))){
                    return false;
                }
                map.put(s.charAt(i),t.charAt(i));
            }
        }
        return true;
    }
}

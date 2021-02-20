package leecode.string;

import java.util.HashMap;
import java.util.Map;
//遇到子串问题，首先想到的就是滑动窗口技巧，双指针问题，解决该类问题的核心是双指针
// 找到字符串中所有字母异位词
//
//左神292（不好理解），看下面git博客
//https://github.com/labuladong/fucking-algorithm/blob/master/%E7%AE%97%E6%B3%95%E6%80%9D%E7%BB%B4%E7%B3%BB%E5%88%97/%E6%BB%91%E5%8A%A8%E7%AA%97%E5%8F%A3%E6%8A%80%E5%B7%A7.md
public class 最小覆盖子串_76 {
/*
滑动窗口框架
String s,t;
//在s中寻找t的最小覆盖字串
String res =s;
//right是初始化为0，不是长度减一，while 条件是 right<s.size 不是 left<right
while(right<s.size()){
    windows.add(s[right]);
    right++;
    while(windows符合要求）{
        res=Math.min(res,window);
        window.remove(s[left]);
        left++;
    }
}
return res;
 */
    public String minWindow(String s, String t) {
        //记录最短字串的开始位置和长度
        int start=0;
        int minlen=Integer.MAX_VALUE;
        //在s中寻找t的最小覆盖字串
        int left=0,right=0;
        String res=s;
        Map<Character,Integer>window=new HashMap<>();//window记录窗口相关的数据！！
        Map<Character,Integer>needs=new HashMap<>();
        for (int i = 0; i <t.length() ; i++) {
            //map.put(key, map.getOrDefault(key, 0) + 1)
            needs.put(t.charAt(i),needs.getOrDefault(t.charAt(i),0)+1);
        }
        int match=0;//记录windows中多少字符符合了
        while (right<s.length()){
            char c=s.charAt(right);
            if(needs.containsKey(c)){//先判断包不包含，再放进去
                window.put(c,window.getOrDefault(c,0)+1);
                if(window.get(c)==needs.get(c)){//出现次数符合要求了 !!
                    match++;
                }
            }
            right++;
            while (match==needs.size()){//所有字符的次数都符合要求了
                //更新res，先更新再处理left
                if(right-left<minlen){
                    start=left;
                    minlen=right-left;
                }
                char c2=s.charAt(left);
                if(needs.containsKey(c2)){//也要先判断
                    window.put(c2,window.get(c2)-1);
                    if(window.get(c2)<needs.get(c2)){
                        match--;
                    }
                }
                left++;
            }
        }
        return minlen==Integer.MAX_VALUE?"":s.substring(start,start+minlen);//确认一下
    }

    public String windows(String s,String t){
        int left=0;
        int right=0;
        int minlen=Integer.MAX_VALUE;
        int start=0;
        Map<Character,Integer>smap=new HashMap<>();
        Map<Character,Integer>tmap=new HashMap<>();
        for (int i = 0; i <t.length() ; i++) {
            tmap.put(t.charAt(i),tmap.getOrDefault(t.charAt(i),1)+1);
        }
        int match =0;
        while (right<s.length()){
            Character c=s.charAt(right);
            if(tmap.containsKey(c)){
                smap.put(c,smap.getOrDefault(c,0)+1);
                if(smap.get(c)==tmap.get(c)){
                    match++;
                }
            }
            right++;
            while (match==tmap.size()){
                if(right-left<minlen){
                    start=left;
                    minlen=Math.min(match,right-left);
                }
                Character m=s.charAt(left);
                if (tmap.containsKey(m)){
                    smap.put(m,smap.get(m)-1);
                    if(smap.get(m)<tmap.get(m)){
                        match--;
                    }
                }
                left++;
            }
        }
        return minlen==Integer.MAX_VALUE?"":s.substring(start,start+minlen);
    }


}

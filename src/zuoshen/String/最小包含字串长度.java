package zuoshen.String;

import java.util.HashMap;
import java.util.Map;

//p288
public class 最小包含字串长度 {
    public int minLength(String str1,String str2){
        char[]chars1=str1.toCharArray();
        char[]chars2=str2.toCharArray();
        Map<Character,Integer>map=new HashMap<>();
        for (int i = 0; i <chars2.length ; i++) {
            if(!map.containsKey(chars2[i])){
                map.put(chars2[i],1);
            }else {
                map.put(chars2[i],map.get(chars2[i])+1);
            }
        }
        int left =0;
        int right =0;
        int match=chars2.length;
        int minLen=Integer.MAX_VALUE;
        while (right!=chars1.length){
            map.put(chars1[right],map.get(chars1[right])-1);//先把最右边欠的数减去1
            if(map.get(chars1[right])>=0){//如果小于0，说明是多减去的
                match--;
            }
            if(match==0){//已经不欠了，看左边界能不能缩小
                while (map.get(chars1[left])<0){//左边有多欠的
                    map.put(chars1[left],map.get(chars1[left])+1);
                    left++;
                }
                minLen=Math.min(minLen,right-left+1);
                match++;//准备左边向右移动
                map.put(chars1[left],map.get(chars1[left])+1);
                left++;
            }
            right++;
        }
        return minLen==Integer.MAX_VALUE?0:minLen;
    }
}

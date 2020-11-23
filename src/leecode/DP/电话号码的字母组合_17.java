package leecode.DP;

import java.util.*;
//套用回溯算法的模板，注意map的初始化
public class 电话号码的字母组合_17 {
    public static List<String> letterCombinations(String digits) {
        HashMap<Character,String>map=new HashMap<>();
        map.put('2',"abc");
        map.put('3',"def");
        map.put('4',"ghi");
        map.put('5',"jkl");
        map.put('6',"mno");
        map.put('7',"pqrs");
        map.put('8',"tuv");
        map.put('9',"wxyz");
        List<String>res=new ArrayList<>();
        if(digits.length()==0)return res;
        StringBuilder str=new StringBuilder();
        huisu_method(digits,str,res,map,0);
        for (String s:res) {
            System.out.println(s);
        }
        return res;
    }
    public static void huisu_method(String digits,StringBuilder str,List<String>res,
                             HashMap<Character,String>map,int index){
        if(str.length()==digits.length()){
            String s=new String(str);
            res.add(s);
            return;
        }
        char[] tmp=map.get(digits.charAt(index)).toCharArray();

        for (Character c:tmp) {
            str.append(c);
            huisu_method(digits,str,res,map,index+1);
            str.deleteCharAt(str.length()-1);
        }
        return;
    }

    /*
    liweiwei题解：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/solution/hui-su-sou-suo-wu-xian-shi-hui-su-yan-du-you-xian-/

     */
    public List<String> letterCombinations2(String digits) {
        String[] charMap = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> res = new LinkedList<>();
        dfs(res, new String(), digits, charMap, 0);
        return res;
    }

    public void dfs(List<String> res, String path, String digits, String[] charMap, int begin) {
        if (begin == digits.length()) {
            res.add(path);
            return;
        }
        String mapString = charMap[digits.charAt(begin) - '2'];
        for (int i = 0; i < mapString.length(); i++) {
            /*
            错误：
            注意：使用string时，这里不能有状态重置，stringbuilder见上，
            这里每次加上mapString.charAt(i);递归完成之后没有撤销！！
            path = path + mapString.charAt(i);
            dfs(res, path, digits, charMap, begin + 1);

            由于字符追加到后面，是新创建一个对象，因此 **没有显式回溯（状态重置）的过程 **；
             */
            dfs(res, path+mapString.charAt(i), digits, charMap, begin + 1);

        }
    }


    public static void main(String[] args) {
        String res="23";
        letterCombinations(res);
    }
}

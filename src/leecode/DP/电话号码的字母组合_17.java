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
        for (String s:res
             ) {
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

        for (Character c:tmp
             ) {
            str.append(c);
            huisu_method(digits,str,res,map,index+1);
            str.deleteCharAt(str.length()-1);
        }
        return;
    }

    public static void main(String[] args) {
        String res="23";
        letterCombinations(res);
    }
}

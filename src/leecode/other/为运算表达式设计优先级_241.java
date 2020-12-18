package leecode.other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://leetcode-cn.com/problems/different-ways-to-add-parentheses/comments/
//suan-tou-wang-ba的评论
//优化dp：https://leetcode-cn.com/problems/different-ways-to-add-parentheses/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-5-5/
public class 为运算表达式设计优先级_241 {
    Map<String,List<Integer>>map=new HashMap<>();//使用备忘录优化递归
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer>list=new ArrayList<>();
        if(map.containsKey(input)){
            return map.get(input);
        }

         /*
        递归：以某个字符作为分隔符断开字符串，递归遍历左右两边
        做法类似构造二叉树，也是分割开来左右遍历，然后合并
        */
        for (int i = 0; i <input.length() ; i++) {
            char cur=input.charAt(i);
            //不是数字，那么按该符号位置分割
            if(!Character.isDigit(cur)){
                List<Integer> leftnum=diffWaysToCompute(input.substring(0,i));//注意返回的是list
                List<Integer> rightnum=diffWaysToCompute(input.substring(i+1,input.length()));
                //左右两边数值进行组合
                for(int left:leftnum){
                    for(int right:rightnum){
                        int num= cal(left,right,cur);
                        list.add(num);
                    }
                }
            }
        }
        ////如果 list 元素为 0，表示找不到符号进行分割，那么表示当前 input 就单单只是一个数字
        if(list.size()==0){
            list.add(Integer.valueOf(input));
        }
        map.put(input,list);
        return list;
    }
    private int cal(int num1,int num2,char opt){
        if(opt=='+'){
            return num1+num2;
        }else if(opt=='-'){
            return num1-num2;
        }else {
            return num1*num2;
        }
    }

    public static void main(String[] args) {
        System.out.println(Integer.valueOf(""));
    }
}

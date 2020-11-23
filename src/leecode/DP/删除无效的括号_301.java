package leecode.DP;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class 删除无效的括号_301 {
    /*
    具体的字符串给定后，就可以求出删除的左括号和右括号的最少数量

    java dfs 通过删 or 不删 进行dfs
    https://leetcode-cn.com/problems/remove-invalid-parentheses/solution/biao-zhun-de-dfs-fei-chang-biao-zhun-by-xiaoweixia/
    每次dfs index+1

    通过for循环 begin 变量进行dfs
    https://leetcode-cn.com/problems/remove-invalid-parentheses/solution/shen-du-you-xian-sou-suo-by-sunnyorange/

     */
    public List<String> removeInvalidParentheses(String s) {
        char[]chars=s.toCharArray();
        int leftNum=0;
        int rightNum=0;
        for(char c:chars){
            if(c=='('){
                leftNum++;
            }
            if(c==')'){
                if(leftNum>0){
                    leftNum--;
                }else {
                    rightNum++;
                }
            }
        }
        HashSet<String>set=new HashSet<>();
        dfs(s,leftNum,rightNum,0,set);
        return new ArrayList<>(set);
    }
    public void dfs(String s, int leftNum, int rightNum, int index, HashSet<String>set){
        if(leftNum==0&&rightNum==0){
            if(isValid(s)){
                //去重 还可以有其他方式
                //在for循环里面   if(i-1>=st&&s[i]==s[i-1])continue;
                set.add(s);
            }
        }
        /*
        这个题目相当于是个组合问题，以for循环的形式对每个字符进行处理
        也可以以选 or 不选 的方式解决该问题
         */
        for (int i = index; i <s.length() ; i++) {

            StringBuilder sb=new StringBuilder();

            //下面三句：将s的index处字符删掉，这个可以写到两个 if 里面，更好理解
            sb.append(s,0,i);
            sb.append(s.substring(i+1));
            String cur=sb.toString();

            if(leftNum>0&&s.charAt(i)=='('){
                //传进的是 cur,即将s的i位置处 ( 删除，注意这里传进去的index为i而不是i+1,因为cur位置处的i就是s的i+1位置
//                dfs(cur,leftNum-1,rightNum,i,set);
                dfs(s.substring(0,i)+s.substring(i+1),leftNum-1,rightNum,i,set);//或者这样写
            }else if(rightNum>0&&s.charAt(i)==')'){
//                dfs(cur,leftNum,rightNum-1,i,set);
                dfs(s.substring(0,i)+s.substring(i+1),leftNum,rightNum-1,i,set);
            }
            //如果字符 不是 左右括号 则继续for循环,相当于没有删除s的i位置上的字符
        }
    }
    public boolean isValid(String s){
        int count=0;
        for(char c:s.toCharArray()){
            if(c=='('){
                count++;
            }
            if(c==')'){
                count--;
            }
            if(count<0){
                return false;
            }
        }
        return count==0;
    }

    public static void main(String[] args) {
        String s="abc";
        for (int i = 0; i <3 ; i++) {
            System.out.println(s.substring(0,i)+s.substring(i+1));
        }
//        StringBuilder sb=new StringBuilder();
//        sb.append(s,0,0);
////        sb.append(s.substring(1));
//        System.out.println(s.substring(0,0)+s.substring(1,3));
    }
}

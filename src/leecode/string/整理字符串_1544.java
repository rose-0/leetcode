package leecode.string;

import java.util.Stack;

public class 整理字符串_1544 {
    public String makeGood(String s) {
        char[]chars=s.toCharArray();
        Stack<Character>stack=new Stack<>();
        for (int i = 0; i <s.length() ; i++) {
            char cur=chars[i];
            if(stack.isEmpty()){
                stack.push(cur);
            }else if(Math.abs(cur-stack.peek())==32){
                stack.pop();
            }else {
                stack.push(cur);
            }
        }
        String res="";
        while (!stack.isEmpty()){
            res+=stack.pop();
        }
        StringBuilder sb=new StringBuilder(res);
        return sb.reverse().toString();
    }
    public String makeGood2(String s) {
        StringBuilder sb=new StringBuilder(s);
        for (int i = 0; i <sb.length()-1 ; i++) {
            if(Math.abs(sb.charAt(i)-sb.charAt(i+1))==32){
                sb.delete(i,i+2);
                i=0;
            }else {
                i++;
            }
        }
        return sb.toString();
    }
}

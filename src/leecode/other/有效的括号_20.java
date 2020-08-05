package leecode.other;

import java.util.Stack;
//可以看labuladong的解法
public class 有效的括号_20 {//这个和左神的题目不一样，这个有三种括号，
    // 而且区分顺序，如"([)]"应该是false
    public static boolean isValid(String s){
        if(s.length()==0||s==null){
            return false;
        }
        char[]chars=s.toCharArray();
        int numone=0;
        int numtwo=0;
        int numthree=0;
        for (int i = 0; i <chars.length ; i++) {
            if(chars[i]==')'&&--numone<0){//两个&&不能写成一个&
                return false;
            }
            if(chars[i]==']'&&--numtwo<0) {
                return false;
            }

            if(chars[i]=='}'&&--numthree<0){
                return false;
            }
            if(chars[i]=='('){
                ++numone;
            }
            if(chars[i]=='['){
                ++numtwo;
            }
            if(chars[i]=='{'){
                ++numthree;
            }
        }
        return numone==0&&numtwo==0&&numthree==0;
    }

    public static boolean isValidtwo(String s){//正确的解法
        char[]chars=s.toCharArray();
        Stack<Character> stack=new Stack();
        for (char c:chars){
            if(c=='(')stack.push(')');
            else if(c=='[')stack.push(']');//一定要加else if否则第四个条件都要去判断，如（）返回false
            else if(c=='{')stack.push('}');
            else if(stack.isEmpty()||c!=stack.pop())return false;
        }
        return stack.isEmpty();
    }


    public boolean isValid1(String s) {//自己的解法

        char[]chars=s.toCharArray();
        Stack<Character>stack=new Stack<Character>();
        for (int i = 0; i <chars.length ; i++) {
            if (chars[i] == '(' || chars[i]=='['||chars[i]=='{') {
                stack.push(chars[i]);
            }
            if(chars[i]==')'){
                if(stack.empty()||stack.pop()!='('){//一定要加上为空的判断
                    return false;
                }
            }
            if(chars[i]==']'){
                if(stack.empty()||stack.pop()!='['){
                    return false;
                }
            }
            if(chars[i]=='}'){
                if(stack.empty()||stack.pop()!='{'){
                    return false;
                }
            }
        }
        return stack.empty();

    }
    public static void main(String[] args) {
        System.out.println(isValidtwo("()"));
    }
}

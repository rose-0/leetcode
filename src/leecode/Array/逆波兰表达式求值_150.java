package leecode.Array;

import java.util.Stack;

public class 逆波兰表达式求值_150 {
    public int evalRPN(String[]token){
        Stack<Integer>stack=new Stack<>();
        int i=0;
        while (i<token.length){
            switch (token[i]){
                case "+":
                    stack.push(stack.pop()+stack.pop());
                    break;
                case "-":
                    int num=stack.pop();// 3 2 -  (3-2 2在3上面)
                    stack.push(stack.pop()-num);
                    break;
                case "*":
                    stack.push(stack.pop()*stack.pop());
                    break;
                case "/":
                    int num2=stack.pop();
                    stack.push(stack.pop()/num2);
                    break;
                    default:
                        stack.push(Integer.valueOf(token[i]));
            }
            i++;//不要漏掉这个
        }
        return stack.pop();
    }
}

package leecode.other;

import java.util.Stack;

public class 验证栈序列_946 {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        /*考察堆栈，就用堆栈模拟，两个指针太麻烦
        Stack<Integer>stack=new Stack<>();
        int pop=0;
        int push=0;
        while (pop<popped.length){
            if(popped[pop]>pushed[push]){
                stack.push(pushed[push++]);
            }
            if(popped[pop]==pushed[push]){
                push++;
                pop++;
            }
        }
         */
        Stack<Integer>stack=new Stack<>();
        int j=0;
        for (int i = 0; i <pushed.length ; i++) {
            stack.push(pushed[i]);
            while (!stack.isEmpty()&&popped[j]==stack.peek()){
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }
}

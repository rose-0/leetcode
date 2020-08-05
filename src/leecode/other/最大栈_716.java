package leecode.other;

import java.util.Stack;

public class 最大栈_716 {
    public Stack<Integer>dataStack;
    public Stack<Integer>maxStack;
    public 最大栈_716(){
        dataStack=new Stack<>();
        maxStack=new Stack<>();
    }
    public void push(int num){
        if(this.maxStack.isEmpty()){
            maxStack.push(num);
        }else if(this.maxStack.peek()<num){
            this.maxStack.push(num);
        }else {
            int max=this.maxStack.peek();
            this.maxStack.push(max);
        }
        this.dataStack.push(num);
    }
    public int pop(){
        this.maxStack.pop();
        return this.dataStack.pop();
    }
    public int getMin(){
        return this.maxStack.peek();
    }
}

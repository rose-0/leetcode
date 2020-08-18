package 笔试代码.other;

import java.util.Stack;

public class 栈排序_0305 {
    private Stack<Integer> dataStack;
    private Stack<Integer> helpStack;

    public 栈排序_0305() {
        dataStack=new Stack<>();
        helpStack=new Stack<>();
    }

    public void push(int val) {
        /*  未优化的代码
        while (!dataStack.isEmpty()&&dataStack.peek()<val){
            helpStack.push(dataStack.pop());
        }
        dataStack.push(val);
        while (!helpStack.isEmpty()){
            dataStack.push(helpStack.pop());
        }
         */
        //优化的代码：采用惰性更新   连续多次push时，需要多次把元素在两个栈中传递。想象这样一个极端情况：
        //连续n次push相同或相近元素，需要来回操作2n x i次，其中i为每次移动的元素数量。
        if(dataStack.isEmpty()){
            dataStack.push(val);
        }else if(dataStack.peek()<val){
            while (!dataStack.isEmpty()&&dataStack.peek()<val){
                helpStack.push(dataStack.pop());
            }
            dataStack.push(val);
            //help里面的数据不立刻放回data里面
        }else if(helpStack.empty() || helpStack.peek() <= val){
            helpStack.push(val);
        }else {//data > val  help也 >val
            while (!helpStack.empty() && helpStack.peek() > val) {
                dataStack.push(helpStack.pop());
            }
            helpStack.push(val);
        }
    }

    public void pop() {
        //延迟到这里
        while (!helpStack.empty()) {
            dataStack.push(helpStack.pop());
        }
        if(!dataStack.isEmpty()){
            dataStack.pop();
        }
    }

    public int peek() {
        //延迟到这里
        while (!helpStack.empty()) {
            dataStack.push(helpStack.pop());
        }
        return dataStack.peek();
    }

    public boolean isEmpty() {
        return dataStack.isEmpty();
    }
}

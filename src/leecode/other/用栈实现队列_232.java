package leecode.other;

import java.util.Stack;

public class 用栈实现队列_232 {
    class MyQueue {
        public Stack<Integer>stackPush;
        public Stack<Integer>stackPop;
        /** Initialize your data structure here. */
        public MyQueue() {
            stackPop=new Stack<>();
            stackPush=new Stack<>();
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            stackPush.push(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            if(stackPush.empty()&&stackPop.empty()){
                throw new RuntimeException("empty");
            }else if(stackPop.empty()){
                while (!stackPush.empty()){
                    stackPop.push(stackPush.pop());
                }
            }
            return stackPop.pop();
        }

        /** Get the front element. */
        public int peek() {
            if(stackPush.empty()&&stackPop.empty()){
                throw new RuntimeException("empty");
            }else if(stackPop.empty()){
                while (!stackPush.empty()){
                    stackPop.push(stackPush.pop());
                }
            }
            return stackPop.peek();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            if(stackPop.empty()&&stackPush.empty()){
                return true;
            }else {
                return false;
            }
        }
    }

}

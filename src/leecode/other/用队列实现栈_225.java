package leecode.other;

import java.util.LinkedList;
import java.util.Queue;

//https://blog.csdn.net/weixin_43338519/article/details/97510825
public class 用队列实现栈_225 {
    class MyStack {
        public Queue<Integer> data;
        public Queue<Integer> help;
        /** Initialize your data structure here. */
        public MyStack() {
            data=new LinkedList<>();
            help=new LinkedList<>();
        }

        /** Push element x onto stack. */
        public void push(int x) {
            data.add(x);
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            if(data.isEmpty()){
                throw new RuntimeException("empty");
            }
            while (data.size()!=1){
                help.add(data.poll());
            }
            int res=data.poll();
//            help.add(res);
            swap();
            return res;
        }

        /** Get the top element. */
        public int top() {
            if(data.isEmpty()){
                throw new RuntimeException("empty");
            }
            while (data.size()!=1){
                help.add(data.poll());
            }
            int res=data.poll();
            help.add(res);//不要忘记这个
            swap();
            return res;
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            if(data.isEmpty()){
                return true;
            }else {
                return false;
            }
        }
        public void swap(){
            Queue<Integer> temp=help;
            help=data;
            data=temp;
        }
    }

}

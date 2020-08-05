package leecode.Array;

import java.util.Stack;

public class 将每个元素替换为右侧最大元素_1299 {
    public int[] replaceElements(int[]arr){
        int max=-1;
        for (int i = arr.length-1; i >=0 ; i--) {//从后向前排
            int temp=arr[i];
            arr[i]=max;
            if(max<temp){
                max=temp;
            }
        }
        return arr;
    }
    public static int[] replaceElements2(int[] arr) {//使用类似 单调栈的思想
        Stack<Integer>stack=new Stack<>();
        stack.push(-1);
        for (int i = arr.length - 1; i >=0; i--) {
            if(stack.peek()>arr[i]){
                stack.push(stack.peek());
            }else {
                stack.push(arr[i]);
            }
        }
        stack.pop();
        int[]ans=new int[arr.length];
        for (int i = 0; i <ans.length ; i++) {
            ans[i]=stack.pop();
        }
        return ans;
    }

}

package leecode.DP;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class 柱状图中最大的矩形_84 {
    /*
    暴力解法
     */
    public int largestRectangleArea(int[] heights) {
        int res = 0;
        for (int i = 0; i < heights.length; i++) {
            int curHeight = heights[i];
            int left = i;
            //找左边最后 1 个大于等于 heights[i] 的下标
            while (left > 0 && heights[left - 1] >= curHeight) {//注意边界条件left>0
                left--;
            }
            int right = i;
            // 找右边最后 1 个大于等于 heights[i] 的索引
            while (right < heights.length - 1 && heights[right + 1] >= curHeight) {//注意边界条件right<heights.length-1
                right++;
            }
            int width = right - left + 1;
            res = Math.max(res, curHeight * width);
        }
        return res;
    }

    /*
    使用单调栈结构
    保证入队的高度是递增的,
     */
    public int largestRectangleArea1(int[] heights) {
        Deque<Integer> stack=new ArrayDeque<>();
        int res=0;
        int len=heights.length;
        /*
        缓存 从左到右，计算结果从右到左 所以缓存的结构使用的是栈
        计算面积需要高度和宽度，宽度由下标决定，高度直接通过下标访问数组，所以栈里面只需要存下标即可
         */
        for (int i = 0; i <heights.length ; i++) {
            //入栈的时候要保证单调性，如果需要一个比它高度小的入栈，说明它不能向右扩展了，计算左边界就是宽度
            while (!stack.isEmpty()&&heights[stack.peekLast()]>heights[i]){
                int curHeight=heights[stack.pollLast()];
                //中间这些高度相等的矩形可以扩展的面积都是一样的
                while (!stack.isEmpty()&&heights[stack.peekLast()]==curHeight){
                    stack.pollLast();
                }

                int curWidth=0;
                if(stack.isEmpty()){
                    curWidth=i;//(i-1)-0+1
                }else {
                    curWidth=(i-1)-(stack.peekLast()+1)+1;
                }

                res=Math.max(res,curHeight*curWidth);
            }
            //判断完成再入栈
            stack.addLast(i);
        }
        /*
        一次遍历完成后，考虑还没有出栈的元素
        只不过这个时候右边没有比它高度还小的柱形了，
        想象这个时候计算宽度应该假设最右边还有一个下标为 len （这里等于 6） 的高度为 0 （或者 0.5，只要比 1 小）的柱形入栈
         */
        while (!stack.isEmpty()){
            int curHeight=heights[stack.pollLast()];
            while (!stack.isEmpty()&&heights[stack.peekLast()]==curHeight){
                stack.pollLast();
            }
            int curWidth=0;
            if(stack.isEmpty()){
                curWidth=len;//(len-1)-0+1
            }else {
                curWidth=len-1-(stack.peekLast()+1)+1;
            }

            res=Math.max(res,curHeight*curWidth);
        }
        return res;
    }
    /*
    优化方法二：
    1、弹栈的时候，栈为空；
    2、遍历完成以后，栈中还有元素；
    在输入数组的两端加上两个高度为 0 （或者是 0.5，只要比 1 严格小都行）的柱形，可以回避上面这两种分类讨论。
    即添加哨兵：
    1、左边的柱形（第 1 个柱形）由于它一定比输入数组里任何一个元素小，它肯定不会出栈，因此栈一定不会为空；
    2、右边的柱形（第 2 个柱形）也正是因为它一定比输入数组里任何一个元素小，它会让所有输入数组里的元素出栈（第 1 个哨兵元素除外）
     */

}
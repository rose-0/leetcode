package leecode.Array;

import java.util.Deque;
import java.util.LinkedList;

//使用双端队列的单调栈解法 图示 https://cloud.tencent.com/developer/article/1505345
//双端队列关键处文字说明 https://github.com/Shellbye/Shellbye.github.io/issues/41
// 和左神不一样的是，这个是和至少为k的最短，左神是和至少为k的最长！！
//因为数组存在负数，所以 区间增大时 前缀和可能减小
public class 和至少为k的最短子数组_862 {
    public static int shortestSubarray(int[] A, int K) {
        //直接前缀和，暴力求解
        int[]preSum=new int[A.length+1];
        /*
        preSum[]数组一定要多一个！！
        nums      3，5，2
        preSum 0，3，8，10
        preSum[i] [0..i-1]的和！！
         */
        for (int i = 0; i <A.length ; i++) {
            preSum[i+1]=preSum[i]+A[i];//
        }

        int min=Integer.MAX_VALUE;
        for (int i = 1; i <=A.length ; i++) {
            for (int j = 0; j <i ; j++) {
                if(preSum[i]-preSum[j]>=K){// [j,i-1]的和 >= K 时更新 min
                    min=Math.min(min,i-j);
                }
            }
        }
        return min==Integer.MAX_VALUE?-1:min;
    }
    //有了前缀和数组后，问题转化为 求差值为k的数组下标最小区间

    //双端队列，维护一个单调栈
    public static int shortestSubarraywithde(int[] A, int K) {
        int[]preSum=new int[A.length+1];
        for (int i = 0; i <A.length ; i++) {
            preSum[i+1]=preSum[i]+A[i];
        }
        int min=Integer.MAX_VALUE;
        //维护单调递增元素的下标，不是值
        Deque<Integer>deque=new LinkedList<>();
        deque.addLast(0);//尾部更新单调递增的下标
        for (int i = 0; i <preSum.length ; i++) {
            //队尾元素 比 当前i元素大，则队尾元素出队 因为 i 要入队，保证值单调递增

            // 把位于末尾没用的x1扔掉，当preSum[x2] <= preSum[x1]（其中x1 < x2）时，
            // 表明x1到x2之间的元素的和是负数或0，那么就是当preSum[xn] - preSum[x1] >= K
            // 则必然有preSum[xn] - preSum[x2] >= K
            while (!deque.isEmpty()&&preSum[deque.peekLast()]>=preSum[i]){//一定要加上不为空的判断，
                // 后面是>= 确保严格单增，相等取后面 这样数组长度更短
                deque.pollLast();
            }
            //队头元素的值是最小的

            //情况2是把指向前面的已经满足条件的x1的指针向后移动1位
            //在更新单调栈的时候就要判断K
            while (!deque.isEmpty()&&preSum[i]-preSum[deque.peekFirst()]>=K){
                min=Math.min(min,i-deque.peekFirst());
                deque.pollFirst();
            }
            deque.addLast(i);//最后再加
        }
        return min==Integer.MAX_VALUE?-1:min;
    }



        public static void main(String[] args) {
        int[]arr={2,-1,2};
        System.out.println(shortestSubarray(arr,3));
    }
}

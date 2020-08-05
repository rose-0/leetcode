package leecode.other;


import java.util.Comparator;
import java.util.PriorityQueue;

public class 数据流的中位数_295 {
    private int count;
    private PriorityQueue<Integer> maxHeap;
    private PriorityQueue<Integer> minHeap;
    public 数据流的中位数_295(){
        count=0;
        maxHeap=new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        minHeap=new PriorityQueue<>();
    }
    public void addNum(int num){
        count=count+1;
        //记得先 大顶堆 再小顶堆！！
        maxHeap.offer(num);
        //保证大顶堆元素小于小顶堆
        minHeap.add(maxHeap.poll());

        if(count%2!=0){//奇数，最大堆要多一个
            maxHeap.add(minHeap.poll());
        }
    }
    public double findMedian(){
        if(count%2==0){
            return (double)(maxHeap.peek()+minHeap.peek())/2;
        }else {
            return (double)maxHeap.peek();
        }
    }
}

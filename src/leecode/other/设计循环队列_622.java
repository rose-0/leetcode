package leecode.other;

public class 设计循环队列_622 {
    class MyCircularQueue {
        private int[]arr;
        private int size;//实现头尾指针的解耦！
        private int front;//队首是0的位置，因为数组只能顺序存放，后来的放在index大的地方
        private int end;//指向第一个空缺的位置
        /** Initialize your data structure here. Set the size of the queue to be k. */
        public MyCircularQueue(int k) {
            this.arr=new int[k];
            this.size=0;
            this.front=0;
            this.end=0;

        }

        /** Insert an element into the circular queue. Return true if the operation is successful. */
        public boolean enQueue(int value) {
            if(size==arr.length){
                return false;
            }
            arr[end]=value;
            size++;//更新size！！
            end=end==arr.length-1?0:end+1;
            return true;
        }

        /** Delete an element from the circular queue. Return true if the operation is successful. */
        public boolean deQueue() {
            if(size==0){
                return false;
            }
            //不删数，只移动下标，改变指针即可
            front=front==arr.length-1?0:front+1;
            size--;
            return true;
        }

        /** Get the front item from the queue. */
        public int Front() {
            if(size==0){
                return -1;
            }
            return arr[front];
        }

        /** Get the last item from the queue. */
        public int Rear() {
            if(size==0){
                return -1;
            }
            return end==0?arr[arr.length-1]:arr[end-1];//注意这里end-1
        }

        /** Checks whether the circular queue is empty or not. */
        public boolean isEmpty() {
            return size==0;
        }

        /** Checks whether the circular queue is full or not. */
        public boolean isFull() {
            return size==arr.length;
        }
    }
}

package 多线程.生产者消费者模式.Condition方法;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerByLock {
    private static final int CAPACITY=5;
    private static final Lock lock=new ReentrantLock();

    private static final Condition fullCondition = lock.newCondition();
    private static final Condition emptyCondition = lock.newCondition();

    public static void main(String[] args) {
        Queue<Integer>queue=new LinkedList<>();
        Thread produce1=new Producer("p1",queue,CAPACITY);
        Thread produce2=new Producer("p2",queue,CAPACITY);
        Thread consumer1=new Consumer("c1",queue,CAPACITY);
        Thread consumer2=new Consumer("c2",queue,CAPACITY);
        Thread consumer3=new Consumer("c3",queue,CAPACITY);

        produce1.start();
        produce2.start();
        consumer1.start();
        consumer2.start();
        consumer3.start();
    }

    public static class Producer extends Thread{
        private Queue<Integer>queue;
        String name;
        int maxSize;
        int i=0;

        public Producer(String name,Queue<Integer>queue,int maxSize){
            super(name);
            this.name=name;
            this.queue=queue;
            this.maxSize=maxSize;
        }
        @Override
        public void run(){
            while (true){
                lock.lock(); //获得锁
                while (queue.size()==maxSize){
                    System.out.println("队列满了，生产者 "+ name +" 等待");
                    try {
                        fullCondition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("生产者 "+ name+ "生产 "+i);
                queue.offer(i++);
                //唤醒所有生产者，消费者
                fullCondition.signalAll();
                emptyCondition.signalAll();
                //释放锁
                lock.unlock();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static class Consumer extends Thread{
        private Queue<Integer>queue;
        String name;
        int maxSize;
        public Consumer(String name,Queue<Integer>queue,int maxSize){
            super(name);
            this.name=name;
            this.queue=queue;
            this.maxSize=maxSize;
        }
        @Override
        public void run(){
            while (true){
                lock.lock();

                while (queue.isEmpty()){
                    System.out.println("队列为空 消费者"+name+" 等待");
                    try {
                        emptyCondition.await();
//                        emptyCondition.wait(); //这样就错了 这是object的wait方法
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int x=queue.poll();
                System.out.println("消费者 "+name+"消费了"+x);

                fullCondition.signalAll();
                emptyCondition.signalAll();

                lock.unlock();

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }



        }
    }
}

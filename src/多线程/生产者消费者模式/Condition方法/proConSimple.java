package 多线程.生产者消费者模式.Condition方法;


import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//不带打印信息，比较清晰一些
public class proConSimple {
    //循环打印abc代码实现中，lock、emptyCondition、fullCondition都是构造函数传参初始化
    private static final Lock lock=new ReentrantLock();
    private static final Condition emptyCondition=lock.newCondition();
    private static final Condition fullCondition=lock.newCondition();

    public static class producer extends Thread{
        int i=0;
        Queue<Integer>queue;
        String name;
        int maxSize;

        public producer(String name, Queue<Integer>queue,int maxSize){
            this.name=name;
            this.queue=queue;
            this.maxSize=maxSize;
        }
        @Override
        public void run(){
            while (true){ //注意这里 死循环
                lock.lock();
                while (queue.size()==maxSize){
                    try {
                        fullCondition.await();

                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                queue.offer(i++);
                emptyCondition.signalAll();
                lock.unlock();
            }
        }
    }

    public static class consumer extends Thread{
        private Queue<Integer>queue;
        String name;
        int maxSize;//消费者这个属性没有用
        public consumer(String name, Queue<Integer>queue,int maxSize){
            this.name=name;
            this.maxSize=maxSize;
            this.queue=queue;
        }
        @Override
        public void run(){
            while (true){
                lock.lock();
                while (queue.isEmpty()){
                    System.out.println("队列为空，消费者"+name+" 等待");
                    try {
                        emptyCondition.await();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                int x=queue.poll();
                System.out.println("消费者 "+name+"消费"+x);

                fullCondition.signal();
//                emptyCondition.signal(); 不写这个也可以

                lock.unlock();
                try {
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Queue<Integer>queue=new LinkedList<>();
        int capcity=5;
        Thread producer1=new producer("p1",queue,capcity);
        Thread producer2=new producer("p2",queue,capcity);
        Thread consumer1=new consumer("c1",queue,capcity);
        Thread consumer2=new consumer("c2",queue,capcity);
        Thread consumer3=new consumer("c3",queue,capcity);

        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();
        consumer3.start();
    }
}


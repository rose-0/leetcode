package 多线程.生产者消费者模式.BlockingQueue方法;

import org.omg.CORBA.PERSIST_STORE;

import java.util.concurrent.LinkedBlockingDeque;
//看这个 package 多线程.生产者消费者模式.非循环实现.blockqueue 类是怎么实现的
public class ProducerConsumerByBQ {
    private static final int CAPACITY=1;

    public static void main(String[] args) {
        LinkedBlockingDeque<Integer> blockingDeque =new LinkedBlockingDeque<>(1);
        //开了两个Producer线程，每个线程都是从0开始生产元素，从运行结果可以看出，第一次 p1，p2都是生产了0；
        Thread producer1=new Producer("P1",blockingDeque,CAPACITY);
        Thread producer2=new Producer("P2",blockingDeque,CAPACITY);

        Thread consumer1=new Consumer("C1",blockingDeque,CAPACITY);
        Thread consumer2=new Consumer("C2",blockingDeque,CAPACITY);
        Thread consumer3=new Consumer("C3",blockingDeque,CAPACITY);

        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();
        consumer3.start();

    }


    public static class Producer extends Thread{
        private LinkedBlockingDeque<Integer>blockingDeque;
        String name;
        int maxSize;
        int i=0;
        public Producer(String name,LinkedBlockingDeque<Integer>blockingDeque,int maxSize){
            super(name);
            this.name=name;
            this.blockingDeque=blockingDeque;
            this.maxSize=maxSize;
        }
        @Override
        public void run(){
            while (true){
                try {
                    blockingDeque.put(i);
                    System.out.println("生产者 "+name+"生产"+i);
                    i++;
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static class Consumer extends Thread{
        private LinkedBlockingDeque<Integer> blockingDeque;
        String name;
        int maxSize;
        public Consumer(String name,LinkedBlockingDeque<Integer>blockingDeque,int maxSize){
            super(name);
            this.name=name;
            this.blockingDeque=blockingDeque;
            this.maxSize=maxSize;
        }
        @Override
        public void run(){
            while (true) {
                try { //加上while true就能一直消费，不加的话，一次就停止了
                    int x = blockingDeque.take();
                    System.out.println("消费者" + name + "消费了" + x);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

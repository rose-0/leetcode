package 多线程.生产者消费者模式.非循环实现;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class blockqueue {
    private static Integer count=0;
    static final BlockingQueue blockingQueue=new ArrayBlockingQueue(10);

    static class Producer extends Thread{

        @Override
        public void run() {
//            for (int i = 0; i <10 ; i++) {
                while (true) {//用while true就是一直生产
                    try {
                        Thread.sleep(1000);//每隔10s 放一个
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        System.out.println(Thread.currentThread().getName() + "生产者准备生产，目前共有" + blockingQueue.size());
//                        blockingQueue.put(1);
                        blockingQueue.put(new Object());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "生产者在生产，目前共有" + blockingQueue.size());
            }
        }
    }

    static class Consumer extends Thread{

        @Override
        public void run() {
//            for (int i = 0; i <10 ; i++) {
            while (true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    System.out.println(Thread.currentThread().getName()+"消费者准备消费，当前有"+blockingQueue.size());
                    blockingQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"消费者消费，当前有"+blockingQueue.size());
            }
        }
    }

    public static void main(String[] args) {
        Thread producer1=new Producer();
        Thread producer2=new Producer();
        Thread consumer1=new Consumer();
        Thread consumer2=new Consumer();
        producer1.start();
        consumer1.start();
        producer2.start();
        consumer2.start();

    }
}

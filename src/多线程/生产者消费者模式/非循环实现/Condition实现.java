package 多线程.生产者消费者模式.非循环实现;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Condition实现 {
    private static Integer count=0;
    private static final Integer full=10;
    private Lock lock=new ReentrantLock();

    private final Condition notFull =lock.newCondition();
    private final Condition notEmpty =lock.newCondition();

    public static void main(String[] args) {
        Condition实现 condition实现=new Condition实现();
        new Thread(condition实现.new Producer()).start();
        new Thread(condition实现.new Consumer()).start();

        new Thread(condition实现.new Producer()).start();

    }

    class Producer implements Runnable{

        @Override
        public void run() {
            for (int i = 0; i <10 ; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.lock();
                while (count==full){
                    try {
                        notFull.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                count++;
                System.out.println(Thread.currentThread().getName()+"在生产，目前有"+count);
                notEmpty.signal();
                lock.unlock();
            }
        }
    }

    class Consumer implements Runnable{

        @Override
        public void run() {
            for (int i = 0; i <10 ; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.lock();
                while (count==0){
                    try {
                        notEmpty.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                count--;
                System.out.println(Thread.currentThread().getName()+"消费者消费，目前有"+count);
                notFull.signal();
                lock.unlock();
            }
        }
    }
}

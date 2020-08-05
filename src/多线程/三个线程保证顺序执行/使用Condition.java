package 多线程.三个线程保证顺序执行;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class 使用Condition {
    private static Lock lock=new ReentrantLock();
    private static Condition condition1=lock.newCondition();
    private static Condition condition2=lock.newCondition();
    private static boolean t1Run=false;
    private static boolean t2Run=false;

    public static void main(String[] args) {
        final Thread thread1=new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                System.out.println("1");
                t1Run=true;
                condition1.signal();
                lock.unlock();
            }
        });
        final Thread thread2=new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                if(!t1Run){
                    try {
                        condition1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("2");
                condition2.signal();
                lock.unlock();
            }
        });
//        final Thread thread3=new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        })
    }
}

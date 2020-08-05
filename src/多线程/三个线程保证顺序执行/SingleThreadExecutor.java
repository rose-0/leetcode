package 多线程.三个线程保证顺序执行;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutor {
    public static void main(String[] args) {
        final Thread thread1=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(1);
            }
        });
        final Thread thread2=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(2);
            }
        });
        final Thread thread3=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(3);
            }
        });
        ExecutorService executorService= Executors.newSingleThreadExecutor();
        executorService.submit(thread2);
        executorService.submit(thread1);
        executorService.submit(thread3);
        executorService.shutdown();
    }
}

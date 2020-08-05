package 多线程.线程池;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
//Callable+Future/FutureTask却可以获取多线程运行的结果，
public class callable示例 {
    public static void main(String[] args) {
        /*
        public interface Callable<V> {
            V call() throws Exception;
        }
        public interface Runnable {
            public abstract void run();
        }
         */
        Callable<Double>callable=new Callable<Double>() {
            @Override
            public Double call() throws Exception {
                return new Random().nextDouble();
            }
        };
        FutureTask<Double>futureTask=new FutureTask<Double>(callable);
        new Thread(futureTask).start();
        System.out.println(futureTask.isDone());
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(futureTask.isDone());
    }
}

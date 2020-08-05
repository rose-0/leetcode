package 多线程.线程池;
//https://www.jianshu.com/p/f030aa5d7a28
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class 自定义线程池 {
    static class MyTask implements Runnable {
        private String name;

        public MyTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                System.out.println(this.toString() + " is running!");
                Thread.sleep(3000); //让任务执行慢点
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "MyTask [name=" + name + "]";
        }
    }
    public static void main(String[] args) {
        BlockingQueue<Runnable>workQueue=new ArrayBlockingQueue<>(2);
        /*自定义拒绝策略
        RejectedExecutionHandler rejectedExecutionHandler=new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

            }
        }
         */
        //使用自带的
        RejectedExecutionHandler rejectedExecutionHandler=new ThreadPoolExecutor.AbortPolicy();
        //创建线程工厂,使用它创建线程
        ThreadFactory threadFactory= new ThreadFactory() {
            public final AtomicInteger num=new AtomicInteger(1);
            @Override
            public Thread newThread(Runnable r) {
                Thread t=new Thread(r,"mythread"+num.getAndIncrement());
                System.out.println("创建了"+t.getName());
                return t;
            }
        };
        //创建自定义线程池
        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(2,4,
                10, TimeUnit.SECONDS,workQueue,threadFactory,rejectedExecutionHandler);
        /*或者使用Executors创建
        ThreadPoolExecutor threadPoolExecutor=Executors.newSingleThreadExecutor();
         */
        for (int i = 1; i <= 10; i++) {
            MyTask task = new MyTask(String.valueOf(i));//创建线程交给线程池
            threadPoolExecutor.execute(task);
        }
    }
}

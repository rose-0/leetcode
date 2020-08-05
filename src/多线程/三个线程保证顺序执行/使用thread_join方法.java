package 多线程.三个线程保证顺序执行;
//https://blog.csdn.net/difffate/article/details/63684290
//https://www.cnblogs.com/wenjunwei/archive/2019/03/25/10573289.html 8种方法
public class 使用thread_join方法 {
    public static void main(String[] args) {
        Thread previous=Thread.currentThread();
        //这里写了一个类
        Thread T1=new Thread(new mythread(previous),String.valueOf(1));
        Thread T2=new Thread(new mythread(T1),String.valueOf(2));
        Thread T3=new Thread(new mythread(T2),String.valueOf(3));
        T1.start();
        T2.start();
        T3.start();

    }
    static class mythread implements Runnable{
        private Thread thread;
        public mythread(Thread thread){
            this.thread=thread;
        }
        @Override
        public void run() {
            try {
                thread.join();
//                System.out.println(thread.getName()+"ter");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(thread.currentThread().getName()+" ter");
//System.out.println(thread.currentThread().getName()+" ter");//thread.currentThread()返回一个实例，这个实例是当前执行代码Thread 的引用
        }
    }
}

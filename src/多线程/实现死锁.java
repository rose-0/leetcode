package 多线程;

public class 实现死锁 {
    private static Object lock1=new Object();
    private static Object lock2=new Object();
    public static class Thead1 implements Runnable {

        @Override
        public void run() {
            synchronized (lock1){
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程1，等待lock2");
                synchronized (lock2){
                    System.out.println("线程1完成");
                }
            }
        }
    }
    public static class Thead2 implements Runnable{
        @Override
        public void run() {
            synchronized (lock2) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程2，等待lock1");
                synchronized (lock1) {
                    System.out.println("线程1完成");
                }
            }
        }
    }

    public static void main(String[] args) {
         Thead1 thread1 =new Thead1();
        Thead2 thead2 = new Thead2();
        Thread thread11=new Thread(thread1);
        Thread thread22=new Thread(thead2);
        thread11.start();
        thread22.start();
    }
}

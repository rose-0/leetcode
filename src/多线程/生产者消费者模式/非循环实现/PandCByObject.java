package 多线程.生产者消费者模式.非循环实现;

public class PandCByObject {
    private static Integer count =0;
    private static final Integer FULL=10;
    private static String Lock="lock";

    public static void main(String[] args) {
        PandCByObject pandCByObject=new PandCByObject();
        new Thread(pandCByObject.new Producer()).start();
        new Thread(pandCByObject.new Consumer()).start();

        new Thread(pandCByObject.new Producer()).start();
        new Thread(pandCByObject.new Consumer()).start();

        new Thread(pandCByObject.new Producer()).start();
        new Thread(pandCByObject.new Consumer()).start();

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
                synchronized (Lock){
                    while (count==FULL){
                        try {
                            Lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count++;
                    System.out.println("生产者："+Thread.currentThread().getName()+"一共生产"+count);
                    Lock.notifyAll();
                }
            }
        }
    }

    class Consumer implements Runnable{

        @Override
        public void run() {
            for (int i = 0; i <10 ; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (Lock){
                    while (count==0){
                        try {
                            Lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count--;
                    System.out.println("消费者："+Thread.currentThread().getName()+"此时count："+count);
                    Lock.notifyAll();
                }
            }
        }
    }

}

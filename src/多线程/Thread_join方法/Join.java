package 多线程.Thread_join方法;//join方法还有超时特性方法，如果设定时间内没有终止，则在时间结束时候返回
//java并发编程的艺术104页
//0利用main构造，0等待main结束返回
//1利用0构造，1等待0
//结束顺序:main 0 1 ....9
public class Join {
    public static void main(String[] args) {
        Thread previous = Thread.currentThread();
        for (int i = 0; i <10 ; i++) {
            Thread thread = new Thread(new Domino(previous),String.valueOf(i));
            thread.start();
            previous=thread;
        }
        System.out.println("main");
    }
    static class Domino implements Runnable{
        private Thread thread;
        public Domino(Thread thread){
            this.thread=thread;
        }
        @Override
        public void run() {
            try{
                thread.join();//构造Domino时候传入一个线程，当前线程Domino等待thread线程执行完毕后才从thread.join方法返回
            }catch (InterruptedException e){

            }
            System.out.println(Thread.currentThread().getName()+" terminate");
        }

    }
}

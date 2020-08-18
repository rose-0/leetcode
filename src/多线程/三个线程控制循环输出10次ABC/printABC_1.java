package 多线程.三个线程控制循环输出10次ABC;
//https://www.jianshu.com/p/34e9f04deeec
public class printABC_1 {
    private static final int print_a=0;
    private static final int print_b=1;
    private static final int print_c=2;
    public static class MyThread extends Thread{
        int which;//which代表线程0？1？2？
        static volatile int state;//静态初始值为0
        static final Object t=new Object();//这个用来当做锁
        public MyThread(int which){
            this.which=which;
        }
        @Override//刚开始state为0，
        public void run(){
            for (int i = 0; i <10 ; i++) {
                synchronized (t) {
                    while (state % 3 != which) {//state为0时，如果是线程0，不进入循环，线程1则进入
                        try {//循环，这个线程进入循环后等待，释放锁，这样才可能使得线程0获得锁
                            t.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(which);
                    state++;
                    t.notifyAll();
                }
            }
        }
    }
    public static class mythread_1 extends Thread{
        static final Object lock=new Object(); //static保证三个对象使用的是一个锁
        int which;
        static volatile int state=1; //static保证改变的都是一个值，volatile保证改变可见
        public mythread_1(int which){
            this.which=which;
        }
        @Override
        public void run(){
            synchronized (lock){
                for (int i = 0; i <10 ; i++) {
                    while (state%3!=which){
                        try {
                            lock.wait();//object的wait方法
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(which);
                    state++;
                    lock.notifyAll();//一定要用all，而不是notify
                }
            }
        }
    }
    public static void main(String[] args) {//一定要传入一个0号线程，因为state初始化为0，一定要有个线程执行它
        //不能state初始化为1，传入1，2，3，因为这样3号mod3为0，不等于自己的线程号，输不出来
//        new MyThread(print_a).start();
//        new MyThread(print_b).start();
//        new MyThread(print_a).start();
//        new MyThread(print_c).start();
        new mythread_1(1).start();
        new mythread_1(2).start();
        new mythread_1(0).start();
    }
}

package 多线程.多线程循环打印;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

//三个线程循环打印abc
//https://www.cnblogs.com/xiaoxi/p/8035725.html
//为什么释放锁被注释掉也可以，看这个博客的demo，结合原理
//https://blog.csdn.net/HEYUTAO007/article/details/49889849
public class PrinterABC implements Runnable{
    private static final int PRINT_COUNT=10;
    private final ReentrantLock reentrantLock;//这个不用static
    private final Condition thisCondition;//condition也不能使用static修饰，
    // 如果变成全局性的，则后面覆盖前面，导致前面失效，死锁，不使用final修饰好像也可以
    private  final Condition nextCondition;
    private    char printChar;//printChar 不能用static修饰！！！
    // 如果使用static修饰，则后面初始化的字符会覆盖前面的字符！！导致所有字符都一样，static是全局性的
//    private static final int PRINT_COUNT=10;
//    private final ReentrantLock reentrantLock;
//    private final Condition thisCondition;
//    private final Condition nextCondition;
//    private  char printChar;

    public PrinterABC(ReentrantLock reentrantLock, Condition thisCondition, Condition nextCondition, char printChar) {
        this.reentrantLock = reentrantLock;
        this.thisCondition = thisCondition;
        this.nextCondition = nextCondition;
        this.printChar = printChar;
    }

    @Override
    public void run() {
        reentrantLock.lock();
        try {
            for (int i = 0; i <PRINT_COUNT ; i++) {
                System.out.print(printChar);
                printChar++;
                printChar++;
                nextCondition.signal(); //下一个打印唤醒，先唤醒，再await
                if(i<PRINT_COUNT-1){//i=PRINT_COUNT-1时候，说明是最后一次了，不需要wait，
                    // 如果最后一次wait的话，因为没有线程执行了。所以一直死锁，非最后一次就要wait
                    thisCondition.await();
                }
//                thisCondition.await(); //当前打印等待，   不清楚需不需要加判断，原博文有写
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            reentrantLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock=new ReentrantLock();
        Condition a=lock.newCondition();
        Condition b=lock.newCondition();
        Condition c=lock.newCondition();
        Thread printa=new Thread(new PrinterABC(lock,a,b,'1'));
        Thread printb=new Thread(new PrinterABC(lock,b,a,'2'));
//        Thread printc=new Thread(new PrinterABC(lock,c,a,'c'));
        //start（）不能保证先后顺序，要使用join方法！！
        printa.start();//如果改成run方法是不可以的！！见startVsrun
//        Thread.sleep(100);
        printb.start();
//        printc.start();
    }
}

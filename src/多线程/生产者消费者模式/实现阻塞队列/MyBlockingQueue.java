package 多线程.生产者消费者模式.实现阻塞队列;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
//https://juejin.im/post/5df8f1776fb9a015fe568013
public class MyBlockingQueue<E> {
    private static int DEFAULT_CAPITY=10;
    private ReentrantLock lock=new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();
    private E[] elements;
    private int capcity;
    private int size;
    private int head;//取元素的下标 take
    private int tail;//放元素的小标 put
    //不传的话，设置默认容量
    public MyBlockingQueue(){
        this(DEFAULT_CAPITY);
    }
    public MyBlockingQueue(int capcity){
        this.capcity=capcity;
        elements=(E[])new Object[capcity];
    }
    public void put(E e){
        lock.lock();
        try {
            while (size==capcity){
                    notFull.await();//等待不满
                }
                    elements[tail]=e;
                    ++size;
                    ++tail;
                    tail=tail==capcity?0:tail;//如果tail走到尾部，则指向0，从头开始放
            } catch (InterruptedException ex) {
                    ex.printStackTrace();
            } finally {
                    notEmpty.signal();//不空了
                    lock.unlock();
            }
    }
    public E take() {
        lock.lock();
        E e = (E)new Object();
        try {
            while (size == 0) {
                notEmpty.await();//等待不空
            }
            --size;
             e = elements[head++];
            head = head == capcity ? 0 : head;
        }catch(InterruptedException exception){
            exception.printStackTrace();
        }finally {
            notFull.signalAll();//已经不满了
            lock.unlock();
        }
        return e;
    }
}

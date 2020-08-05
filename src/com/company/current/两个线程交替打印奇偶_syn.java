package com.company.current;

public class 两个线程交替打印奇偶_syn {
    public synchronized void print1(){
        for (int i = 0; i <=100; i+=2) {
            this.notify();
            System.out.println("线程1："+i);
            try {
                Thread.sleep(100);
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
    public synchronized void print2(){
        for (int i = 1; i <=100 ; i+=2) {
            this.notify();
            System.out.println("线程2 "+i);
            try {
                Thread.sleep(100);
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }

    public static void main(String[] args) {
        两个线程交替打印奇偶_syn demo=new 两个线程交替打印奇偶_syn();
        Thread t1=new Thread(demo::print1);
        Thread t2=new Thread(demo::print2);
        t1.start();
        t2.start();
    }
}

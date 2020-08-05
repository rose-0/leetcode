package com.company.current;

import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.logging.SimpleFormatter;

public class ThreadLocalExample implements Runnable{
    private static final ThreadLocal<SimpleDateFormat>formatter=ThreadLocal.withInitial(()->new SimpleDateFormat("yyyyMMdd HHmm"));

    public static void main(String[] args) throws Exception{
        ThreadLocalExample obj=new ThreadLocalExample();
        for (int i = 0; i <10 ; i++) {
            Thread t=new Thread(obj,""+i);
            Thread.sleep(new Random().nextInt(1000));
            t.start();
        }
    }
    @Override
    public void run() {
        System.out.println("name="+Thread.currentThread().getName()+"defult"+formatter.get().toPattern());
        try {
            Thread.sleep(new Random().nextInt(10000));
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        formatter.set(new SimpleDateFormat());
        System.out.println("name="+Thread.currentThread().getName()+"formatter"+formatter.get().toPattern());

    }
}

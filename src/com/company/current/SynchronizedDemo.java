package com.company.current;

public class SynchronizedDemo {
    public void method(){
        synchronized (this){
            System.out.println("demo");
        }
    }
}

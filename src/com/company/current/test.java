package com.company.current;

import java.util.concurrent.*;

public class test {
    public static void main(String[] args) throws InterruptedException , Exception {

    }
}
class TestThread extends Thread{
    @Override
    public void run(){
       int hash=Sington.getUniqueInstance().hashCode();
        System.out.println(hash);
   }
}

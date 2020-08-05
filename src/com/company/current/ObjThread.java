package com.company.current;

public class ObjThread extends Thread {
    LockTestClass lock;
    int i=0;
    public ObjThread(LockTestClass lock,int i){
        this.lock=lock;
        this.i=i;
    }
    public void run(){
//        lock.noSynMethod(this.getId(),this);
        lock.synInMethod();
//        lock.synOnMethod();
//        lock.synMethodWithObj();
        LockTestClass.increment();
    }
}

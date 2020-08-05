package com.company.current;

public class LockTestClass {
    private static int i=0;
    private Object object=new Object();
    public void noSynMethod(long threadID,ObjThread thread){
        System.out.println("nosyn: class obj is "+thread+",ID is"+threadID);
    }
    public synchronized void synOnMethod(){
        System.out.println("synOnMethod begin"+System.currentTimeMillis()+"ms");
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("synOnMethod ends");
    }
    public void synInMethod(){
        synchronized (this){
            System.out.println("synInMethod begin"+System.currentTimeMillis()+"ms");
            try {
                Thread.sleep(2000L);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("synInMethod ends");
        }
    }
    public void synMethodWithObj(){
        synchronized (object){
            System.out.println("synMethodWithObj begins"+System.currentTimeMillis()+"ms");
            try{
                Thread.sleep(2000L);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("synInMethod ends");
        }
    }
    public static synchronized void increment(){
        System.out.println("class synchronized i="+i+",time = "+
                System.currentTimeMillis()+"ms");
        i++;
        try {
            Thread.sleep(2000L);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("ends");
    }
}

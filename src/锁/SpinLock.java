package 锁;

import java.util.concurrent.atomic.AtomicReference;

public class SpinLock {
    private AtomicReference<Thread>cas=new AtomicReference<>();
    private int count;
    public void lock(){
        Thread current=Thread.currentThread();
        if(current==cas.get()){
            count++;
            return;
        }
        while (!cas.compareAndSet(null,current)){

        }
    }
    public void unlock(){
        Thread current=Thread.currentThread();
        if(current==cas.get()){
            if(count>0){
                count--;
            }else {
            cas.compareAndSet(current,null);
            }
        }

    }
}

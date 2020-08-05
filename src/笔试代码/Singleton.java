package 笔试代码;

public class Singleton {
    private static volatile Singleton singleton;
    private Singleton(){}
    public Singleton getSingleton(){
        if(singleton==null){
            synchronized (Singleton.class){
                if(singleton==null){
                    singleton=new Singleton();
                }
            }
        }
        return singleton;
    }
}

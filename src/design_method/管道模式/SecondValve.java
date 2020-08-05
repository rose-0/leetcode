package design_method.管道模式;

public class SecondValve implements Valve {
    public Valve next;
    public Valve getNext() {
        return next;
    }

    public void setNext(Valve v) {
        next = v;
    }

    public void invoke(String s) {
        System.out.println("执行SecondValve阀门，并掉调用下一个阀门");
        //注意这行代码
        getNext().invoke(s);
    }
}

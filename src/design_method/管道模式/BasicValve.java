package design_method.管道模式;

public class BasicValve implements Valve {

    protected Valve next;

    public Valve getNext() {
        return next;
    }

    public void setNext(Valve valve) {
        next = valve;
    }

    public void invoke(String s) {

        System.out.println("调用基础阀门");
    }
}

package design_method.管道模式;

public interface Valve {
    public Valve getNext();

    public void setNext(Valve v);

    public void invoke(String s);
}

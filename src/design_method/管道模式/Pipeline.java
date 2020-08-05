package design_method.管道模式;

public interface Pipeline {
    public Valve getFirst();

    public Valve getBasic();

    public void setBasic(Valve v);

    public void addValve(Valve v);
}

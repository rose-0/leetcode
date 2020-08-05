package design_method.管道模式;

public class StandardPipeline implements Pipeline {
    //第一个阀门
    protected Valve first;
    //基础阀门
    protected Valve basic;

    public Valve getFirst() {
        return first;
    }

    public Valve getBasic() {
        return basic;
    }

    public void setBasic(Valve v) {
        basic = v;
    }

    /**
     * 添加阀门
     *
     * @param v
     */
    public void addValve(Valve v) {
        if (first == null) {
            first = v;
            v.setNext(basic);
        } else {
            Valve current = first;
            while (current != null) {
                if (current.getNext() == basic) {
                    current.setNext(v);
                    v.setNext(basic);
                    break;
                }
                current = current.getNext();
            }
        }
    }
}

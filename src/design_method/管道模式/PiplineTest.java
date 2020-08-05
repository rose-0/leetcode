package design_method.管道模式;
//https://www.baiyp.ren/JAVA%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F-10%E7%AE%A1%E9%81%93%E6%A8%A1%E5%BC%8F.html
public class PiplineTest {
    public static void main(String[] args) {
        String s = "test";
        StandardPipeline pipeline = new StandardPipeline();
        BasicValve basic = new BasicValve();
        SecondValve second = new SecondValve();
        ThirdValve third = new ThirdValve();
        pipeline.setBasic(basic);
        pipeline.addValve(second);
        pipeline.addValve(third);
        pipeline.getFirst().invoke(s);
    }
}

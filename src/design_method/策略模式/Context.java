package design_method.策略模式;

public class Context {
    CalPrice calPrice;

    public void method(double price){
        calPrice.calPrice(price);
    }
}

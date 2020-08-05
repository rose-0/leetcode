package design_method.策略模式;

public class Svip implements CalPrice {

    @Override
    public Double calPrice(Double originalPrice) {
        return originalPrice*0.7;
    }
}

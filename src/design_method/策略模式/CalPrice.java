package design_method.策略模式;

public interface CalPrice {
    //根据原价返回一个最终的价格
    Double calPrice(Double originalPrice);
}

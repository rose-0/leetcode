package design_method.观察者模式;

public class Client {
    public static void main(String[] args) {
        SubscriptionSubject subscriptionSubject= new SubscriptionSubject();
        WeixinUser user1=new WeixinUser("tom");
        WeixinUser user2=new WeixinUser("jhon");
        subscriptionSubject.attach(user1);
        subscriptionSubject.attach(user2);
        subscriptionSubject.notify("updae ---");
    }
}

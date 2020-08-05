package design_method.观察者模式;

//ConcrereObserver 具体观察者
public class WeixinUser implements Observer {
    private String name;
    public WeixinUser(String name){
        this.name=name;
    }
    @Override
    public void update(String message) {
        System.out.println(name+"message:"+message);
    }
}

package design_method.适配器.对象适配器;

public class Client {
    public static void main(String[] args) {
        Phone phone=new Phone();
        phone.charging(new VolatageAdapter(new Voltage220V()));
    }
}

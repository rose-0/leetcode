package design_method.适配器.对象适配器;

import design_method.适配器.类适配器.IVoltage5V;

public class Phone {
    public void charging(IVoltage5V iVoltage5V){
        if(iVoltage5V.output5V()==5){
            System.out.println("ok");
        }else {
            System.out.println("error");
        }
    }
}

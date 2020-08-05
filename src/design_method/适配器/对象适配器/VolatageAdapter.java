package design_method.适配器.对象适配器;

import design_method.适配器.类适配器.IVoltage5V;

public class VolatageAdapter  implements IVoltage5V {
    private Voltage220V voltage220V;
    public VolatageAdapter(Voltage220V voltage220V){
        this.voltage220V=voltage220V;
    }
    @Override
    public int output5V() {
        int srcV=voltage220V.output220V();
        int dstV=srcV/44;
        return dstV;
    }



}

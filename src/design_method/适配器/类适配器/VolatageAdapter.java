package design_method.适配器.类适配器;

public class VolatageAdapter extends Voltage220V implements IVoltage5V {
    @Override
    public int output5V() {
        int srcV=output220V();
        int dstV=srcV/44;
        return dstV;
    }



}

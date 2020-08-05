package leecode.string;

public class 七进制数_504 {
    public String convertToBase7(int num){
        if(num==0){
            return "0";
        }
        boolean flag=false;
        StringBuilder stringBuilder=new StringBuilder();
        if(num<0){
            flag=true;
            num=num*-1;
        }
        while (num>0){
            int i=num%7;
            num=num/7;
            stringBuilder.insert(0,i);
        }
        if(flag){
            stringBuilder.insert(0,"-");
        }
        return stringBuilder.toString();
    }
}

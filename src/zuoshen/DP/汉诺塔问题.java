package zuoshen.DP;

public class 汉诺塔问题 {
    //可以对比leecode
    public void  process(int num,String from,String to,String help){
        if(num==1){
            System.out.println("移动1从"+from+"到"+to+"借助"+help);
            return;
        }
        process(num-1,from,help,to);
        System.out.println("移动"+num+"从"+from+"到"+to);
        process(num-1,help,to,from);
    }

}




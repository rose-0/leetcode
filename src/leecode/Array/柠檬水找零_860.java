package leecode.Array;

public class 柠檬水找零_860 {
    public static boolean lemonadeChange(int[] bills) {
        int five=0;
        int ten=0;
        for(int i=0;i<bills.length;i++){
            if(five<0){
                break;
            }
            switch (bills[i]){
                case 5:
                    five++;
                    break;
                case 10:
                    five--;
                    ten++;
                    break;
                case 20:
                    if(ten>0){//先找零10块钱
                        ten--;
                        five--;
                    }else {
                        five-=3;
                    }
                    break;
            }
        }
//        System.out.println(sum);
        return five>=0;
    }

    public static void main(String[] args) {
        int[] bills = {5,5,5,10,20};
        System.out.println(lemonadeChange(bills));
    }
}

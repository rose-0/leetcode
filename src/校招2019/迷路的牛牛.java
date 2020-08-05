package 校招2019;

import java.util.Scanner;

public class 迷路的牛牛 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        String path=sc.next();
        int countLeft=0;
        int countRight=0;
        for (int i = 0; i <n ; i++) {
            if(path.charAt(i)=='L'){
                countLeft++;
            }else {
                countRight++;
            }
        }
        char[]dir=new char[]{'E','S','W','N'};
        int count=countRight-countLeft;
        count%=4;
        if(count<0){
            count=count+4;
        }
        System.out.println(dir[count-1]);//err !!! count%4下标取值范围是0-3 ，这样count-1 为 -1~2
    }
}

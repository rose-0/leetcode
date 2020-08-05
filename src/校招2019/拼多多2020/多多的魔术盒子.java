package 校招2019.拼多多2020;

import java.util.Scanner;

public class 多多的魔术盒子 {
//    import java.util.*;
//    public class Main{
        public static void main(String[]args){
            Scanner sc=new Scanner(System.in);
            int num=sc.nextInt();
            for(int i=0;i<num;i++){
                int n=sc.nextInt();
                System.out.println(cal(n));
            }
        }
        public static int cal(int n){
            if(n==1) return 1;
            if(n==2) return 2;
            return 1+cal(n/2);
        }
    }
//}

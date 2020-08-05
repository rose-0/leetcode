package zuoshen.输入输出练习;

import java.util.Scanner;

/*
输入包括两个正整数a,b(1 <= a, b <= 10^9),输入数据包括多组。
输出a+b的结果
 */
public class A {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while (sc.hasNext()){
            for (int i = 0; i <2 ; i++) {
                int a=sc.nextInt();
                int b=sc.nextInt();
                System.out.println(a+b);
            }
        }
    }
}

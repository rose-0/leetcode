package zuoshen.输入输出练习;

import java.util.Scanner;

/*

 */
public class K {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while (sc.hasNext()){
            long sum=0;
            for (int i = 0; i <2 ; i++) {
                sum+=sc.nextLong();
            }
            System.out.println(sum);
        }
    }
}

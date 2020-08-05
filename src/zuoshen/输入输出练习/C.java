package zuoshen.输入输出练习;

import java.util.Scanner;

/*
链接：https://ac.nowcoder.com/acm/contest/320/C
来源：牛客网

输入包括两个正整数a,b(1 <= a, b <= 10^9),输入数据有多组, 如果输入为0 0则结束输入
输出a+b的结果
 */
public class C {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while (sc.hasNext()){//输入数据有多组，所以while一直读数据，每组两个，所以while里面只读两个
            int a=sc.nextInt();
            int b=sc.nextInt();
            if(a==0&&b==0){
                break;
            }
            System.out.println(a+b);
        }
    }
}

package zuoshen.输入输出练习;

import java.util.Scanner;

/*
链接：https://ac.nowcoder.com/acm/contest/320/D
来源：牛客网

输入数据包括多组。
每组数据一行,每行的第一个整数为整数的个数n(1 <= n <= 100), n为0的时候结束输入。
接下来n个正整数,即需要求和的每个正整数。

每组数据输出求和的结果
 */
public class D {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        /*
        while相当于把所有数据读进来
        如果第一个数字表示有几个数，则读入，加个for循环，就可以处理这一行数字了 如F
        如果第一个数字表示有几行，则读入，加个for循环，表示处理n行，里面如果知道每行几个数字，则读入，再加for循环 如E
        如果知道几个数一组，则依次读入即可  如C
         */
        while (!sc.hasNext("0")){//读取每行数字，一次处理一行
            int num=sc.nextInt();//这一行有num个数
            int sum=0;
            for (int i = 0; i <num ; i++) {//读入num个数
                sum+=sc.nextInt();
            }
            System.out.println(sum);
        }
    }
}

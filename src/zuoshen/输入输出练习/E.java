package zuoshen.输入输出练习;

import java.util.Scanner;

/*
链接：https://ac.nowcoder.com/acm/contest/320/E
来源：牛客网

输入的第一行包括一个正整数t(1 <= t <= 100), 表示数据组数。
接下来t行, 每行一组数据。
每行的第一个整数为整数的个数n(1 <= n <= 100)。
接下来n个正整数, 即需要求和的每个正整数。

每组数据输出求和的结果
 */
public class E {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while (sc.hasNext()){
            int n=sc.nextInt();
            for (int i = 0; i <n ; i++) {
                int m=sc.nextInt();
                int sum=0;
                for (int j = 0; j <m ; j++) {
                    sum+=sc.nextInt();
                }
                System.out.println(sum);
            }
        }
    }

}

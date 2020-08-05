package zuoshen.输入输出练习;

/*
链接：https://ac.nowcoder.com/acm/contest/320/F
来源：牛客网

输入数据有多组, 每行表示一组输入数据。
每行的第一个整数为整数的个数n(1 <= n <= 100)。
接下来n个正整数, 即需要求和的每个正整数。
每组数据输出求和的结果
 */
import java.util.Scanner;

public class F {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while (sc.hasNext()){
            int n=sc.nextInt();
            int sum=0;
            for (int i = 0; i <n ; i++) {
                sum+=sc.nextInt();
            }
            System.out.println(sum);
        }
    }
}

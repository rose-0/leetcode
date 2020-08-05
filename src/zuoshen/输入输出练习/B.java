package zuoshen.输入输出练习;
/*
输入第一行包括一个数据组数t(1 <= t <= 100)
接下来每行包括两个正整数a,b(1 <= a, b <= 10^9)
输出a+b的结果
 */
import java.util.*;
public class B {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while (sc.hasNext()){
            int n=sc.nextInt();//读入第一个数字，表示有n行
            for (int i = 0; i <n ; i++) {//对每一行的数字处理
                //因为每行只有两个数字，如果每行数字不确定，则需要while或者for循环
                int a=sc.nextInt();
                int b=sc.nextInt();
                System.out.println(a+b);
            }
        }
    }
}

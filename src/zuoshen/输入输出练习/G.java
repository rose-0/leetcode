package zuoshen.输入输出练习;

import java.util.Scanner;

/*
链接：https://ac.nowcoder.com/acm/contest/320/G
来源：牛客网

输入数据有多组, 每行表示一组输入数据。

每行不定有n个整数，空格隔开。(1 <= n <= 100)。

每组数据输出求和的结果

hasNextint和hasNextline https://blog.csdn.net/Mr_yuntuo/article/details/84714646
https://www.cnblogs.com/www-yang-com/p/7993397.html
采用has xxxx的话，后面也要用next xxxx。比如前面用hasNextLine，那么后面要用 nextLine 来处理输入。
                                         前面用hasNextInt，那么后面要用 nextInt 来处理输入
hasNext是检查是否有非空字符。
hasNextLine是检查输入中是否还有linePattern。其中LinePattern其实是匹配一个正则表达式。

abc def ghij
kl mno pqr st
uvw xyz
你用next()，第一次取的是abc，第二次取的是def，第三次取的是ghij
你用nextLine()，第一次取的是abc def ghij，第二次取的是kl mno pqr st，第三次取的是uvw xyz
明白了吧。前一个是以回车或空格为分隔符，一次取一个单词，后一个是以回车为分隔符，一次取一行。
 */
public class G {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while (sc.hasNextLine()){
            String temp=sc.nextLine();//读取一行数据就用nextline，读取一个数据就用next
            String[]num=temp.split(" ");
            int sum=0;
            for (String s:num) {
                sum+=Integer.valueOf(s);
            }
            System.out.println(sum);
        }
    }
}

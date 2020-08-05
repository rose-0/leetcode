package zuoshen.输入输出练习;

import java.util.Arrays;
import java.util.Scanner;

/*
多个测试用例，每个测试用例一行。

每行通过空格隔开，有n个字符，n＜100

对于每组测试用例，输出一行排序过的字符串，每个字符串通过空格隔开
 */
public class I {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while (sc.hasNextLine()){
            String temp=sc.nextLine();
            String []nums=temp.split(" ");
            Arrays.sort(nums);
            for (int i = 0; i <nums.length ; i++) {
                System.out.print(nums[i]+" ");
            }
            System.out.println();
        }
    }
}

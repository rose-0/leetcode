package zuoshen.输入输出练习;

import java.util.Arrays;
import java.util.Scanner;

/*
多个测试用例，每个测试用例一行。
每行通过,隔开，有n个字符，n＜100
对于每组用例输出一行排序后的字符串，用','隔开，无结尾空格
 */
public class J {
    //next()方法遇到第一个扫描有效字符，即第一个非空格非换行符后面开始，
    // 一直获取到下一个空格，换行符之前的，单个字符串
    //nextLine()可以扫描到一行内容并作为一个字符串而被获取到
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while (sc.hasNextLine()){
            String temp=sc.nextLine();
            String[]nums=temp.split(",");
            Arrays.sort(nums);
            for (int i = 0; i <nums.length-1 ; i++) {
                System.out.print(nums[i]+",");
            }
            System.out.print(nums[nums.length-1]);
            System.out.println();
        }
    }
}

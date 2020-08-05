package 校招2019;

import java.util.Scanner;

public class 俄罗斯方块 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        int[]count=new int[n];
        for (int i = 0; i <m ; i++) {
            int num = sc.nextInt();
            count[num - 1]++;
        }
        int min=Integer.MAX_VALUE;
        for (int i = 0; i <count.length ; i++) {
                min=Math.min(min,count[i]);
        }
        System.out.println(min);
    }
}

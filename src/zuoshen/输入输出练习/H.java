package zuoshen.输入输出练习;

import java.util.Arrays;
import java.util.Scanner;

/*
输入有两行，第一行n

第二行是n个空格隔开的字符串

输出一行排序后的字符串，空格隔开，无结尾空格
 */
public class H {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while (sc.hasNext()){
            int n=sc.nextInt();
            String[]nums=new String[n];
            for (int i = 0; i <n ; i++) {
                nums[i]=sc.next();
            }
            Arrays.sort(nums);
            for (int i = 0; i <n ; i++) {
                System.out.print(nums[i]+" ");
            }
        }
    }
}

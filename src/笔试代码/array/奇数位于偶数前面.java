package 笔试代码.array;
//https://www.nowcoder.com/questionTerminal/beb5aa231adc45b2a5dcc5b62c93f593
//有序数组， 奇数位于偶数前面 并且有序
public class 奇数位于偶数前面 {
    public static void tiaozhen2(int[]num) {
        int k=0;//记录摆好位置的奇数的个数
        for (int i = 0; i <num.length ; i++) {
            if(num[i]%2==1){
                int j=i;
                while (j>k){//j>=k+1  num[j]向前放到 k+1的位置上
                    int temp=num[j];
                    num[j]=num[j-1];
                    num[j-1]=temp;
                    j--;//注意这个
                }
                k++;
            }
        }
        for (int i = 0; i <num.length ; i++) {
            System.out.print(num[i]+" ");
        }
    }
}

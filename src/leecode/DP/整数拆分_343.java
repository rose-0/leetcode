package leecode.DP;

public class 整数拆分_343 {
    static int max=0;
    public static int integerBreak(int n) {

        if(n==2||n==1){
            return 1;
        }

        for (int i = 1; i <=n/2 ; i++) {
            int temp=Math.max(i*(n-i),i*integerBreak(n-i));
            max=max>temp?max:temp;
//            System.out.println("i="+i+"temp:"+temp);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(integerBreak(10));
    }
}

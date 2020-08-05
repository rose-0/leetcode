package leecode.other;
//https://www.cnblogs.com/grandyang/p/4383775.html
public class pow_x_n_50 {
    public static double myPow(double x, int n) {
        if(n==0)return 1;
        double half=myPow(x,n/2);
        if(n%2==0)return half*half;
        if(n>0)return half*half*x;//这是n为正数的情况
        return half*half/x;//这个是n为负数的情况，多个乘以自身，意思是half*half*(x的负一次方)
    }
    public static double myPow2(double x, int n) {
        double res = 1.0;
        for (int i = n; i != 0; i /= 2) {
            if (i % 2 != 0) res *= x;
            x *= x;
        }
        return n < 0 ? 1 / res : res;
    }

    public static void main(String[] args) {
        System.out.println(myPow2(3.0,5));
    }
}

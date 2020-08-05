package zuoshen.DP;

public class 求解n的阶乘 {
    public int re_con(int n){
        int sum=1;
        for (int i = 1; i <=n ; i++) {
            sum*=i;
        }
        System.out.println(sum);
        return sum;
    }
    public int con(int n){
        if(n==1){
            return 1;
        }
        return n*con(n-1);
    }
}

package leecode.DP;

public class 第N个泰波那契数_1137 {
    public int tribonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3);
    }

    public int tribonacci2(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int dp_i_3=0;
        int dp_i_2=1;
        int dp_i_1=1;
        int dp=0;
        for (int i = 3; i <=n ; i++) {
            dp=dp_i_1+dp_i_2+dp_i_3;

            dp_i_3=dp_i_2;
            dp_i_2=dp_i_1;
            dp_i_1=dp;
        }
        return dp;
    }
}

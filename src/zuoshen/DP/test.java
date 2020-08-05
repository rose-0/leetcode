package zuoshen.DP;

public class test {
    public static void main(String[] args) {
//        new 汉诺塔问题().process(3,"from","to","help");
        char[]res=new char[]{'a','c','c'};
//        new 打印一个字符串的非重复排列().print_all(res,0);
//        System.out.println(new 求解n的阶乘().con(3));
        int[][]matix=new int[][]{{1,2,1},{5,4,1},{1,3,1}};
        int[]arr=new int[]{5,10,25,1};
//        int sum=new 矩阵的最小路径和().min_sum_dp(matix,0,0);
        System.out.println(new 换钱的方法数().process(arr,15,0,15));
        System.out.println(new 换钱的方法数().process_method(arr,15,0,15));
        System.out.println(new 换钱的方法数().method_dp(new int[arr.length+1][16],15,15,0,arr));
        System.out.println(new 换钱的方法数().method_dp2(arr,15));
    }
}

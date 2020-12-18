package leecode.other;

public class x的平方根_69 {
    //分析https://www.shuzhiduo.com/A/pRdBxe92Jn/
    public int sqrt (int x) {
        // write code here
        int low=0;
        int high=x;
        while(low<=high){//这里是等于
            long mid=(low+high)/2;
            if(mid*mid==x){
                return (int)mid;
            }else if(mid*mid<x){
                low=(int)(mid+1);
            }else{
                high=(int)(mid-1);
            }
        }
        return high;//返回high！！
    }

    //有精度要求
    public double sqrt1 (int x) {
        double start=0.0;
        double end=x;
        double p= 1e-2; // 1*10的-2次方
        double mid=(start+end)/2.0;
        while (Math.abs(mid*mid-x)>p){
            if(mid*mid<x){
                start=mid;//跟上面不同的是 不加1
            }else if(mid*mid>x){
                end=mid;
            }else {
                return mid;
            }
            mid=(start+end)/2.0;
        }
        return mid;
    }
    //liweiwei
    public int mySqrt(int x) {
        // left、right、mid都要是long 否则不正确 针对大整型测试用例通不过，因此变量需要声明为 long 类型
        long left=0;
        long right=x/2+1;//否则会超出时间限制
        while (left<right){
            long mid=(left+right+1)/2;
            if(mid*mid>x){
                right=mid-1;
            }else {
                left=mid;
            }
        }
        return (int) left;
    }
}

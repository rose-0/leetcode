package leecode.other;

import java.util.Arrays;

public class 计数质数_204 {
    public int countPrimes(int n) {
        int count=0;
        for (int i = 2; i <n ; i++) {//从2开始
            //判断素数
            if(isPrime(i)){
                count++;
            }
        }
        return count;
    }
    public boolean isPrime(int n){
        for (int i = 2; i <n ; i++) {
            if(n%i==0){//这里是 n%i 不是 2
                return false;
            }
        }
        return true;
    }
    //优化1
    public int countPrimes2(int n) {
        boolean[] isPrime=new boolean[n];
        Arrays.fill(isPrime,true);
        for (int i = 2; i <n ; i++) {
            if(isPrime[i]){
                // i的倍数就不可能是素数了
                // 2是一个素数，那么 2*2 2*3 2*4 。。。。都不是素数
                // 每次 j=j+i 开始 j=2*2 循环一次加2 相当于 2*3
                for (int j = 2*i; j <n ; j=j+i) {
                    isPrime[j]=false;
                }
            }
        }
        int count=0;
        for (int i = 2; i <n ; i++) {
            if(isPrime[i]){
                count++;
            }
        }
        return count;
    }
    //优化2
    public int countPrimes3(int n) {
        boolean[]isPrime=new boolean[n];
        Arrays.fill(isPrime,true);
        for (int i = 2; i <n ; i++) {
            if(isPrime[i]){
                for (int j = i*i; j <n ; j=j+i) {//从i*i开始
                    isPrime[j]=false;
                }
            }
        }
        int count=0;
        for (int i = 2; i <n ; i++) {
            if(isPrime[i]){
                count++;
            }
        }
        return count;
    }
}

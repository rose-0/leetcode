package leecode.other;

import java.util.Random;

/*
https://leetcode-cn.com/problems/implement-rand10-using-rand7/solution/xiang-xi-fen-xi-fei-chang-jing-dian-de-ti-mu-deng-/
rand 大数生成小数:譬如 rand10得到rand7 思路很简单，如果得到8-10，就继续调用，直到处于1-7为止
核心就是 等概率映射，映射不到的就继续调用

rand 小数生成大数，核心也是一样，等概率映射 最好是让每个数，只能有且一种组合方式出现
rand7 + (rand7 - 1) * 7
每投一次，那就是加上一个1-7，那么我不能让数可以溢出（必须保持得到方式唯一），那我就以7为一个层的容量！
这就是一个7进制！！

进一个 1就对应一个新数，因为这个7位数，1-7就代表一个数，进一位 1 (也就是7) ，就是对于另外一个数


为啥等概率：看这个
https://leetcode-cn.com/problems/implement-rand10-using-rand7/solution/cong-zui-ji-chu-de-jiang-qi-ru-he-zuo-dao-jun-yun-/
 */
public class 用Rand7实现Rand10_470 {
    public int rand10() {
        Random random=new Random();
        int num=(random.nextInt(7)-1)*7+random.nextInt(7);
        while (num>10){
            num=(random.nextInt(7)-1)*7+random.nextInt(7);
        }
        return num;
    }
}

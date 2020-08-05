package Sort;

import java.util.Random;

//https://github.com/labuladong/fucking-algorithm/blob/master/%E7%AE%97%E6%B3%95%E6%80%9D%E7%BB%B4%E7%B3%BB%E5%88%97/%E6%B4%97%E7%89%8C%E7%AE%97%E6%B3%95.md
/*
Random random=new Random();
int rand= random.nextInt(num);//[0,num)的随机数
int rand= random.nextInt((max-min)+1)+min;//[0+min,max-min+1+min);[min,max+1)
或者使用Math库
Math.random()返回 0<=Math.random()<1的随机数
int rand=min+(int)(Math.random()*(max-min+1))
 */
public class 洗牌算法 {
    //正确性：产生的结果有n！种，因为一个长为n的数组，全排列就有n！种
    public void shuffle(int[]arr){
        Random random=new Random();//i=0 产生n种 i=1 n-1种
        for (int i = 0; i <arr.length ; i++) {//从后往前
            int rand= i+random.nextInt(arr.length-i);//[i,arr.length)的随机数
            swap();
        }
    }
    public void swap(){

    }
}

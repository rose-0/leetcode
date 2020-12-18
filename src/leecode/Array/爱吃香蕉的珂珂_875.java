package leecode.Array;

public class 爱吃香蕉的珂珂_875 {
    /*
    liweiwei https://leetcode-cn.com/problems/koko-eating-bananas/solution/er-fen-cha-zhao-ding-wei-su-du-by-liweiwei1419/
    最大值最小化问题
     */
    public int minEatingSpeed(int[] piles, int H) {
        int left=1;
        int right=1;
        for (int i = 0; i <piles.length ; i++) {
            right=right>piles[i]?right:piles[i];
        }
        while (left<right){
            int mid=(left+right)/2;
            if(calTime(piles,mid)>H){
                left=mid+1;
            }else {
                right=mid;
            }
        }
        return left;
    }
    public int calTime(int[] piles,int num){
        int sum=0;
        for (int i = 0; i <piles.length ; i++) {
            //(piles[i]-1+num)/num 是对 piles[i]向上取整
            sum+=(piles[i]-1+num)/num;
        }
        return sum;
    }
}

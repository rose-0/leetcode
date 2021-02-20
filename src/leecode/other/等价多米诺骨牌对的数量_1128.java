package leecode.other;

public class 等价多米诺骨牌对的数量_1128 {
    //暴力会超时
    public int numEquivDominoPairs(int[][] dominoes) {
        int ans=0;
        for (int i = 0; i <dominoes.length ; i++) {
            for (int j = i+1; j <dominoes.length ; j++) {
                if(isEqual(dominoes[i],dominoes[j])){
                    ans++;
                }
            }
        }
        return ans;
    }
    public boolean isEqual(int[] pair1,int[] pair2){
        return (pair1[0]==pair2[0]&&pair1[1]==pair2[1])||(pair1[0]==pair2[1]&&pair1[1]==pair2[0]);
    }


    //wdw87的评论 https://leetcode-cn.com/problems/number-of-equivalent-domino-pairs/comments/
    public int numEquivDominoPairs2(int[][] dominoes) {
        int[]map=new int[100];
        int res=0;
        for (int i = 0; i <dominoes.length ; i++) {
            int x=dominoes[i][0];
            int y=dominoes[i][1];
            int k= x>y?x*10+y:y*10+x;
            map[k]++;
        }
        
        for (int i = 0; i <map.length ; i++) {
            res+=map[i]*(map[i]-1)/2;    
        }
        return res;
    }
}

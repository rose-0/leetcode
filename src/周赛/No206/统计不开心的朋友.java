package 周赛.No206;

import java.util.HashMap;
import java.util.Map;

public class 统计不开心的朋友 {
    public static int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        Map<Integer,Integer>map=new HashMap<>();
        for (int i = 0; i <pairs.length ; i++) {
            map.put(pairs[i][0],pairs[i][1]);
            map.put(pairs[i][1],pairs[i][0]);
        }
        int res=0;
        for (int x:map.keySet()) {
            int y=map.get(x);
            //找比y亲密的   x的pre
            for (int i = 0; i <n-1 ; i++) {
                if(preferences[x][i]!=y){
                    int u=preferences[x][i];
                    int v=map.get(u);
                    while (i<n-1&&preferences[u][i]!=v){
                        if(preferences[u][i]==x){
                            res++;
                        }
                        i++;
                    }
                }
            }

        }

//        for (int i = 0; i <n ; i++) {
//            map.put(i,preferences[i][0]);
//        }
//        int count=0;
//        for (int i = 0; i <pairs.length ; i++) {
//            if(map.get(pairs[i][0])==pairs[i][1]){
//                count++;
//            }
//            if(map.get(pairs[i][1])==pairs[i][0]){
//                count++;
//            }
//        }
        return res;
    }

    public static void main(String[] args) {
        int[][]mat={
                {1,2,3},
                {3,2,0},
                {3,1,0},
                {1,2,0},
        };
        int[][]pair={
                {0,1},
                {2,3},
        };

        System.out.println(unhappyFriends(4,mat,pair));

    }
}

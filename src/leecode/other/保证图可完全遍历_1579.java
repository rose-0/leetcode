package leecode.other;

import java.util.Stack;
//https://leetcode-cn.com/problems/remove-max-number-of-edges-to-keep-graph-fully-traversable/solution/java-tan-xin-3ci-bing-cha-ji-by-xinzzheng/
/*
一个无向图为连通图最少需要的边为 n - 1. 其中 n 为图中的节点数. 如果这个题没有 Alice, Bob 公共边这种事儿，我们用两步即可：

判断图是否为连通图；
若为连通图 (n - 1) - 边数 即为需要删除的边数。
 */
public class 保证图可完全遍历_1579 {
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        UF uf1=new UF(n);
        UF uf2=new UF(n);
        UF uf3=new UF(n);
        int cnt=0;
        int c1=0;
        int c2=0;
        for (int[]edge:edges){
            int type=edge[0];
            int x=edge[1]-1;//-1是因为数组大小为n，是从下标0开始的
            int y=edge[2]-1;
            //改造并查集模板中的union方法，计算type3重复边数量cnt, type3的重复边也需要去掉
            if(type==3) cnt+=uf3.union(x,y)?1:0;
            if(type==1) c1++;
            if(type==2) c2++;
            if(type==1||type==3) uf1.union(x,y);//计算alice连通分量的数目
            if(type==2||type==3) uf2.union(x,y);
        }
        //说明不需要 type1 和 type2;
        if(uf3.count==1){
            return c1+c2+cnt;
        }
        //只要分别从 type1 和 type2 中都拿出 UF3 （联通分量数量 - 1）个边就能使得 UF3 联通 。
        if(uf1.count==1&&uf2.count==1){
            return cnt+(c1-(uf3.count-1))+(c2-(uf3.count-1));
        }
        // type1、type2、type3
        // 判断其联通分量数量是否为1， 如果有一个不唯一，则返回-1，说明一开始都不能完全遍历;
        return -1;
    }
    class UF{
        public int[]arr;
        public int count;
        public int[]size;
        public UF(int n){
            arr=new int[n];
            size=new int[n];
            for (int i = 0; i <arr.length ; i++) {
                arr[i]=i;
                size[i]=1;
            }
            count=n;
        }
        public boolean union(int p,int q){
            int rootp=findParent(p);
            int rootq=findParent(q);
            if(rootp==rootq){
                return true;
            }
            if(size[rootp]>size[rootq]){
                arr[rootq]=rootp;
                size[rootp]+=size[rootq];
            }else {
                arr[rootp]=rootq;
                size[rootq]+=size[rootp];
            }
            count--;
            return false;
        }
        
        public int findParent(int num){
            /*
            Stack<Integer>stack=new Stack<>();
            int father=arr[num];
            while (father!=num){
                stack.push(num);
                num=father;
                father=arr[num];
            }
            while (!stack.isEmpty()){
                arr[stack.pop()]=father;
            }
            */
            
            while (arr[num]!=num){
                arr[num]=arr[arr[num]];
                num=arr[num];
            }
            return num;
        }
    }
}

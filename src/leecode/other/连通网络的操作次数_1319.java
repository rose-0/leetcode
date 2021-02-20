package leecode.other;

/*
在并查集的基础上加一些辅助变量的练习题目
https://leetcode-cn.com/problems/number-of-operations-to-make-network-connected/solution/wang-luo-lian-jie-bing-cha-ji-by-yexiso-1nd4/
    
 */
public class 连通网络的操作次数_1319 {
    public int makeConnected(int n, int[][] connections) {
        //链接n台电脑需要n-1条线，connections.length为线的数量
        if(connections.length<n-1){
            return -1;
        }
        count=n;//连通分量的个数初始化为n
        int[]size=new int[n];
        int[]parent=new int[n];
        for (int i = 0; i <n ; i++) {
            size[i]=1;
            parent[i]=i;
        }
        for(int[]conection:connections){
            int x=conection[0];
            int y=conection[1];
            union(x,y,parent,size);
        }
        //看这个解释 https://leetcode-cn.com/problems/number-of-operations-to-make-network-connected/solution/shou-hua-tu-jie-kao-cha-bing-cha-ji-1319-u9nb/
        return --count;
    }
    int count;
    private void union(int x,int y,int[]parent,int[]size){
        int fx=findParent(x,parent);
        int fy=findParent(y,parent);
        if(fx==fy){
            return;
        }else {
            if(size[fx]>=size[fy]){
                parent[fy]=fx;
                size[fx]+=size[fy];
            }else {
                parent[fx]=fy;
                size[fy]+=size[fx];
            }
            count--;//
        }
    }
    private int findParent(int x,int[]parent){
        if(parent[x]!=x){
            int father = findParent(parent[x],parent);
            parent[x]=father;//路径压缩
        }
        return parent[x];
    }
}

package zuoshen.other;

import java.util.Stack;

public class 并查集_labuladong {
    /*
    主要实现以下几个api
    public void union(int p, int q);//将p和q相连
    public boolean connected(int p,int q);//判断p和q是否连通
    public int count();//返回图有多少个连通分量
     */
    //连通分量个数
    private int count;
    //存储一棵树,使用数组
    private int[] parent;
    //记录树的重量，防止合并的时候不平衡
    private int[] size;

    public 并查集_labuladong(int n){
        this.count=n;
        parent=new int[n];
        size=new int[n];
        for (int i = 0; i <n ; i++) {
            parent[i]=i;//初始化i的父母为自己
            size[i]=1;
        }
    }

    public void union(int p,int q){
        int rootP=find(p);
        int rootQ=find(q);
        if(rootP==rootQ){
            return;
        }
        //小树接到大树下面
        if(size[rootP]>size[rootQ]){
            parent[rootQ]=rootP;
            size[rootP]+=size[rootQ];//更新rootp的size！！不要颠倒了！！
        }else {
            parent[rootQ]=rootP;
            size[rootQ]+=size[rootP];
        }
        count--;
    }

    public boolean connected(int p,int q){
        int rootP=find(p);
        int rootQ=find(q);
        return rootP==rootQ;
    }
    private int find (int x){
        /*
        Stack<Integer>stack=new Stack<>();
        int father = parent[x];
        while (father!=x){
            stack.push(x);//路径上的x都存下来
            x=father;//x上移
            father=parent[x];
        }
        while (!stack.empty()){
            parent[stack.pop()]=father;
        }
         */
        while (parent[x]!=x){
            //路径压缩
            parent[x]=parent[parent[x]];//parent[x]即x的父节点赋值为x的祖父节点，此时路径已经压缩了
            // x的父节点不断向上，可以看公众号gif
            x=parent[x];//x上移
        }
        return x;
    }
}

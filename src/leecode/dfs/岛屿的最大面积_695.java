package leecode.dfs;

import com.sun.org.apache.bcel.internal.generic.FADD;

public class 岛屿的最大面积_695 {
    //对比lee200
//    并查集只需要走右，走下 方向 即{1,0},{0,1}
    static int[][]dirs=new int[][]{{1,0},{0,1},{0,-1},{-1,0}};//表示上下左右搜索，{1,-1}表示斜着搜索
    public static int maxAreaOfIsland(int[][]grid){
        int m=grid.length;
        int n=grid[0].length;

        union union=new union(m*n);
        for (int i = 0; i <m ; i++) {
            for (int j = 0; j <n ; j++) {
                System.out.println("2222 i="+i+"j= "+j+" ");
                if(grid[i][j]==1){
                    for (int[]dir:dirs){
                        int x=i+dir[0];
                        int y=j+dir[1];
                        if(bound(x,y,grid)&&grid[x][y]==1){
                            union.merge(i*n+j,x*n+y);
                        }
                    }
                }
            }
        }
        int max=0;
        for (int i = 0; i <m ; i++) {
            for (int j = 0; j < n; j++) {
                if(grid[i][j]==1){
                    System.out.println("234"+size[i*n+j]+" "+i+" "+j);
                  max=Math.max(size[i*n+j],max);
                }
            }
        }
        return max;
    }
    public static boolean bound(int x,int y,int[][]grid){
        return x>=0&&x<grid.length&&y>=0&&y<grid[0].length;
    }
    //思路：并查集看每个树的size
    static int[]size;
    static class union{
        private int[]parent;
//        private int[]size;
        private int count;
        public union(int n){
            parent=new int[n];
            size=new int[n];
            this.count=n;
            for (int i = 0; i <n ; i++) {
                parent[i]=i;
                size[i]=1;
            }
        }
        public void merge(int node1,int node2){
            int father1=findFather(node1);
            int father2=findFather(node2);
            if(father1==father2){
                return;
            }
            //怎么的到各自的size？
            if(size[father1]>size[father2]){//小放在大
                parent[father2]=father1;
                size[father1]+=size[father2];
            }else {
                parent[father1]=father2;
                size[father2]+=size[father1];
            }
            count--;//记得这个
        }
        public boolean isConnected(int node1,int node2){
            return findFather(node1)==findFather(node2);
        }
        public int findFather(int node){
//            int father=parent[node];
            while (parent[node]!=node){ //写成while(father!=node)就错了，因为while里面father不更新
                parent[node]=parent[parent[node]];
                node=parent[node];
            }
            return node;
        }
    }

    //dfs
//    public static int max=0;
    public static int maxAreaOfIsland2(int[][]grid) {
        int max=0;
        boolean[][]visit=new boolean[grid.length][grid[0].length];
        for (int i = 0; i <grid.length ; i++) {
            for (int j = 0; j <grid[0].length ; j++) {
                if(grid[i][j]==1){
                    int temp=maxAreaOfIslandwithdfs(grid,0,0,visit);
//                    System.out.println("i="+i+"j="+j+"temp="+temp);
                    max=Math.max(max,temp);
//                    return -1;
                }
            }
        }
        return max;
    }
    /*
    dfs每个点都可以进行dfs时候，dfs就需要返回值！！因为每个点进行dfs的结果
    需要去更新我们最后要的结果，因为java不能传引用，所以每次拿返回值来更新

    每个点都需要dfs时候就需要visit数组！！

    遍历完成之后 visit[i][j]重新变成false就错了！！！，

    maxAreaOfIslandwithdfs2可以看下不传引用怎么改变值
     */
        public static int maxAreaOfIslandwithdfs(int[][]grid,int i,int j,boolean[][]visit){
        if(grid[i][j]==0||visit[i][j]){
            return 0;
        }
        visit[i][j]=true;
        int num=1;//设为1.如果设为0永远是0
        for(int[]dir:dirs){
            int x=i+dir[0];
            int y=j+dir[1];
            if(bound(x,y,grid)){
                int temp=maxAreaOfIslandwithdfs(grid,x,y,visit);
//                if(i==0&&j==0) {
                    System.out.println("x=" + x + "y=" + y + "temp=" + temp);
//                }
                num=num+temp;
//                System.out.println(num);
            }
        }
//        visit[i][j]=false;
        return num;
    }
    public static int maxAreaOfIslandwithdfs2(int[][]grid,int i,int j,boolean[][]visit,int count) {
        if(!bound(i,j,grid)||visit[i][j]||grid[i][j]==0){
            return count;
        }
        visit[i][j]=true;
        for(int[]dir:dirs){
            int x=i+dir[0];
            int y=j+dir[1];
            count=maxAreaOfIslandwithdfs2(grid,x,y,visit,count);//这里传入的count没有加1！！
        }
        visit[i][j]=false;//加上这个就不对
        return count+1;
    }

        public static void main(String[] args) {
        int[][]grid=new int[][]{
                {1,1,0,0,0},
                {1,1,0,0,0},
                {0,0,0,1,1},
                {0,0,0,1,1}
        };
//        System.out.println(maxAreaOfIsland(grid));
        System.out.println(maxAreaOfIsland2(grid));
    }
}

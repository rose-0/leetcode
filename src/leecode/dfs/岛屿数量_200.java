package leecode.dfs;

import java.util.LinkedList;
import java.util.Queue;

//对比lee695
public class 岛屿数量_200 {
    int[][]dirs=new int[][]{{1,0},{0,1},{0,-1},{-1,0}};//并查集只需要走右，走下 方向 即{1,0},{0,1}
    public int numsIslands(char[][]grid){
        boolean[][]visit=new boolean[grid.length][grid[0].length];
        int ans=0;
        for (int i = 0; i <grid.length ; i++) {
            for (int j = 0; j <grid[0].length ; j++) {
                /*
                !visit[i][j]要加上这个
                从一个点联通的 1 就不需要再记录为岛屿了，不需要再调用dfs了
                dfs函数中都进行了标记 visit
                如果不加 !visit[i][j] 则所有的1都被dfs，即使相互联通的
                 */
                if(grid[i][j]=='1'&&!visit[i][j]){//要加个 &&!visit[i][j]
                    numswithdfs(grid,i,j,visit);
                    ans++;
                }
            }
        }
        return ans;
    }

    /*
    liweiwei dfs图解

    注意 选择起点的条件，即调用dfs的点满足的条件
    还有 dfs函数中进行对下一个点dfs的条件

    每次从一个点向四个方向dfs，并且按照一定的顺序
    开始dfs时 记录四个方向的状态
    每个方向状态有一下情况：
    1、越界
    2、未访问 且为1
    3、未访问 且为0
    4、已经访问过

    每个方向处理：
    1、接下来要走
    2、等待回溯（等待要走的格子走完后，再对自己进行处理）

    四个方向全部走完，则退回到上一个格子，找上一个格子的下一个方向

    当从一个方向dfs完毕，返回到开始dfs的点时，可以选择恢复状态，因为即使恢复为未访问状态，也是按照固定的顺序进行dfs的，
    所以不会再对该点进行dfs，但是如果从另外一点开始进行dfs时（调用dfs函数时设置的起点），可能又对该点进行dfs

    每次调用dfs函数完毕，就是对 传入dfs起点这个点相通的所有点都处理完毕
     */
    public void numswithdfs(char[][]grid,int i,int j,boolean[][]visit){

        visit[i][j]=true;
        for(int[]dir:dirs){
            int x=i+dir[0];
            int y=j+dir[1];
            //判断该点的状态能否进行dfs：未越界，未dfs，且为1
            if(!bound(x,y,grid)&&!visit[x][y]&&grid[i][j]=='1'){
                numswithdfs(grid,x,y,visit);
            }
        }
        return;
    }

    /*
    “广度优先遍历”不用回溯,但是需要一个 “辅助队列”
    所有加入队列的结点，都应该马上被标记为 “已经访问”，否则有可能会被重复加入队列。

     */
    public int numswithbfs(char[][]grid) {
        boolean[][]visit=new boolean[grid.length][grid[0].length];
        int ans=0;
        int col=grid[0].length;
        int row=grid.length;
        for (int i = 0; i <grid.length ; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                /*
                如果一个点没有访问过，且是1，则进行bfs
                 */
                if(!visit[i][j]&&grid[i][j]=='1'){
                    ans++;
                    LinkedList<Integer>queue=new LinkedList<>();
                    /*
                    将 横 纵坐标转化为一个数字，否则需要存一个数组
                     */
                    queue.addLast(i*col+j);
                    visit[i][j]=true;//标记为已经访问
                    while (!queue.isEmpty()){
                        int cur=queue.pollFirst();
                        int newX=cur/col;
                        int newY=cur&col;
                        if(bound(newX,newY,grid)&&grid[newX][newY]=='1'&&!visit[newX][newY]){
                            queue.addFirst(newX*col+newY);
                            visit[newX][newY]=true;
                        }
                    }
                }
            }
        }
        return ans;
    }

    public boolean bound(int x, int y, char[][] grid) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length;
    }

    //考虑使用并查集合解决
    public int numsIslands2(char[][]grid) {
        int m=grid.length;
        int n=grid[0].length;

        int dummy=m*n;
        union union=new union(m*n+1);
        for (int i = 0; i <grid.length ; i++) {
            for (int j = 0; j <grid[0].length ; j++) {
                if(grid[i][j]=='1'){
                    for(int[]dir:dirs){
                        int x=i+dir[0];
                        int y=j+dir[1];
                        if(bound(x,y,grid)&&grid[x][y]=='1'){
                            union.merge(i*n+j,x*n+y);
                        }
                    }
                }else {
                    union.merge(i*n+j,dummy);
                }

            }
        }

        return union.getCount()-1;
    }
        class union{
        public int[]parent;
        public int[]size;
        public int count;

        public union(int n){
            this.count=n;
            parent=new int[n];
            size=new int[n];
            for (int i = 0; i <n ; i++) {//不要忘记这里初始化
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
            if(size[father1]>size[father2]){
                parent[father2]=father1;
                size[father1]+=size[father2];
            }else {
                parent[father1]=father2;
                size[father2]+=size[father1];
            }
            count--;
        }
        public boolean connected(int node1,int node2){
            return findFather(node1)==findFather(node2);
        }
        private int findFather(int node){
            while (parent[node]!=node){
                parent[node]=parent[parent[node]];
                node=parent[node];
            }
            return node;
        }
        public int getCount(){
            return count;
        }
    }
}

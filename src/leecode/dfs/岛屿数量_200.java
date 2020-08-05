package leecode.dfs;
//对比lee695
public class 岛屿数量_200 {
    int[][]dirs=new int[][]{{1,0},{0,1},{0,-1},{-1,0}};//并查集只需要走右，走下 方向 即{1,0},{0,1}
    public int numsIslands(char[][]grid){
        boolean[][]visit=new boolean[grid.length][grid[0].length];
        int ans=0;
        for (int i = 0; i <grid.length ; i++) {
            for (int j = 0; j <grid[0].length ; j++) {
                if(grid[i][j]=='1'&&!visit[i][j]){//要加个 &&!visit[i][j]
                    numswithdfs(grid,i,j,visit);
                    ans++;
                }
            }
        }
        return ans;
    }

    public void numswithdfs(char[][]grid,int i,int j,boolean[][]visit){
        if(!bound(i,j,grid)||visit[i][j]||grid[i][j]=='0'){
            return;
        }
        visit[i][j]=true;
        for(int[]dir:dirs){
            int x=i+dir[0];
            int y=j+dir[1];
            numswithdfs(grid,x,y,visit);
        }
        return;
    }

    public  boolean bound(int x,int y,char[][]grid){
        return x>=0&&x<grid.length&&y>=0&&y<grid[0].length;
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

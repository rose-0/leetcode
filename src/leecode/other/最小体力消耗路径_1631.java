package leecode.other;

public class 最小体力消耗路径_1631 {
    /*
    二分：https://leetcode-cn.com/problems/path-with-minimum-effort/solution/bfs-er-fen-cha-zhao-by-time-limit/
    二分查找的思路：在给定地图和体力消耗上限的情况下，判断是否存在一条路径可以到达终点。
    那么如何判断呢？根据 heights 和 limit 建立一张图，判断起点和终点是否联通即可(BFS,DFS,并查集等算法均可)。


    随着 limit 的增大，边只会加入，而不会被删除。也就是说，如果当体力限制为 limit 时存在可达路径，
    那么当限制为 limit+1 及更大值时，均存在可达路径。反之，当限制为 limit 时不存在可达路径，
    那么 limit-1 及更小值均不会存在路径，这种具备单调性的问题，直接使用二分来代替枚举即可~

    二分 + dfs:题目要求高度差绝对值最大值的最小值，是比较典型的最大值最小化问题，可以用二分查找+DFS。
    DFS + 二分查找 模板题: https://leetcode-cn.com/problems/path-with-minimum-effort/solution/dfs-er-fen-cha-zhao-mo-ban-ti-by-kobe24o/
    
    将格子抽象为一个图 两点之间建立边
    https://leetcode-cn.com/problems/path-with-minimum-effort/solution/zui-xiao-ti-li-xiao-hao-lu-jing-by-zerotrac2/
    「并查集]：我们可以将所有边按照长度进行排序并依次添加进并查集，直到左上角和右下角连通为止。
    [最短路]：我们可以使用任一单源最短路径的算法（例如 Dijkstra 算法），只需要在维护当前路径长度时，将其修改为题目中的定义即可。
    [二分答案]：我们可以对最短路径的长度进行二分。当我们二分枚举到的长度为 xx 时，我们只保留所有长度 
                x≤x 的边。随后从左上角开始进行搜索（深度优先搜索、广度优先搜索）均可，
                只需要判断是否能够到达右下角即可。

     */
    public int minimumEffortPath(int[][] heights) {
        int left=0;
        int right=1000000;
        boolean[][]visit = new boolean[heights.length][heights[0].length];
        while (left<right){
            int mid = (left + right) >>> 1;
//            if(!exist(0,0,heights,visit,mid)){  //每次都要传一个新的visit数组才可以！！！

            if(!exist(0,0,heights,new boolean[heights.length][heights[0].length],mid)){
                    left=mid+1;
            }else {
                right=mid;
            }
        }
        return left;
    }
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public boolean exist(int x,int y,int[][]heights,boolean[][]visit,int limit){
        if(!isInbound(x,y,heights)){
            return false;
        }
        if(visit[x][y]){
            return false;
        }
        if(x==heights.length-1&&y==heights[0].length-1){
            return true;
        }
        visit[x][y]=true;
        for (int i = 0; i <4 ; i++) {
            int nextX=x+dirs[i][0];
            int nextY=y+dirs[i][1];
            if(isInbound(nextX,nextY,heights)&&Math.abs(heights[x][y]-heights[nextX][nextY])<=limit){
                if(exist(nextX,nextY,heights,visit,limit)){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean isInbound(int x,int y,int[][]heights){
        return x>=0&&x<heights.length&&y>=0&&y<heights[0].length;
    }
    
    //并查集 建立最小生成树
    
    class union{
        public int[] parent;
        public int[] size;
        int count;
        public union(int n){
            parent=new int[n];
            size=new int[n];
            count=n;
            for (int i = 0; i <parent.length ; i++) {
                parent[i]=i;
            }
            for (int i = 0; i <size.length ; i++) {
                size[i]=1;
            }
        }
//        public int findParent(int x){
//            while (parent[x]!=x){
//                parent[x]=parent[parent[x]];
//                
//            }
//        }
    }
    
}

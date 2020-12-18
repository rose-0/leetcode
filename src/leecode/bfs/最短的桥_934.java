package leecode.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class 最短的桥_934 {
    static int[][] dir = new int[][]{{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    public static int shortestBridge(int[][] A) {
        Queue<int[]>queue=new LinkedList<>();
        boolean[][]visit=new boolean[A.length][A[0].length];
        boolean found=false;
        out:
        for (int i = 0; i <A.length ; i++) {
            for (int j = 0; j <A[0].length ; j++) {
                if(A[i][j]==1){
                    dfs(A,i,j,visit);//一定要把两个1分开 将找到的第一个1全部变成2，可以通过bfs 或者 dfs找
                    break out;//dfs完成后 要退出两层循环
                }
            }
        }

        for (int i = 0; i <A.length ; i++) {
            for (int j = 0; j <A[0].length ; j++) {
                if(A[i][j]==2){
                    queue.add(new int[]{i,j});
                }
            }
        }
        int step=0;
        while (!queue.isEmpty()){
            int size=queue.size();
            for (int i = 0; i <size ; i++) {
                int[]cur=queue.poll();
                int curx = cur[0];
                int cury = cur[1];
                for (int j = 0; j < 4; j++) {
                    int newx = curx + dir[j][0];
                    int newy = cury + dir[j][1];
                    if(!isBound(A,newx,newy)||visit[newx][newy]){
                        continue;
                    }
                    if(A[newx][newy]==1){
                        return step;
                    }
                    visit[newx][newy]=true;
                    queue.add(new int[]{newx,newy});
                }
            }
            step++;
        }
        return -1;
    }
    public static void dfs(int[][] A,int i,int j,boolean[][]visit){
        if(A[i][j]==1){
            A[i][j]=2;
        }
        visit[i][j]=true;
        for (int k = 0; k <4 ; k++) {
            int newx = i + dir[k][0];
            int newy = j + dir[k][1];
            if(isBound(A,newx,newy)&&!visit[newx][newy]&&A[newx][newy]==1){
                dfs(A,newx,newy,visit);
//                visit[i][j]=true;
            }
        }
    }
    public static boolean isBound(int[][] grid, int x, int y) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length;
    }

    public static void main(String[] args) {
        int[][]A=new int[][]{
                {1,1,1,1,1},
                {1,0,0,0,1},
                {1,0,1,0,1},
                {1,0,0,0,1},
                {1,1,1,1,1}
        };
        System.out.println(shortestBridge(A));
    }

}

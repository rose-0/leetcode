package leecode.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class lee_01矩阵_542 {
    int[][] dir = new int[][]{{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    //自己的想法是从每个 1 处开始进行bfs
    public int[][] updateMatrix(int[][] matrix) {
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1) {
                    bfs(matrix, i, j);
                }
            }
        }
        return matrix;
    }

    public void bfs(int[][] matrix, int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        int step = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int curx = cur[0];
                int cury = cur[1];
                for (int j = 0; j < 4; j++) {
                    int newx = curx + dir[j][0];
                    int newy = cury + dir[j][1];
                    if (!isBound(matrix, newx, newy)) {
                        continue;
                    }
                    if (matrix[newx][newy] == 0) {
                        matrix[x][y] = step;
                        return;
                    }
                    queue.add(new int[]{newx, newy});
                }
            }
            step++;
        }
    }

    public boolean isBound(int[][] grid, int x, int y) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length;
    }

    /*
    大佬的解法：从所有的0开始进行多源bfs 对比 1162地图分析
    还可以使用 dp 看题解
     */
    public int[][] updateMatrix2(int[][] matrix) {
        Queue<int[]>queue=new LinkedList<>();
        boolean[][]visit=new boolean[matrix.length][matrix[0].length];
        for (int i = 0; i <matrix.length ; i++) {
            for (int j = 0; j <matrix[0].length ; j++) {
                if(matrix[i][j]==0){
                    visit[i][j]=true;
                    queue.add(new int[]{i,j});
                }
            }
        }
        int step=0;
        while (!queue.isEmpty()){
            int size=queue.size();
            for (int i = 0; i <size ; i++) {
                int[] cur = queue.poll();
                int curx = cur[0];
                int cury = cur[1];
                if(matrix[curx][cury]==1){
                    matrix[curx][cury]=step;
                }
                for (int j = 0; j < 4; j++) {
                    int newx = curx + dir[j][0];
                    int newy = cury + dir[j][1];
                    if (!isBound(matrix, newx, newy)) {
                        continue;
                    }
                    if(!visit[newx][newy]&&matrix[newx][newy]==1){
                        visit[newx][newy]=true;
                        queue.add(new int[]{newx,newy});
                    }
                }
            }
            step++;
        }
        return matrix;
    }
}
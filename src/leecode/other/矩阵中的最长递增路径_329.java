package leecode.other;
//这个和左神那个题目对比，这个四个方向都可以走，左神那个只能走两个方向
//理解 dp+dfs https://leetcode-cn.com/problems/longest-increasing-path-in-a-matrix/solution/javashi-xian-shen-du-you-xian-chao-ji-jian-dan-yi-/

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.function.BinaryOperator;

public class 矩阵中的最长递增路径_329 {
    public static int longestIncreasingPath(int[][] matrix) {
        int m=matrix.length;//m行n列
        if(m==0){
            return 0;
        }
        int n=matrix[0].length;
        int[][]step={{0,1},{0,-1},{1,0},{-1,0}};
        boolean[][]visit=new boolean[m][n];
        int max=1;
        int[][]dp=new int[m][n];
        for (int i = 0; i <m; i++) {//不要忘记这两个循环，而不是只是传个0，0就好了
            for (int j = 0; j <n ; j++) {
                max=Math.max(max,method(matrix,step,visit,i,j,dp));
            }
        }
        return max;
    }
    public static int method(int[][]matrix,int[][]step,boolean[][]visit,
                      int startx,int starty,int[][]dp){//返回i，j为起点的最长递增路径长度

        //        if(startx==0||starty==0||startx==matrix.length-1||starty==matrix[0].length-1){
//            return length+1;
//        }
        if(dp[startx][starty]!=0){
            return dp[startx][starty];
        }
        visit[startx][starty]=true;
        int temp=matrix[startx][starty];
        int max=1;
        for (int i = 0; i <4 ; i++) {
            int newx=startx+step[i][0];
            int newy=starty+step[i][1];
            if(hefa(matrix,newx,newy)&&!visit[newx][newy]){
                if(matrix[newx][newy]>temp){
                    max=Math.max(max,method(matrix,step,visit,newx,newy,dp)+1);//这里不要忘记加1
                }
            }
        }
        visit[startx][starty]=false;
        dp[startx][starty]=max;
        return max;
    }
    public static boolean hefa(int[][]matrix,int x,int y){
        int m=matrix.length;
        int n=matrix[0].length;
        if(x>=0&&x<m&&y>=0&&y<n){
            return true;
        }
        return false;
    }


    //dp+DFS记忆化搜索 https://www.jianshu.com/p/99d1e2ca99e8
    public static int longestIncreasingPathwithdp(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int max = 0;
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dp = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // loop 是返回每个节点开始的最长递增路径，max求所有节点中的最大值
                max = Math.max(max, loop(matrix, dp, i, j));
            }
        }
        return max;
    }

    //使用数组dp记录每个节点开始的最长升序路径长度
    static int[][]dir={{1,0},{-1,0},{0,-1},{0,1}};//一定要有一个0！！
    private static int loop(int[][] matrix,  int[][] dp, int i, int j) {
        if(!bound(i,j,matrix)){//这个条件不写也可以，因为下面dfs前已经判断了
            return 0;
        }
        if(dp[i][j]>0){
            return dp[i][j];
        }
        int max=0;//记录四个方向中的最大值
        for (int k = 0; k <4 ; k++) {
            int x = i + dir[k][0];
            int y = j + dir[k][1];
            if (bound(x, y, matrix) && matrix[i][j] < matrix[x][y]) {//注意这个大小条件，递增才可以dfs!!
                max=Math.max(max,loop(matrix,dp,x,y));
            }
        }
        dp[i][j]=max+1;//max是四周的最大值，dp[i][j]要将max加上自己
        return dp[i][j];
    }

    public static boolean bound(int x,int y, int[][]matrix){
        return x>=0&&x<matrix.length&&y>=0&&y<matrix[0].length;
    }

    //拓扑排序
    //https://leetcode-cn.com/problems/longest-increasing-path-in-a-matrix/solution/zhi-jie-tao-yong-tuo-bu-pai-xu-mo-ban-mei-you-ji-q/
    public static int longestIncreasingPathwithsort(int[][] matrix) {
        int[][]count = new int[matrix.length][matrix[0].length];//count存储入度，二维的，课程表那个是一维的
        for (int i = 0; i <matrix.length ; i++) {
            for (int j = 0; j <matrix[0].length ; j++) {
                for (int[]d:dir) {
                    if(bound(i+d[0],j+d[1],matrix)&&matrix[i+d[0]][j+d[1]]<matrix[i][j]){//周围比它小，它入度加1
                        count[i][j]++;
                    }
                }
            }
        }
        //课程表需要构造 List<List<Integer>>adjacency 记录节点指向的所有边，这个不需要，就是4个方向

        //队列放int[]标识每个点
        Queue<int[]>queue=new ArrayDeque<>();
        for (int i = 0; i <count.length ; i++) {
            for (int j = 0; j <count[0].length ; j++) {
                if(count[i][j]==0){
                    queue.add(new int[]{i,j});
                }
            }
        }
        int ans=0;//记录最长路径
        while (!queue.isEmpty()){
            ans++;
            //这个需要一层一层的全部出队列！
            //这个跟课程表I那个题不一样，需要一层一层的出列，而不是一个一个的出，因为课程表那个不关心队列长度
            for (int size = queue.size(); size >0 ; size--) {//size是>0
                int[]cur=queue.poll();
                for(int[]d:dir){
                    if(bound(cur[0]+d[0],cur[1]+d[1],matrix)
                            &&matrix[cur[0]+d[0]][cur[1]+d[1]]>matrix[cur[0]][cur[1]]){
                        count[cur[0]+d[0]][cur[1]+d[1]]=count[cur[0]+d[0]][cur[1]+d[1]]-1;
                        if(count[cur[0]+d[0]][cur[1]+d[1]]==0){
                            queue.add(new int[]{cur[0]+d[0],cur[1]+d[1]});
                        }
                    }
                }
            }
        }
        return ans;
    }

        public static void main(String[] args) {
        int[][]num={ {9,9,4},
                     {6,6,8},
                     {2,1,1}
                   };
        System.out.println(longestIncreasingPath(num));
            System.out.println(longestIncreasingPathwithdp(num));
    }
}

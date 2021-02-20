package leecode.other;

public class 由斜杠划分区域_959 {
    //https://leetcode-cn.com/problems/regions-cut-by-slashes/solution/mei-ge-xiao-ge-fen-jie-wei-3-3-fang-ge-qiu-lian-to/
    /*
    https://leetcode-cn.com/problems/regions-cut-by-slashes/solution/tu-jie-suan-fa-san-chong-xiang-xi-jie-fa-by-godwei/
    https://leetcode-cn.com/problems/regions-cut-by-slashes/solution/c-dong-hua-zhuan-huan-cheng-dao-yu-ge-sh-guve/
     */
    public int regionsBySlashes(String[] grid) {
        int n=grid.length;
        boolean[][]graph=new boolean[3*n][3*n];
        for (int i = 0; i <n ; i++) {
            for (int j = 0; j <n ; j++) {
                if(grid[i].charAt(j)=='/'){
                    graph[i*3][j*3+2]=true;
                    graph[i*3+1][j*3+1]=true;
                    graph[i*3+2][j*3]=true;
                }else if(grid[i].charAt(j)=='\\') {
                    graph[i*3][j*3]=true;
                    graph[i*3+1][j*3+1]=true;
                    graph[i*3+2][j*3+2]=true;
                }
            }
        }
        int res=0;
        for (int i = 0; i <n*3 ; i++) {
            for (int j = 0; j <n*3 ; j++) {
                if(graph[i][j]==false){
                    dfs(graph,i,j);
                    res++;
                }
            }
        }
        return res;
    }
    private void dfs(boolean[][]graph,int i,int j){
        int n=graph.length;
        if(i>=0&&j>=0&&i<n&&j<n&&graph[i][j]==false){
            graph[i][j]=true;
            dfs(graph,i,j+1);
            dfs(graph,i,j-1);
            dfs(graph,i+1,j);
            dfs(graph,i-1,j);
        }
    }
}

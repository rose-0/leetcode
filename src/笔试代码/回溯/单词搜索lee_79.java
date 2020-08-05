package 笔试代码.回溯;
//看别人写的
public class 单词搜索lee_79 {
    public static boolean exist(char[][] board, String word) {
        int m=board.length;
        int n=board[0].length;
        boolean[][]visit=new boolean[m][n];
        int[][]direction={{0,1},{0,-1},{1,0},{-1,0}};
        for (int i = 0; i <m ; i++) {
            for (int j = 0; j <n ; j++) {
                if(dfs(board,i,j,word,0,visit,direction)){
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean dfs(char[][]board,int i,int j,String word,int start,boolean[][]visit,int[][]direction) {
        if (start == word.length() - 1) {
            if (board[i][j] == word.charAt(start)) {
                return true;
            }
            return false;
        }

        if (board[i][j] == word.charAt(start)) {
            visit[i][j] = true;//先判断相等再设置标志
            for (int k = 0; k < 4; k++) {
                int x = i + direction[k][0];
                int y = j + direction[k][1];
                if (area(board, x, y)&&!visit[x][y]) {
                    if(dfs(board, x, y, word, start + 1, visit, direction)){
                        return true;
                    }
                }
            }
            visit[i][j] = false;//这儿不要忘记
        }
        return false;//这儿是false不是true！
    }
    public static boolean area(char[][]board,int x,int y){
        int m=board.length-1;
        int n=board[0].length-1;
        if(x>=0&&y>=0&&x<=m&&y<=n){
            return true;
        }
        return false;
    }

    public class Solution {

        private boolean[][] marked;

        //        x-1,y
        // x,y-1  x,y    x,y+1
        //        x+1,y
        private int[][] direction = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
        // 盘面上有多少行
        private int m;
        // 盘面上有多少列
        private int n;
        private String word;
        private char[][] board;

        public boolean exist(char[][] board, String word) {
            m = board.length;
            if (m == 0) {
                return false;
            }
            n = board[0].length;
            marked = new boolean[m][n];
            this.word = word;
            this.board = board;

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (dfs(i, j, 0)) {
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean dfs(int i, int j, int start) {
            if (start == word.length() - 1) {
                return board[i][j] == word.charAt(start);
            }
            if (board[i][j] == word.charAt(start)) {
                marked[i][j] = true;
                for (int k = 0; k < 4; k++) {
                    int newX = i + direction[k][0];
                    int newY = j + direction[k][1];
                    if (inArea(newX, newY) && !marked[newX][newY]) {
                        if (dfs(newX, newY, start + 1)) {
                            return true;
                        }
                    }
                }
                marked[i][j] = false;
            }
            return false;
        }

        private boolean inArea(int x, int y) {
            return x >= 0 && x < m && y >= 0 && y < n;
        }


    }
    public static void main(String[] args) {

//        char[][] board =
//                {
//                        {'A', 'B', 'C', 'E'},
//                        {'S', 'F', 'C', 'S'},
//                        {'A', 'D', 'E', 'E'}
//                };
//
//        String word = "ABCCED";


        char[][] board = {{'a'}};
        String word = "ab";

        boolean exist = 单词搜索lee_79.exist(board,word);
        System.out.println(exist);
    }


}

package leecode.dfs;

public class 解数独_37 {
    //https://leetcode-cn.com/problems/sudoku-solver/solution/hui-su-fa-jie-shu-du-by-i_use_python/
    //格子坐标 转为 一维的话，见上面这个链接的评论
    //该题目需要设置标记数组 标记哪些数字已经被使用来 相当于全排列问题
    public void solveSudoku(char[][] board) {
        //最后一个长度为10 表示数字 1-9
        boolean[][]rowUsed=new boolean[9][10];
        boolean[][]colUsed=new boolean[9][10];
        boolean[][][]boxUsed=new boolean[9][9][10];
        for (int row = 0; row <board.length ; row++) {
            for (int col = 0; col <board[0].length ; col++) {
                int num=board[row][col]-'0';
                if(num>=1&&num<=9){
                    //也可以使用 三个 hashset 来记录哪些数字出现过
                    rowUsed[row][num]=true;
                    colUsed[col][num]=true;
                    boxUsed[row/3][col/3][num]=true;
                }
            }
        }
        dfs(board,rowUsed,colUsed,boxUsed,0,0);
        return;

    }

    // boolean 因为这个题目是找到一个解后就立刻返回
    public boolean dfs(char[][]board,boolean[][]rowUsed,boolean[][]colUsed,boolean[][][]boxUsed,int row,int col){
        if(col==board[0].length){
            col=0;//检查下一行
            row++;
            if(row==board.length){
                // ?
                return true;
            }
        }
        if(board[row][col]=='.'){
            //在每个没有数字 位置上 进行 1-9 的数字枚举
            for (int num = 1; num <=9 ; num++) {
                // canUsed要为true  则 !false, 三个 || 要为false，则这三个都要为 false，即 每行 每列 每个格子 这个num均没有被使用
                boolean canUsed=!(rowUsed[row][num]||colUsed[col][num]||boxUsed[row/3][col/3][num]);
                if(canUsed){
                    rowUsed[row][num]=true;
                    colUsed[col][num]=true;
                    boxUsed[row/3][col/3][num]=true;

                    board[row][col]=(char)(num+'0');

                    if(dfs(board,rowUsed,colUsed,boxUsed,row,col+1)){
                        return true;
                    }

                    rowUsed[row][num]=false;
                    colUsed[col][num]=false;
                    boxUsed[row/3][col/3][num]=false;

                    board[row][col]='.';
                }
            }
        }else {
            //该位置上有数字 直接考虑下一个位置
            return dfs(board,rowUsed,colUsed,boxUsed,row,col+1);
        }
        // 前面都是 return true，如果前面把格子都填满了，那么就return true了，停止填格子，代码走到这里 说明前面没有填满 return false
        return false;
    }

    /*
    可以看看这个 先枚举位置 再枚举数字 感觉这个比较好理解一些
    https://leetcode-cn.com/problems/sudoku-solver/solution/37-jie-shu-du-hui-su-sou-suo-suan-fa-xiang-jie-by-/
     */
    public boolean  dfs2(char[][]board){
        for (int i = 0; i <board.length ; i++) {
            for (int j = 0; j <board[0].length ; j++) {
                if(board[i][j]!='.') continue;
                for (char k = '1'; k <= '9' ; k++) {
                    if(isValid(board,i,j,k)){
                        board[i][j]=k;
                        if(dfs2(board)){//不需要传 i j
                            return true;
                        }
                        board[i][j]='.';
                    }
                }
                // 这里直接return false也是不需要写dfs递归终止条件（下标越界）的原因
                return false;//注意这里 对位置 [i,j] 上的9个数字都尝试了，没有返回true 即没有解 所以返回false
            }
        }
        return true;//遍历完没有返回false
    }

    public boolean isValid(char[][]board,int row,int col,char num){
        for (int i = 0; i <9 ; i++) {
            if(board[row][i]==num){//检查该行是否有数字
                return false;
            }
        }
        for (int i = 0; i <9 ; i++) {
            if(board[i][col]==num){//检查该列是否有数字
                return false;
            }
        }
        int boxRow=(row/3)*3;//该位置所属的小格子的开始行
        int boxCol=(col/3)*3;//该位置所属的小格子的开始列
        //遍历该位置所属九宫格里面的所有数字
        for (int i = boxRow; i <boxRow+3 ; i++) {
            for (int j = boxCol; j <boxCol+3 ; j++) {
                if(board[i][j]==num){
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args) {
        boolean ues=false||true||false;
        System.out.println(ues);
    }
}

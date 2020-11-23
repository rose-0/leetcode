package leecode.回溯;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class n皇后_51 {
//    public List<List<String>> solveNQueens(int n) {
//
//    }
    public int method_num(int[]record,int i,int n){
        if(i==n){
            return 1;
        }
        int res=0;
        for (int j = 0; j <n ; j++) {
            if(isValid(record,i,j)){
                record[i]=j;
                res+=method_num(record,i+1,n);
            }
        }
        return res;
    }
    public boolean isValid(int[]record,int i,int j){
        for (int k = 0; k <i ; k++) {
            if(record[k]==j||Math.abs(i-k)==Math.abs(record[k]-j)){
                return false;
            }
        }
        return true;
    }
    //套用回溯框架
    //https://leetcode-cn.com/problems/permutations/solution/hui-su-suan-fa-xiang-jie-by-labuladong-2/
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> listall=new ArrayList<>();
        List<String>list=new ArrayList<>();
        char[][]board=new char[n][n];
        for (char[]chars:board) Arrays.fill(chars,'.');
        /*数组变成这样
        ...
        ...
        ...*/
        backtrack(board,0,listall);
        return listall;
    }
    //找第row行的有效位置，可以放则写上Q
    public static void backtrack(char[][]board,int row,List<List<String>> listall){
        if(row==board.length){//说明所有的有效位置都找到了
            listall.add(charToString(board));
            return;
        }
        int n =board[row].length;
        for (int col = 0; col <n ; col++) {//第row行上找哪一列合适
            if(!isValid(board,row,col)){
                continue;
            }
            board[row][col]='Q';
            backtrack(board,row+1,listall);
            board[row][col]='.';
        }
    }
    public static List<String> charToString(char[][]array){
        List<String>result=new ArrayList<>();
        for (char[]chars:array){
            result.add(String.valueOf(chars));
        }
        return result;
    }
    public static boolean isValid(char[][]board,int row,int col){
        int rows=board.length;
        // check is valid in col，不同行相同列是否有相同的Q
        //上面单层循环 都只会选择同一行的一个元素 所以不需要对行进行检查
        for (int i = 0; i <board[0].length ; i++) {
            if(board[i][col]=='Q'){
                return false;
            }
        }
        /*
        只需要检查右上方和左上方就可以了，因为其它位置还没有放棋子
        右上方：行减一，列加一；之后继续向左上方走
        左上方；行减一，列减一
         */
        // check is valide upright
        for (int i = row - 1, j = col + 1; i >= 0 && j < rows; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }
        // check is valide upleft
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }
        return true;
    }

    /*
    liweiwei https://leetcode-cn.com/problems/n-queens/solution/gen-ju-di-46-ti-quan-pai-lie-de-hui-su-suan-fa-si-/
    可以像全排列那样 为列、主对角线、副对角线 设置标记数组来判断位置合不合法
     */
    public static void main(String[] args) {
        solveNQueens(3);
    }
}

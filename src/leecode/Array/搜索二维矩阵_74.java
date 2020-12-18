package leecode.Array;

public class 搜索二维矩阵_74 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row=0;
        int col=matrix[0].length-1;
        while (row<matrix.length&&col>=0){//是col>=0 不是 >0
            if(target>matrix[row][col]){
                row++;
            }
            if(target<matrix[row][col]){
                col--;
            }
            if(target==matrix[row][col]){
                return true;
            }
        }
        return false;
    }
}
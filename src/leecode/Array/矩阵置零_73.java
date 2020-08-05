package leecode.Array;
//https://leetcode-cn.com/problems/set-matrix-zeroes/solution/o1kong-jian-by-powcai/
public class 矩阵置零_73 {
    public static void setZeroes(int[][] matrix) {
        boolean rowflag=false;
        boolean colflag=false;
        for (int i = 0; i <matrix[0].length ; i++) {
            if(matrix[0][i]==0){
                rowflag=true;
                break;//有0就可以break了
            }
        }
        for (int i = 0; i <matrix.length ; i++) {
            if(matrix[i][0]==0){
                colflag=true;
                break;
            }
        }
        //把中间0都移到第一行和第一列来体现
        for (int i = 1; i <matrix.length ; i++) {
            for (int j = 1; j <matrix[0].length ; j++) {
                if(matrix[i][j]==0){
                    matrix[i][0]=0;
                    matrix[0][j]=0;
                }
            }
        }
        //如果从0开始遍历，第一行有0，则先把第一行变为0，再者全部变为0
        //应从1开始遍历，利用首行首列的0（上面已经把中间0都移到首行首列）进行置0
        for (int i = 1; i <matrix.length ; i++) {
            for (int j = 1; j <matrix[0].length ; j++) {
                if(matrix[0][j]==0){
                    matrix[i][j]=0;
                }
                if(matrix[i][0]==0){
                    matrix[i][j]=0;
                }
            }
        }
        if(colflag){
            for (int i = 0; i <matrix.length ; i++) {
                matrix[0][i]=0;
            }
        }
        if(rowflag){
            for (int i = 0; i <matrix[0].length ; i++) {
                matrix[i][0]=0;
            }
        }

    }

    public static void main(String[] args) {
        int[][] matrix =
                {
                        {1, 1, 4, 1},
                        {7, 5, 0, 2},
                        {2, 1, 1, 1}
                };
        setZeroes(matrix);
        for (int i = 0; i <matrix.length ; i++) {
            for (int j = 0; j <matrix[0].length ; j++) {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }
}

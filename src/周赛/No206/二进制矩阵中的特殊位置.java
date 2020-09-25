package 周赛.No206;

public class 二进制矩阵中的特殊位置 {
    public static int numSpecial(int[][] mat) {
        int count=0;
        for (int i = 0; i <mat.length ; i++) {
            for (int j = 0; j <mat[0].length ; j++) {
                if(mat[i][j]==1){
                    if(isValid(mat,i,j)){
                        count++;
                    }
                }
            }
        }
        return count;
    }
    public static boolean isValid(int[][]mat,int i,int j){
        for (int k = 0; k <mat[0].length ; k++) {
            if(k!=j&&mat[i][k]==1){
                return false;
            }
        }
        for (int k = 0; k <mat.length ; k++) {
            if(i!=k&&mat[k][j]==1){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][]mat={
                {1,0,0},
                {0,0,1},
                {1,0,0}
        };
        System.out.println(numSpecial(mat));
    }
}

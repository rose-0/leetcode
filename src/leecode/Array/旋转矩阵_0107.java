package leecode.Array;
//lee 旋转矩阵
//左神 P333
//https://blog.csdn.net/Hollake/article/details/100514966
public class 旋转矩阵_0107 {
    public static int[][] rotate(int[][] matrix) {
        //对于m*n的矩阵
        int m=matrix.length;
        int n=matrix[0].length;
        int[][]reverse=new int[n][m];
        for (int i = 0; i <reverse.length ; i++) {
            for (int j = 0; j <reverse[0].length ; j++) {
//                reverse[j][m-1-i]=matrix[i][j];//这个也可以，但是对于m*n来说，就不行了
                /*
                旋转后   旋转前
                0，0     1，0
                0，1     0，0

                i,j      x,i

                x+j=旋转前的行数-1
                x=旋转前的行数-1-j
                 */
                reverse[i][j]=matrix[m-1-j][i];//这个也适合m*n m是原始矩阵的行数
            }
        }
        for (int i = 0; i <reverse.length ; i++) {
            for (int j = 0; j <reverse[0].length ; j++) {
                System.out.print(reverse[i][j]);
            }
            System.out.println();
        }
        return reverse;
    }

    //原地旋转 只能是n*n的，不能是m*n的
    public void rotate2(int[][]matrix){
        int a=0;
        int b=0;
        int c=matrix.length-1;
        int d=matrix[0].length-1;
        while (a<c){
            int times=d-b;
            int temp=0;
            /*可看左神 P334自己画的图
                    1     4      left 1
                                  13      4
                    13    16          16 right
             */
            //四个点    [a][b+i](0,0)第一个数1       [c-i][b](3,0)第四个数13
            //         [c][d-i](3,3)第三个数16      [a+i][d](0,3)第二个数4
            //
            for (int i = 0; i <times ; i++) {
                temp=matrix[a][b+i];  //存第一个数
                //把1保存了，就可以先处理1，1赋值13，再处理13，赋值16，逆时针保存
                matrix[a][b+i]  =  matrix[c-i][b];//第一个数 1=第4个数 13
                matrix[c-i][b] =  matrix[c][d-i];//第4个数 13=第3个数 16
                matrix[c][d-i]=  matrix[a+i][d];//第三个数 16=第二个数 4
                matrix[a+i][d] =  temp;
            }
            a++;
            b++;
            d--;
            c--;
        }
    }
}

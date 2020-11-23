package leecode.dfs;

public class 图像渲染_733 {
    static int[][]dir=new int[][]{{-1,0},{1,0},{0,1},{0,-1}};

    public static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        boolean[][] visit=new boolean[image.length][image[0].length];
        int oldColor=image[sr][sc];
        if(oldColor==newColor){
            return image;
        }
        //从一个点开始进行dfs，所以不需要使用visit数组进行标记
        //不需要visit数组的原因：如果进行了dfs 则颜色变成新的颜色，用颜色的更改表示已经进行了dfs，即和visit数组相同的作用
        //dfs都需要visit数组，防止死循环
        dfs(image,visit,sr,sc,newColor,oldColor);
        return image;
    }

    public static void dfs(int[][] image,boolean[][] visit,int x,int y,int newColor,int oldColor){
        image[x][y]=newColor;

        for (int i = 0; i <4 ; i++) {
            int newX=x+dir[i][0];
            int newY=y+dir[i][1];
            if(isValid(image,newX,newY)&&image[newX][newY]==oldColor){

                dfs(image,visit,newX,newY,newColor,oldColor);
            }
        }


    }
    public static boolean isValid(int[][] image,int x,int y){
        return x>=0&&x<image.length&&y>=0&&y<image[0].length;
    }

    public static void main(String[] args) {
        int[][]board=new int[][]{
                {0,0,0},
                {0,1,1}
        };
        floodFill(board,1,1,1);
    }
}

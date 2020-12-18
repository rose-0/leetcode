package leecode.DP;

public class 最大矩形_85 {
    //方法1 感觉比较容易理解一些
    //https://leetcode-cn.com/problems/maximal-rectangle/solution/java-chang-gui-dong-gui-si-lu-shi-pin-jiang-jie-da/
    public int maximalRectangle(char[][] matrix) {
        if(matrix.length==0){
            return 0;
        }
        int[][] dp = new int[matrix.length][matrix[0].length];
        //char 转 int
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = 1;
                }
            }
        }

        //求出每一点的高度
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (dp[i - 1][j] >= 1) {
                    dp[i][j] = dp[i][j] == 1 ? dp[i - 1][j] + 1 : 0;
                }
            }
        }

        int area = 0;

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (dp[i][j] == 0) {
                    continue;
                }
                int curHeight = dp[i][j];
                //找宽度
                for (int k = j; k >= 0 && dp[i][k] != 0; k--) {
                    int curWidth = j - k + 1;
                    curHeight = Math.min(curHeight, dp[i][k]);//当前高度也要更新
                    area = Math.max(area, curHeight * curWidth);
                }
            }
        }
        return area;
    }

    /*
    感觉像是左神的那个方法
    https://leetcode-cn.com/problems/maximal-rectangle/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-1-8/
     */
    public int maximalRectangle2(char[][] matrix) {
        int maxArea=0;
        int[][] help = new int[matrix.length][matrix[0].length];
        //更新 以当前数字结尾 每行连续1的个数 等价于 记录宽度
        for (int i = 0; i <help.length ; i++) {
            for (int j = 0; j <help[0].length ; j++) {
                if(matrix[i][j]=='1'){
                    if(j==0){//不要越界
                        help[i][j]=1;
                    }else {
                        help[i][j]=help[i][j-1]+1;//前一列
                    }
                }else {
                    help[i][j]=0;
                }
                //和方法一的差别是向上扩展
                int minWidth=help[i][j];
                for (int k = i; k >=0 ; k--) {
                    int curHeight=i-k+1;
                    //更新宽度，和方法一 更新高度一样
                    minWidth=Math.min(minWidth,help[k][j]);
                    maxArea=Math.max(maxArea,minWidth*curHeight);
                }
            }
        }
        return maxArea;
    }
    /*
    方法三 调用lee84 题目的方法
    https://leetcode-cn.com/problems/maximal-rectangle/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-1-8/
     */
}
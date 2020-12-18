package leecode.Array;

public class 有序矩阵中第K小的元素_378 {
    /*
    二分查找 left right可以是数，也可以是下标，该题目是数字

    当前行的最后一个元素并不一定会小于下一行的首元素，但是每一行 每一列是有序的，即保证左上角 最小 右下角 最大
    https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix/solution/378java-er-fen-fa-tu-jie-you-xian-dui-lie-liang-ch/
    使用二分查找的题目 其查找范围都有一个值域

    这样怎么保证最终的right值是矩阵中的值？
    求出矩阵元素排序后，把矩阵分成两份，求使得前一份包含k个元素的数值范围左边界值，二分查找就是找到这个左边界值

    这个里面有代码执行的例子: https://www.cnblogs.com/grandyang/p/5727892.html
     */
    public int kthSmallest(int[][] matrix, int k) {
        int left=matrix[0][0];
        int right=matrix[matrix.length-1][matrix[0].length-1];
        while (left<right){
            int mid=(left+right)/2;
            int count=calNum(matrix,mid);
            if(count<k){
                // 第k小的数在右半部分，且不包含mid
                left=mid+1;
            }else {
                right=mid;
            }
        }
        return left;
    }
    public int calNum(int[][]matrix,int mid){
        int row=0;
        int col=matrix[0].length-1;
        int sum=0;
        while (row<matrix.length&&col>=0){
            if(mid>=matrix[row][col]){//大于等于当前行的最后一个
                sum+=col+1;//sum + 列+1
                row++;//比较下一行
            }else {
                col--;
            }
        }
        return sum;
    }
}

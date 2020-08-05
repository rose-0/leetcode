package leecode.Array;

import java.util.ArrayList;
import java.util.List;

public class 螺旋矩阵_54 {
    public List<Integer> spiralOrder(int[][] matrix) {
        int left=0;
        int right=matrix[0].length-1;
        int up=0;
        int down=matrix.length-1;
        List<Integer>list=new ArrayList<>();
        while (true){
            //循环1 3；循环2 4正好是相反的方向
            /*
            1： 从左到右 up++
            2： 从上到下 right--;
            3:  从右到左 down--;
            4:  从下到上 left++
             */
            for (int i = left; i <=right ; i++) {//[left,right]
                list.add(matrix[up][i]);//行是 up！！上边界
            }
            up++;
            if(up>down){
                break;
            }
            for (int i = up; i <=down ; i++) {
                list.add(matrix[i][right]);
            }
            right--;
            if(right<left){
                break;
            }
            for (int i = right; i >=left ; i--) {
                list.add(matrix[down][i]);
            }
            down--;//不是++
            if(down<up){
                break;
            }
            for (int i = down; i >=up ; i--) {
                list.add(matrix[i][left]);
            }
            left++;
            if(left>right){
                break;
            }
        }
        return list;
    }
}

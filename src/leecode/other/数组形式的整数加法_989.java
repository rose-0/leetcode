package leecode.other;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class 数组形式的整数加法_989 {
    //思路： https://leetcode-cn.com/problems/add-to-array-form-of-integer/solution/shu-zu-xing-shi-de-zheng-shu-jia-fa-by-l-jljp/
    //代码 看第一个评论 https://leetcode-cn.com/problems/add-to-array-form-of-integer/comments/
    
    public List<Integer> addToArrayForm(int[] A, int K) {
        int len =A.length;
        int lastNum = K;
        LinkedList<Integer> linkedList = new LinkedList<>();
        int i=len-1;
        while (i>=0||lastNum>0){
            if(i>=0){
                lastNum=lastNum+A[i];
            }
            linkedList.addFirst(lastNum%10);
            lastNum=lastNum/10;
            i--;
        }
        return linkedList;
    }
}

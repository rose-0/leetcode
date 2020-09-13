package leecode.Array;
//https://leetcode-cn.com/problems/majority-element-ii/solution/duo-shu-tou-piao-de-sheng-ji-ban-hao-li-jie-java-b/
import java.util.ArrayList;
import java.util.List;
//  求出现次数超过1/3的元素
public class 求众数II_229 {
    public List<Integer> majorityElement(int[] nums) {
        int candidateA=nums[0];
        int candidateB=nums[0];
        int countA=0;
        int countB=0;
        for (int i = 0; i <nums.length ; i++) {
            if(nums[i]==candidateA){
                countA++;
                continue;
            }
            if(nums[i]==candidateB){
                countB++;
                continue;
            }
            if(countA==0){
                candidateA=nums[i];
                countA++;
                continue;
            }
            if(countB==0){
                candidateB=nums[i];
                countB++;
                continue;
            }
            //若此时两个候选人的票数都不为0，且当前元素不投AB，那么A,B对应的票数都要--;
            countA--;
            countB--;
        }
        //上一轮遍历找出了两个候选人，但是这两个候选人是否均满足票数大于N/3仍然没法确定，需要重新遍历，确定票数
        countA = 0;
        countB = 0;
        List<Integer>res=new ArrayList<>();
        for (int num : nums) {
            if (num == candidateA)
                countA++;
            else if (num == candidateB)
                countB++;
        }
        if (countA > nums.length / 3)
            res.add(candidateA);
        if (countB > nums.length / 3)
            res.add(candidateB);
        return res;


    }
}

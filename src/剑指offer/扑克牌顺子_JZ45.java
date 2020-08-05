package 剑指offer;

import java.util.Arrays;

public class 扑克牌顺子_JZ45 {
    public class Solution {
        public boolean isContinuous(int [] numbers) {
            if(numbers==null||numbers.length==0){
                return false;
            }
            Arrays.sort(numbers);
            int numberOfZero=0;
            int sumInterval=0;
            for(int i=0;i<numbers.length-1;i++){
                if(numbers[i]==0){
                    numberOfZero++;
                    continue;
                }
                if(numbers[i]==numbers[i+1]){
                    return false;
                }
                sumInterval+=numbers[i+1]-numbers[i]-1;//记得减1
            }
            return numberOfZero>=sumInterval;
        }
    }

}

package leecode.other;
//https://leetcode-cn.com/problems/single-number-ii/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--31/
//解法三
// https://www.jianshu.com/p/0236b51b903f
// << 左移 相当于 *2  >>右移 相当于 /2  >>> 无符号右移，空位以0补齐
public class 只出现一次的数字II_137 {
    // i<<2 i左移2位 左移一位相当于*2
    public int singleNumber(int[]nums){
        int ans=0;
        for (int i = 0; i <32 ; i++) {
            int count =0;
            //遍历每一个数，求所有的数在第i位上1的个数和count，所以把i放在外侧循环
            for (int j = 0; j <nums.length ; j++) {
                if((nums[j]>>>i & 1)==1){
                    count++;
                }
            }
            if(count%3!=0){
                ans=ans|1<<i;//所有不是3的倍数的列写1（1左移i位，做或操作），其他列写0
            }
        }
        return ans;
    }
}

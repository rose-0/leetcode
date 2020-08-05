package leecode.Array;
//https://github.com/labuladong/fucking-algorithm/blob/master/%E9%AB%98%E9%A2%91%E9%9D%A2%E8%AF%95%E7%B3%BB%E5%88%97/%E7%BC%BA%E5%A4%B1%E5%92%8C%E9%87%8D%E5%A4%8D%E7%9A%84%E5%85%83%E7%B4%A0.md
/*
这种数组问题，关键点在于元素和索引是成对儿出现的，常用的方法是排序、异或、映射。
映射的思路就是我们刚才的分析，将每个索引和元素映射起来，通过正负号记录某个元素是否被映射。
 */
public class 错误的集合_645 {
    public int[] findErrorNums(int[] nums) {
        int[]err=new int[2];
        for (int i = 0; i <nums.length ; i++) {
            int index=Math.abs(nums[i])-1;//数组是从1开始的，所以下标和值差1
            if(nums[index]<0){//索引对应的元素已经是负数，说明出现了重复元素
                err[0]=nums[index]*(-1);
            }else {//索引对应的元素变成负数
                nums[index]=nums[index]*(-1);
            }
        }
        //有两个元素对应到了同一个索引，而且会有一个索引没有元素对应过去。
        // 找到那个没有元素对应的索引，不就是找到了那个缺失的元素了
        //现在索引对应的元素已经都变成负数了，找到还是正数的元素，就是没有索引对应它
        for (int i = 0; i <nums.length ; i++) {
            if(nums[i]>0){
                err[1]=i+1;
            }
        }
        return err;

    }
}

package leecode.Array;

public class 缺失的第一个正数_41 {
    //数组看作hash表：将数值为i的数映射到下标为i-1位置处，找第一个正整数，所以0处应该放1 而不是0
    public int firstMissingPositive(int[] nums) {
        int len=nums.length;
        for (int i = 0; i <len ; i++) {
            // 3 4 -1 1
            // nums[0]=3,应该放在 下标为 nums[i]-1 位置处(0位置处的3应该放在下标2处)，
            // 看下标2处是不是3，不是则交换
            //i位置上的数，应该在下标为 它减1的位置上面，
            // 所以看下下标为它减1的位置上的值（nums[nums[i]-1]）放的是不是它（nums[i]）
            //不是，则应该交换，把它放在正确的位置上
            //nums[i]>0&&nums[i]<=len >0是因为要把正整数放在它们的下标上 <=len 是len-1上放置len

            /*
            nums[i] < 1 && nums[i] > len，那么直接舍弃
            比如 nums = [7, 8, 9, 10, 11], len = 5
            我们发现数组中的元素都无法进行填充，直接舍弃跳过，
            那么最终遍历数组的时候，我们发现 nums[0] != 0 + 1，即第一个缺失的是 1

            为什么使用 while ？ 因为交换后，原本 i 位置的 nums[i] 已经交换到了别的地方，
            交换后到这里的新值不一定是适合这个位置的，因此需要重新进行判断交换
            如果使用 if，那么进行一次交换后，i 就会 +1 进入下一个循环，那么交换过来的新值就没有去找到它该有的位置
            比如 nums = [3, 4, -1, 1] 当 3 进行交换后， nums 变成 [-1，4，3，1]，
            此时 i == 0，如果使用 if ，那么会进入下一个循环， 这个 -1 就没有进行处理
             */
            while (nums[i]>0&&nums[i]<=len&&nums[i]!=nums[nums[i]-1]){ //不是nums[i]！=i+1 这样不对，比较两个num值才行
                //指定范围内，没有放在正确位置上，则交换
                swap(nums,nums[i]-1,i);
            }
        }
        for (int i = 0; i <len ; i++) {
            if(nums[i]!=i+1){
                return i+1;//返回的是下标+1，不是值
            }
        }
        return len+1;//注意这个
    }
    public void swap(int[]nums,int index1,int index2){
        int temp=nums[index1];
        nums[index2]=nums[index1];
        nums[index1]=temp;
    }
}

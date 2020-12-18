package leecode.Array;

public class 寻找两个正序数组的中位数_4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        /*
        https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/he-bing-yi-hou-zhao-gui-bing-guo-cheng-zhong-zhao-/
        看下面的评论回复
        让 num1 为 长度较小的数组，num2为长度较大的数组
        是交换数组 nums1 和数组 nums2 真正的原因，它的附加效果是索引 i 的搜索区间更短。
        索引 j 是根据索引 i 计算得到的，索引 i 肯定不会越界，因为它是主动的，在 nums1 范围内移动，
        但是 j 这个被动的索引就很有可能越界，当索引 i 的搜索区间很长，j就十分容易越界；
         */
        int m = nums1.length;
        int n = nums2.length;

        /*
        分割线左边的所有元素需要满足的个数 m + (n - m + 1) / 2;
        (m + n + 1) / 2 对于长度为偶数，因为除法是向下取整，所以 = (m+n)/2;
        对于长度为奇数，(m+n+2-1)/2是向上取整，被除数+除数-1
         */
        int totalLeft = (m + n + 1) / 2;

        // 在 nums1 的区间 [0, m] 里查找恰当的分割线，
        // 使得分割线满足交叉的条件： nums1[i - 1] <= nums2[j] && nums2[j - 1] <= nums1[i]
        int left = 0;
        int right = m;

        while (left < right) {
            /*
            因为left不收缩，所以 +1
            找到下标i，意味着分割线左边有i个元素
             */
            int i = left + (right - left + 1) / 2;
            int j = totalLeft - i;
            //上面 i 取的是 左+右+1 即使只有1个数 i=0+1+1 所以i-1也不会越界
            // if 后面的条件就是 不满足交叉条件，交叉条件是两个都为真，所以直接对一个条件取假 即可，
            // 也可以取另外一个条件 nums2[j - 1] > nums1[i] left=i+1,中位数也不需要+1
            if (nums1[i - 1] > nums2[j]) {
                // 下一轮搜索的区间 [left, i - 1]
                right = i - 1;
            } else {
                // 下一轮搜索的区间 [i, right]
                left = i;
            }
        }

        int i = left;
        int j = totalLeft - i;

        //Integer.MIN_VALUE 让其在比较最大值的时候不被选中
        int nums1LeftMax = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];

        int nums1RightMin = i == m ? Integer.MAX_VALUE : nums1[i];

        int nums2LeftMax = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];

        int nums2RightMin = j == n ? Integer.MAX_VALUE : nums2[j];

        //数组之和为奇数
        if (((m + n) % 2) == 1) {
            //左边最大值
            return Math.max(nums1LeftMax, nums2LeftMax);
        } else {
            return (double) ((Math.max(nums1LeftMax, nums2LeftMax) + Math.min(nums1RightMin, nums2RightMin))) / 2;
        }
    }
    /*
    https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/jiang-qi-zhuan-wei-zhao-liang-ge-you-xu-shu-zu-de-/
    寻找两个有序数组第k小的数字的转化
     */
}

package leecode.Array;
//见到logn就想到二分查找
//为什么二分查找大的那一半一定会有峰值呢？ 大的一侧一定有，不是代表小的一侧没有，这个题只要找到一个即可
// （即nums[mid]<nums[mid+1]时，mid+1~N一定存在峰值）
// 我的理解是，首先已知 nums[mid+1]>nums[mid]，那么mid+2只有两种可能，
// 一个是大于mid+1，一个是小于mid+1，小于mid+1的情况，那么mid+1就是峰值，大于mid+1的情况，
// 继续向右推，1，3，5，6，4和1，3，5，6，7
//如果一直到数组的末尾都是大于的，那么可以肯定最后一个元素是峰值，因为nums[nums.length]=负无穷
public class 寻找峰值_162 {
    //下面的方法简单
    public static int findPeakElement(int[] nums) {
        int low=0;
        int high=nums.length-1;
        while (low<high) {
            int mid = (low + high) / 2;
            if(nums[mid]<nums[mid+1]){
                if(mid+2>high||nums[mid+2]<nums[mid+1]){
                    return mid+1;
                }
                low=mid;//不是mid+1
            }else {
                if(mid-1<low||nums[mid-1]<nums[mid]){
                    return mid;
                }
                high=mid;
            }
        }
        return low;
    }
    private static  int findPeakElement2(int[] nums) {
        int left = 0, right = nums.length - 1;
        for (; left < right; ) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[mid + 1]) {
                /*
                //不能写成mid
                case: 1,2,3,1

                规律一：如果nums[i] > nums[i+1]，则在i之前一定存在峰值元素
                规律二：如果nums[i] < nums[i+1]，则在i+1之后一定存在峰值元素  即nums[i]肯定不是峰值，所以排除掉
                 */
                left = mid+1;
            } else {
                System.out.println("111");
                right = mid;
            }
        }
        return left;
    }
    public boolean quli(int[]nums,int index){
        if(index>0&&index<nums.length-1&&nums[index]>nums[index-1]&&nums[index]>nums[index+1]){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[]num={1,2,3,1};
//        System.out.println(findPeakElement(num));
        System.out.println(findPeakElement2(num));
    }
}

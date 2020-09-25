package leecode.Array;
//左神的书有两个，P435 一个是最小值，一个是存在

/*
将数组一分为二，其中一定有一个是有序的，
另一个是部分有序。
此时有序部分用二分法查找。
无序部分再一分为二，其中一个一定有序，另一个可能有序，可能无序。就这样循环.
 */
public class 搜索旋转排序数组_33 {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            /*
            mid将数组一分为二，判断target在前面还是后面
            利用 一半有序这个特性 看target在有序那部分还是无序那部分
            所以先找出哪部分有序，再看target的位置
             */
            //二分查找是比较 nums[mid] 和 target 的关系
            if (nums[left] <= nums[mid]) {// left--mid是有序数组
                if (nums[left] <= target && target < nums[mid]) { //target在前面，注意 <=
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            /*
            3,1   target=1
            left=0; right=1;
            mid=0;
            left==mid, nums[left]=3  nums[mid]=3  nums[right]=1
            由于除法向下取整，这时如果写成nums[left]<nums[mid] 则判断后面有序 去后面找 就错了
            所以 写成nums[left]<= nums[mid]
            */
            else {//mid -- right 是有序数组
                if (nums[mid] < target && target <= nums[right]) { //注意 <=
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    //查找有序旋转数组是否存在数
    public Boolean exist(int[] arr, int num) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        while (low <= high) {
            mid = (low + high) / 2;
            if (arr[mid] == num) {
                return true;
            }
            if (arr[low] == arr[mid] && arr[mid] == arr[high]) {
                while (low != mid && arr[low] == arr[mid]) {
                    low++;
                }
                if (low == mid) {
                    low = mid + 1;
                    continue;
                }
            }
            if (arr[low] != arr[mid]) {
                if (arr[low] < arr[mid]) {
                    if (num > arr[low] && num < arr[high]) {
                        high = mid - 1;
                    } else {
                        low = mid + 1;
                    }
                } else {
                    if (num > arr[mid] && num <= arr[high]) {
                        low = mid + 1;
                    } else {
                        high = mid - 1;
                    }
                }
            } else {
                ///
            }
        }
        return false;
    }

    //二分 liweiwei 在数组中查找符合条件的元素的下标
    public int search2(int[] nums, int target) {
        int left=0;
        int right=nums.length-1;

        while (left<right){
            int mid=(left+right)>>>1;

            //nums[left]<nums[mid] 就错了，比较迷
            //可见下面评论讨论 https://leetcode-cn.com/problems/search-in-rotated-sorted-array/solution/er-fen-fa-python-dai-ma-java-dai-ma-by-liweiwei141/
            // 可能是两个数的时候 对于【3，1】这种两个元素的螺旋数组，
            // 如果用左中位数，if (nums[mid]>nums[left])这个条件是无法正确判断mid左和mid右哪边是有序的
            // mid=0,left=0，导致程序认为[mid,right]有序，实际上是[mid+1,right]有序，可能是这个原因
            if(nums[left]<=nums[mid]){//先找有序区间 [left, mid] 有序
                if(nums[left]<=target&&target<=nums[mid]){
                    //把比较好些的判断（target 落在有序的那部分）放在 if 的开头考虑，把剩下的情况放在 else 里面。
                    //[left,mid]
                    right=mid;
                }else {
                    //[mid+1,right]
                    left=mid+1;
                }
            }else {
                // [mid+1,right]有序
                if(nums[mid+1]<=target&&target<=nums[right]){//target<=nums[right]不然就错了
                    // [mid+1,right]
                    left=mid+1;
                }else {
                    right=mid;
                }
                /*
                else里面这样写也可以
                if(nums[mid]<=target&&target<=nums[right]){
                    // [mid+1,right]
                    left=mid;
                }else {
                    right=mid-1;
                }
                 */
            }
        }
        if(nums[left]==target){
            return left;
        }
        return -1;
    }
}

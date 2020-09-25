package Sort;

public class 二分查找_liweiwei {
    /*
    https://blog.csdn.net/qq_43584847/article/details/102845035
    https://blog.csdn.net/zhicheshu4749/article/details/103086361?utm_medium=distribute.pc_relevant.none-task-blog-blogcommendfrommachinelearnpai2-4.nonecase&depth_1-utm_source=distribute.pc_relevant.none-task-blog-blogcommendfrommachinelearnpai2-4.nonecase
    https://leetcode-cn.com/problems/search-insert-position/solution/te-bie-hao-yong-de-er-fen-cha-fa-fa-mo-ban-python-/
     */
    /*
    1、求中间位置
        int mid = (left + right) / 2;   //越界风险
        int mid = left + (right - left) / 2;    //right为负，也可能越界
        //写成下面形式
        左中位数：int mid = left + (right - left) / 2 ;
        右中位数：int mid = left + (right - left + 1) / 2 ;

     2、循环条件：
        while(left <= right) ：是在 循环体内部查找元素，退出循环时，要分析 left、right位置上的值
        while(left<right) ：是在 在循环体内部排除元素 ，每一轮的循环中排除一半以上的元素，最后
                            退出循环时，left==right，可以把区间“夹逼”只剩下1个数，判断即可

     3、分支逻辑：
        循环内只写两个分支，一个分支排除中位数，另一个分支不排除中位数，循环中不单独对中位数做判断
        情况1：
        if(排除中位数的逻辑){
            left=mid+1;
        }else{
            right=mid;
        }
        情况2：
        if(排除中位数的逻辑){
            right=mid-1;
        }else{
            left=mid;
        }
        例如：实现 int sqrt(int x) 函数。计算并返回 x 的平方根，且整数，例如 5的平方根约等于 2.236，
        在这道题应该返回 2。因此如果一个数的平方小于或者等于 x，那么这个数有可能是也有可能不是 x 的平方根，
        但是能很肯定的是，如果一个数的平方大于 x ，这个数肯定不是 x 的平方根。
        if(x*x > num) {//x肯定不是，找比x小的
            right = mid - 1;
        }
        else {
            left = mid;
        }
        lee35 找到“大于或者等于目标值的第 1 个数的索引”
        如果中位数小于目标值，它就应该被排除，左边界left就至少是mid+1；
        如果中位数大于等于目标值，因此右边界就不能把mid排除，因此右边界right至多是mid，
        if(排除中位数的逻辑){
            left=mid+1;
        }else{
            right=mid;
        }
     4、选择中位数的类型 左中位数 or 右中位数，避免死循环
        死循环1：
        左中位数：int mid = left + (right - left) / 2 ;
        if(排除中位数){
            right=mid-1;
        }else{
            left=mid;//当只有两个数时，mid向下取整=left，left=mid，左边界不收缩，
                        循环一直走else会死循环，应该选择右中位数，
        }
        死循环2：
        右中位数：int mid = left + (right - left+1) / 2 ;
        if(排除中位数){
            left=mid+1;
        }else{
            right=mid;//当只有两个数时，mid向上取整=right，right=mid，右边界不收缩，
                        循环一直走else会死循环，应该选择右中位数，
        }

      5、退出循环时候，单独判断一下


      写题目步骤：
        1、无脑的写while left <right;
        2、先写分支逻辑，并且先写排除中位数的逻辑分支（不绝对）
            把区间分为 2 个部分（一个部分肯定不存在目标元素，另一个部分有可能存在目标元素），
            始终思考下一轮搜索区间是什么，如果是 [mid, right] 就对应 left = mid ，
            如果是 [left, mid - 1] 就对应 right = mid - 1，是保留 mid 还是 +1+1、-1−1 就在这样的思考中完成；


        3、先默认将中位数写成左中位数，根据分支情况看要不要改为右中位数
            区间划分 决定中间数取法
            //左中位数 左边界收缩 left=mid+1 右中位数 右边界要收缩 right=mid-1;
            区间划分为 [left, mid] 与 [mid + 1, right] ，mid 被分到左边，对应 int mid = left + (right - left) / 2;
            即mid = left 区间等价于 [left,left] [right,right]
            区间划分为 [left, mid - 1] 与 [mid, right] ，mid 被分到右边，对应 int mid = left + (right - left + 1) / 2;。


        4、退出循环判断
     */
}

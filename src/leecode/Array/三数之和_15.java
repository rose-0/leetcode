package leecode.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventListener;
import java.util.List;

public class 三数之和_15 {





    //两数之和 目标为target的 见labuladong  https://mp.weixin.qq.com/s/fSyJVvggxHq28a0SdmZm6Q
    public static List<List<Integer>> fin(int[]nums,int start,int target){//加start是为了供三数之和使用
        List<List<Integer>>res=new ArrayList<>();
        Arrays.sort(nums);
        int low=start;
        int height=nums.length-1;
        while (low<height){
            int left=nums[low];
            int right=nums[height];
            int sum=left+right;
            if(sum<target){
                low++;//是low，不是left
            }else if(sum>target){
                height--;
            }else {
                res.add(Arrays.asList(nums[low], nums[height]));

                //去除重复元素！！！
                while (low<height&&nums[low]==left){
                    low++;
                }
                while (low<height&&nums[height]==right){
                    right--;
                }
            }
        }
        return res;
    }

    public static List<List<Integer>> threeSum(int[]nums,int target){
        Arrays.sort(nums);
        List<List<Integer>>res=new ArrayList<>();
        List<List<Integer>>twores=new ArrayList<>();
        for (int i = 0; i <nums.length ; i++) {
            //找 i+1 之后 和为 target-nums[i] 的两个数
            twores=fin(nums,i+1,target-nums[i]);//leecode上是没有target的！！
            for(List<Integer>list:twores){
                List<Integer>list1=new ArrayList<>(list);
                list1.add(nums[i]);
                res.add(new ArrayList<>(list1));
            }
            //跳过重复元素
            while (i<nums.length-1&&nums[i]==nums[i+1]){
                i++;
            }
        }
        return res;
    }


    /*
    可以看这个回溯的解法！：
    https://leetcode-cn.com/problems/4sum/solution/18-si-shu-zhi-he-javashi-xian-hui-su-fa-jian-zhi-b/

     */





    //数组：排序，hash，双指针，前缀和等 解题方法的组合 解题大方向一定要有
    //比如这个题目 就是排序+双指针
    //https://leetcode-cn.com/problems/3sum/solution/hua-jie-suan-fa-15-san-shu-zhi-he-by-guanpengchn/
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>>ans=new ArrayList<>();
        int len=nums.length;
        if(nums==null||len<3){
            return ans;
        }
        Arrays.sort(nums);
        for (int i = 0; i <len-2 ; i++) {//写成len-2好一些，如果写成len的话，也可以，下面L<R会卡条件 不会执行
            if(nums[i]>0)break;
            if(i>0&&nums[i]==nums[i-1])continue;//去重复 注意 i>0

            int L=i+1;
            int R=len-1;
            while (L<R){
                int sum=nums[i]+nums[L]+nums[R];
                if(sum==0){
                    ans.add(Arrays.asList(nums[i],nums[L],nums[R]));
                    //要先去重，再移动指针；先移动指针是不对的！！！先移动指针，可能因为重复问题，导致最后实际没有移动
                    //case:[-2,0,1,1,2] 此时 left指向0  right指向2
                    //如果先移动指针，则 left指向1 right 指向1，再去重，就丢掉 -2，1，1这个解
                    while (L < R && nums[L] == nums[L+1]) {
                        L++;//去重,sum=0时候
                    }
                    while (L<R&&nums[R]==nums[R-1]){
                        R--;//去重
                    }
                    L++;//sum=0时左右指针同时移动，不要忘记这个！！
                    R--;
                }
                else if(sum<0){
                    L++;
                }
                else if(sum>0){
                    R--;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[]nums={-1, 0, 1, 2, -1, -4};
        List<List<Integer>>res=threeSum(nums,0);
        for(List<Integer>list:res){
            for(int temp:list){
                System.out.print(temp+" ");
            }
            System.out.println();
        }
    }
}

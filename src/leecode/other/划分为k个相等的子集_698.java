package leecode.other;

public class 划分为k个相等的子集_698 {
    //https://leetcode-cn.com/problems/partition-to-k-equal-sum-subsets/solution/javadai-fan-hui-zhi-de-hui-su-fa-by-caipengbo/
    public  static boolean canPartitionKSubsets(int[] nums, int k) {
        int sum=0;
        int maxNum=0;
        for (int i = 0; i <nums.length ; i++) {
            sum=sum+nums[i];
            maxNum=maxNum>nums[i]?maxNum:nums[i];
        }
        if(sum%k!=0||sum/k<maxNum){
            return false;
        }
        boolean[]visit=new boolean[nums.length];
        return dfs(nums,sum/k,visit,0,0,k);
    }
    //子集 问题修改过来的
    public static boolean dfs(int[]nums,int target,boolean[]visit,int cur,int start,int k){
        if(k==0){
            return true;
        }
        if(cur==target){
            // 找下一个子集 这里要注意
            return dfs(nums,target,visit,0,0,k-1);
        }

        for (int i = start; i <nums.length ; i++) {
            if(!visit[i]&&cur+nums[i]<=target){

                    visit[i]=true;
                    if(dfs(nums,target,visit,cur+nums[i],i+1,k)) {//这个判断一定要加
                        return true;
                    }

                    visit[i]=false;
                    /*错误的写法
                    if(!visit[i]){
                        cur=cur+nums[i];//这样即使不进行dfs，cur的值也发生了改变 cur只能在dfs时候才能改变
                        if(cur<=target){
                            visit[i]=true;
                            if(dfs(nums,target,visit,cur,i+1,k)){
                                return true;
                            }
                            visit[i]=false;
                         }
                    }
                     */

            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[]num=new int[]{4, 3, 2, 3, 5, 2, 1};
        System.out.println(canPartitionKSubsets(num,4));
    }
}

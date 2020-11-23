package leecode.Array;

import java.util.*;

public class 全排列II_47 {
    //https://leetcode-cn.com/problems/permutations-ii/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liwe-2/
    public List<List<Integer>> permuteUnique(int[] nums) {
        int n=nums.length;
        boolean[]used=new boolean[n];
        List<List<Integer>>res=new ArrayList<>();
        List<Integer>path=new ArrayList<>();
        Arrays.sort(nums);
        dfs(res,path,nums,0,used);
        return res;
    }

    public void dfs(List<List<Integer>>res,List<Integer>path,int[]nums,int index,boolean[]used){
        if(index==nums.length){
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i <nums.length ; i++) {
            if(!used[i]){

                //这一次搜索的起点和上一次搜索的起点一样,
                // 对于右子树来说，上一次就是左子树
                // 对于左子树来说 上一次就是父节点
                // 而且上一次搜索的相同的数刚刚被撤销；
                /*
                      1 1 2为例

                                 []
                            /     \       \
                           1      1（重复）  2
                          / \     / \      / \
                         1   2    1  2     1  1(重复)
                        /    \    /   \    /   \
                       2     1    2    1   1    1
                 */
                if(i!=0&&(nums[i]==nums[i-1])&&!used[i-1]){
                    continue;
                }

                used[i]=true;
                path.add(nums[i]);

                dfs(res,path,nums,index+1,used);
                used[i]=false;
                path.remove(path.size()-1);
            }
        }
    }
}

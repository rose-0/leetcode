package leecode.Array;

import java.util.ArrayList;
import java.util.List;

public class 子集_78_liweiwei {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path=new ArrayList<>();
        for (int i = 0; i <nums.length+1 ; i++) {
            //枚举深度
            backtrack(res,path,nums,i,0);
        }
        return res;
    }


    //liweiwei  直接dfs进行遍历画图 递归的过程记录深度
    // https://leetcode-cn.com/problems/subsets/solution/hui-su-python-dai-ma-by-liweiwei1419/
    public static void backtrack(List<List<Integer>>res, List<Integer>path, int[]nums, int depth, int begin){
        /*
        不传深度的话，直接
        res.add(new ArrayList<>(path)); 即可
         */
        if(depth==path.size()){
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = begin; i <nums.length ; i++) {
            path.add(nums[i]);
            //在 for 循环里应该使用变量，所以是 i ，如果写成 start ，start 在这个方法里是常量，使用 start 就不符合语义了。
            backtrack(res,path,nums,depth,i+1);
            path.remove(path.size()-1);
        }
    }
    //https://leetcode-cn.com/problems/subsets/solution/hui-su-python-dai-ma-by-liweiwei1419/
    //以 选 或者 不选 来画树形图，则最后的结果都是在叶子节点上
    public static void backtrack2(List<List<Integer>>res, List<Integer>path, int[]nums,  int index) {
        if(index==nums.length){//无数可选，到达页子节点
            res.add(new ArrayList<>(path));
            return;
        }

        //选择了当前的数
        path.add(nums[index]);
        //选择了数，进入下一层 递归就是进入下一层的过程
        backtrack2(res,path,nums,index+1);

        //撤销选择，重置状态，又进入 选和不选 的两种抉择中
        path.remove(path.size()-1);
        //不选，直接进入下一层
        backtrack2(res,path,nums,index+1);
    }

    //位运算技巧


}

package leecode.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 子集II_90 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>>res=new ArrayList<>();
        Arrays.sort(nums);//去重一定要先排序
        dfs(nums,res,0,new ArrayList<>());
        return res;
    }

    public void  dfs(int[] nums,List<List<Integer>>res,int begin,List<Integer>list){
        res.add(new ArrayList<>(list));
        for (int i = begin; i <nums.length ; i++) {
            if(i>0&&nums[i]==nums[i-1]&&i>begin){//和前面一样的去重判断 lee40
                continue;
            }
            list.add(nums[i]);
            dfs(nums,res,i+1,list);
            list.remove(list.size()-1);
        }
    }

}

package leecode.Array;

import java.util.ArrayList;
import java.util.List;

public class 组合总和III_216 {
    public List<List<Integer>> combinationSum3(int k, int n) {//和为n k个数
        int[]nums=new int[9];
        for (int i = 0; i <9 ; i++) {
            nums[i]=i+1;
        }
        List<List<Integer>> res=new ArrayList<>();
        dfs(res,new ArrayList<>(),nums,k,n,0);
        return res;
    }
    public void dfs(List<List<Integer>>res,List<Integer>list,int[]nums,int k,int n,int begin){
        if(list.size()==k){
            if(calSum(list)==n){
                res.add(new ArrayList<>(list));
                return;
            }else {
                return;
            }
        }
        if(calSum(list)>n){
            return;
        }
        for (int i = begin; i <nums.length ; i++) {
            list.add(nums[i]);
            dfs(res,list,nums,k,n,i+1);
            list.remove(list.size()-1);
        }
    }
    public int calSum(List<Integer>list){
        int sum=0;
        for(Integer i:list){
            sum=sum+i;
        }
        return sum;
    }
}

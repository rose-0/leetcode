package leecode.Array;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 组合总和II_40 {
    /*
    不能包含两个相同的解 但是一个解里面却可以包含重复的数字
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res=new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        boolean[]used=new boolean[candidates.length];
        //排序去重复
        Arrays.sort(candidates);
        dfs(res,list,candidates,0,target,used);
        return res;
    }

    //使用boolean数组
    public void dfs(List<List<Integer>> res,List<Integer> list,int[] candidates,int begin,int target,boolean[]used){
        if(target<0){
            return;
        }
        if(target==0){
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = begin; i <candidates.length ; i++) {
            //这样去重 一定要先排序数组！！

            /*
            解释!used[i-1] 在情况1 begin=1 处理右边2时候 左边2已经撤销 状态布尔值为false
                          在情况2 begin=2 处理下面的2时候 上面的2还没有被撤销 布尔值为true
             */
            if(i>0&&candidates[i]==candidates[i-1]&&!used[i-1]){
                continue;
            }
            list.add(candidates[i]);
            used[i]=true;
            //每个数只能选择一次时，下次搜索的起点就是i+1，对比 lee39
            dfs(res,list,candidates,i+1,target-candidates[i],used);
            list.remove(list.size()-1);
            used[i]=false;
        }
    }

    //不使用boolean数组
    public void dfs2(List<List<Integer>> res,List<Integer> list,int[] candidates,int begin,int target){
        if(target<0){
            return;
        }
        if(target==0){
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = begin; i <candidates.length ; i++) {
            //这样去重 一定要先排序数组！！
            /*
            情况1：
                      1
                 /        \
begin=1      2(i=1)   2(i=2)  这种情况不会发生 但是却允许了不同层级之间的重复即：
            /             \
begin=2  5(i=2)          5
            情况2：
                  1
                 /
                2      这种情况确是允许的
               /
              2
          candidates[i]==candidates[i-1] 这个会把情况1和2都排掉
          如何保留情况2，i>begin即可（i>begin排除的是情况1）
          例2的两个2是处在不同层级上的，在一个for循环中，所有被遍历到的数都是属于一个层级的
          对于重复的2，第一个2：i=begin，其他2：i>begin 所以 i>begin就可以只保留第一个

          如果if cur > 0 and candidates[cur-1] == candidates[cur] ，就会排除掉情况2，会导致全局只有一个相同数字可用，
          肯定不对，所以改成cur > begin，这样递归进入下一层时和上一层的重复结果没有任何关系了。

          同一层中的两个相同的值，i-1搜索的范围会更大，后面的搜索结果都是前面的真子集，所以只保留第一个就包含了全部可能的结果
          肯定会在下一层再一次搜索到i所组成组合，就重复了。


          for循环的最开始，cur=start，不会越界

             */
            if(i>begin&&candidates[i]==candidates[i-1]){//i>begin排除的是情况1
                continue;
            }
            list.add(candidates[i]);
            //每个数只能选择一次时，下次搜索的起点就是i+1，对比 lee39
            dfs2(res,list,candidates,i+1,target-candidates[i]);
            list.remove(list.size()-1);
        }
    }

}

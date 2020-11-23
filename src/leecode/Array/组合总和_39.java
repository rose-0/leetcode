package leecode.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 组合总和_39 {
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res=new ArrayList<>();
        List<Integer>path=new ArrayList<>();
        // 排序是剪枝的前提
        Arrays.sort(candidates);
        boolean[]used=new boolean[candidates.length];
        dfs2(res,path,used,candidates,target,0);
        return res;
    }
    //详解 liweiwei https://leetcode-cn.com/problems/combination-sum/solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-m-2/
    /*
    本题数字可以重复选择，注意重复选择如何绘制树形图

    由于每个元素可以重复使用，所以每个元素创建分支的时候，可以从这个元素开始进行选择，即还是可以选它
    如果不能重复，就不能再选择自己

    可以根据选或者不选来绘制树形图，参考官方的画图和解法
    还可以根据选择列表绘制树形图

    直接组合的形式绘制树形图：https://leetcode-cn.com/problems/combination-sum/solution/shou-hua-tu-jie-zu-he-zong-he-combination-sum-by-x/
    [2,3,6,7] 7

//                                                                  []
//                /                                                      /              \     \
//               [2]（分支从还是第一个数开始）             [3]分支从还是第2个数开始            [6]    [7]
//           /  ｜  |  \                      /                /      ｜       \                  \
//         2.2 2.3 1.4  2.3                 3.2(重复前面 2 3)  3.3    3.6       3.7


    区分 组合问题和排列问题
        排列是选哪一个的问题，但是不能选择已经选过的
        组合是对于一个数 选或者不选 两种选择
        排列问题讲究顺序 如上 2.3 和 3.2 是两个不同的选择，但是已经选过的不能再选，需要记录哪些数字已经使用过，此时用 used 数组；
        组合问题不讲究顺序 2.3和3.2是相同的选择，和都为5，产生重复的原因是每个节点展开分支时考虑了所有的候选数
                       解决重复：按照顺序搜索，每次搜索的时候设置下次搜索的起点begin
                       如果题目要求结果集不计算顺序，也要按照顺序搜索，如lee15 lee47

    区分 used标记数组和begin变量的使用
    什么时候 需要用index

    对于target的改变和二叉树路径和节点那个题目比较像，lee113 都是直接对target处理，而不是重新写一个变量记录和
    */

    //错误示范 这是排列的写法
    public static void dfs(List<List<Integer>>res,List<Integer>path,boolean[]used,int[]candidates,int target,int index){
        if(calSum(path)==target){
            res.add(new ArrayList<>(path));
            return;
        }
        if(target<calSum(path)){
            return;
        }
        if(index==candidates.length){
            return;
        }
        for (int i = 0; i <candidates.length ; i++) {
            if(!used[i]){
                if(i>0&&candidates[i]==candidates[i-1]&&!used[i-1]){
                    continue;
                }
                path.add(candidates[i]);
                used[i]=true;
                dfs(res,path,used,candidates,target,index+1);
                path.remove(path.size()-1);
                used[i]=false;
            }
        }
    }

    //target减法记录和
    public static void dfs2(List<List<Integer>>res,List<Integer>path,boolean[]used,int[]candidates,int target,int index) {
        // 由于进入更深层的时候，小于 0 的部分被剪枝，因此递归终止条件target<0 可以不写
        if(target<0){
            return;
        }
        if(target==0){
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i <candidates.length ; i++) {
            // 重点理解这里剪枝，前提是候选数组已经有序，在里面剪枝，在外面target<0 比如2 2 2 3在这里直接返回，而不写这个，要在外面返回
            if (target - candidates[i] < 0) {
                break;
            }

            path.add(candidates[i]);
            System.out.println("递归前 =》"+path+" ");

            // 注意：由于每一个元素可以重复使用，下一轮搜索的起点依然是 i，这里非常容易弄错
            // dfs2相当于更深一层，直到叶子节点 （可看打印结果）
            dfs2(res,path,used,candidates,target-candidates[i],i);

            //返回前去掉叶子节点，回到父节点，可以再走向另外一个子节点（可看打印结果）
            path.remove(path.size()-1);
            System.out.println("递归后 =》"+path+" ");
        }
    }

    //sum加法记录和
    public static void dfs3(List<List<Integer>>res,List<Integer>path,int[]candidates,int target,int index,int sum) {
        if(sum>target){
            return;
        }
        if(sum==target){
            res.add(new ArrayList<>(path));
        }
        for (int i = index; i <candidates.length ; i++) {
            path.add(candidates[i]);
            dfs3(res,path,candidates,target,sum+candidates[i],i);
            path.remove(path.size()-1);
        }
    }

    //计算每个树节点的和
    public void dfs12(int[] candidates,List<Integer>list,List<List<Integer>> res,int begin,int target){
        if(calSum(list)==target){
            res.add(new ArrayList<>(list));
            return;
        }
        if(calSum(list)>target){
            return;
        }
        if(begin==candidates.length){//没有这个条件也可以，应该是一定存在解 所以上面的条件把这个包含了
            return;
        }

        for (int i = begin; i <candidates.length ; i++) {
            list.add(candidates[i]);
            dfs12(candidates,list,res,i,target);
            list.remove(list.size()-1);
        }
    }

    //排序主要的作用的剪枝，让程序在深搜的过程中尽量排除掉不能搜索到结果的分支，以节约时间。


    public static int calSum(List<Integer> list) {
        if (list.size() == 0) {
            return 0;
        }
        int sum = 0;
        for (Integer i : list) {
            sum += i;
        }
        return sum;
    }

    public static void main(String[] args) {
        int[]candidates= new int[]{2,3,6,7};
        combinationSum(candidates,7);
    }
}

package leecode.Array;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
//回溯算法详解：https://leetcode-cn.com/problems/permutations/solution/hui-su-suan-fa-xiang-jie-by-labuladong-2/
/*
框架：
result = [] //全局记录结果
def backtrack(路径, 选择列表):
    if 满足结束条件:
        result.add(路径)
        return

    for 选择 in 选择列表:
        做选择
        backtrack(路径, 选择列表)
        撤销选择
现在可以解答开头的几个名词：[2] 就是「路径」，路径就是结果
记录你已经做过的选择；[1,3] 就是「选择列表」，表示你当前可以做出的选择；
「结束条件」就是遍历到树的底层，在这里就是选择列表为空的时候。
 backtrack 函数其实就像一个指针，在这棵树上游走，
 同时要正确维护每个节点的属性，每当走到树的底层，其「路径」就是一个全排列。
 对比先序和后序遍历，（把路径和选择列表看作是每个节点的属性）
 我们只要在递归之前做出选择，在递归之后撤销刚才的选择，就能正确得到每个节点的选择列表和路径。
    for 选择 in 选择列表:
    # 做选择
    将该选择从选择列表移除
    路径.add(选择)
    backtrack(路径, 选择列表)
    # 撤销选择
    路径.remove(选择)
    将该选择再加入选择列表

 */



public class 子集_78 {
    //
    public static List<List<Integer>> subsets(int[]nums){
        List<List<Integer>>listall=new ArrayList<>();
        LinkedList<Integer>temp=new LinkedList<>();
//        dfss(nums,0,listall,temp);
//        preOrder(nums,0,temp,listall);
//        huisuwithnode(nums,0,temp,listall);
//        dfs(nums,0,new LinkedList<>(),listall);
        dfsone(nums,0,temp,listall);
        for (List list:listall) {
            System.out.println(list);
        }
        return listall;
    }
    public static void dfss(int[]nums,int index,List<List<Integer>>listall,List<Integer>temp){
        if(index==nums.length){return;}
        temp=new ArrayList<>(temp);
        listall.add(temp);
        dfss(nums,index+1,listall,temp);
        temp.add(nums[index]);
        dfss(nums,index+1,listall,temp);
    }
    //dfs前序遍历  //这个不好理解，现在不要看了
//https://leetcode-cn.com/problems/subsets/solution/er-jin-zhi-wei-zhu-ge-mei-ju-dfssan-chong-si-lu-9c/
//集合中每个元素的选和不选，构成了一个满二叉状态树，
// 比如，左子树是不选，右子树是选，从根节点、到叶子节点的所有路径，构成了所有子集。
// 可以有前序、中序、后序的不同写法，结果的顺序不一样。本质上，其实是比较完整的中序遍历。


    public static void preOrder(int[] nums, int i, List<Integer> subset, List<List<Integer>> res) {
        if (i >= nums.length) return;
        // 到了新的状态，记录新的路径，要重新拷贝一份

      subset = new ArrayList<Integer>(subset);//其实自始至终改变的都是同一个subset，使用new是对半路状态的截断

        // 这里是前序对节点进行处理
        res.add(subset);
        preOrder(nums, i + 1, subset, res);//从i+1开始所有的子集
        subset.add(nums[i]);
        preOrder(nums, i + 1, subset, res);
    }
    //-------------------------------------------------------------------------------
    /*
    dfsone方法的模型：
    https://leetcode-cn.com/problems/subsets/solution/hui-su-python-dai-ma-by-liweiwei1419/
                  []
     选1        /    \
      index=0  []    [1]
     选2       /\     /\
           1 []  2   1  1,2
     选3     /\
          2
     */
    private static void dfsone(int[] nums, int index,
                     List<Integer> list, List<List<Integer>> res) {//要用一个可以按顺序插入的list，直接用list也可以
        if (index == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        // 当前数可选，也可以不选

        // 不选，直接进入下一层
        dfsone(nums, index + 1, list, res);

        // 选了有，进入下一层
        list.add(nums[index]);
        dfsone(nums, index + 1, list, res);
        list.remove(list.size()-1);//这儿是移除最后一个元素
    }

//--------------------------------------------------------------------------------------------
//回溯法，回溯的过程中记录节点https://leetcode-cn.com/problems/subsets/solution/hui-su-python-dai-ma-by-liweiwei1419/
//回溯算法的解空间：解空间当做图，则这个图的结点构成依据是解里面元素的个数，即空集邻接了{1},{2},{3}，而集合
//{1}邻接了{1,2},{1,3},集合{2}邻接{2,3}，集合{1,2}邻接了{1,2,3}
//链接：https://leetcode-cn.com/problems/subsets/solution/dui-liang-chong-di-gui-qiu-jie-suan-fa-de-shi-jian/
/*
                              []
                        /      |    \
                      [1]     [2]    [3]
                     /   \       \
                   [1,2] [1,3]   [2,3]
                    /
                 [1,2,3]

 回溯模板https://leetcode-cn.com/problems/subsets/solution/hui-su-si-xiang-tuan-mie-pai-lie-zu-he-zi-ji-wen-t/
 */


    private static void huisuwithnode(int[]nums,int begin,LinkedList<Integer>pre,List<List<Integer>>res){
        // 没有显式的递归终止,因为循环完了就结束
        res.add(new ArrayList<>(pre));// 注意：Java 的引用传递机制，这里要 new 一下
        for (int i = begin; i <nums.length ; i++) {//从begin开始就排除选择了
            pre.add(nums[i]);
            huisuwithnode(nums,i+1,pre,res);//这里是i+1，不是begin+1,
            // i不断在循环中增加，如果写成begin，则会重复回溯（例如下次循环i为begin+1，但还是从begin回溯）
            pre.remove(pre.size()-1);// 组合问题，状态在递归完成后要重置
        }
    }
//-------------------------------------------------------------
//回溯法，回溯的过程中记录深度
    private static List<List<Integer>> subsetsfordepth(int[]nums){//这个方法不怎么好理解
        List<List<Integer>>listall=new ArrayList<>();
        LinkedList<Integer>temp=new LinkedList<>();
        for (int i = 0; i <nums.length+1 ; i++) {//长度为0，1，2的数组集合
            huisuwithdepth(nums,0,i,temp,listall);
        }
        for (List list:listall) {
            System.out.println(list);
        }
        return listall;
    }
    private static void huisuwithdepth(int[]nums,int start,int depth,List<Integer>pre,List<List<Integer>>res){
        if(depth==pre.size()){//depth是预先设定的深度，也就是最后返回的数组长度
            res.add(new ArrayList<>(pre));
            return;
        }
        for (int i = start; i <nums.length ; i++) {
            pre.add(nums[i]);
            huisuwithdepth(nums,i+1,depth,pre,res);
            pre.remove(pre.size()-1);
        }
    }
    //-------------------------------------------------------------------------

    public static void main(String[] args) {
        int[] nums={1,2};
//        subsets(nums);
        subsetsfordepth(nums);
//        String str="abc";
//        List<String>strings=new ArrayList<>();
//        print_sub(0,"",str.toCharArray(),strings);
//        System.out.println(strings);
    }

    public static void subString(String str,int index,StringBuilder temp){
        if(index==str.length()){
            System.out.println(temp);
            temp=new StringBuilder();
            return;
        }
        subString(str,index+1,temp);
        subString(str,index+1,temp.append(str.charAt(index)));
    }
    public static void print_sub(int index,String sub,char[] res,List<String>strings){
        if(index==res.length){
            sub=new String(sub);
            strings.add(sub);
//            System.out.println(sub);
            return;//return一定要有
        }
//        sub=new String(sub);
//        strings.add(sub);
        print_sub(index+1,sub,res,strings);
//        System.out.println(sub);  在最后才进行输出，而不是中间输出
        print_sub(index+1,sub+res[index],res,strings);
//        System.out.println(sub);
    }
}

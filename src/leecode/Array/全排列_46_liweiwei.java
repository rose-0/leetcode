package leecode.Array;

import java.util.ArrayList;
import java.util.List;

public class 全排列_46_liweiwei {
    /*
    笔记：

    如果解决一个问题有多个步骤，每一个步骤有多种方法，题目又要我们找出所有的方法，可以使用回溯算法；

    回溯算法强调了深度优先遍历思想的用途
    深度优先遍历强调的是一种遍历的思想，相应的，还有bfs

    搜索引擎的 搜索 和 回溯搜索 算法里 搜索 的意思是一样的，搜索问题的解，可以通过 遍历 实现，
    回溯算法用于 搜索一个问题的所有的解 ，通过深度优先遍历的思想实现。

    和动态规划的区别：动态规划只需要求我们评估最优解是多少，最优解对应的具体解是什么并不要求。
                   回溯算法可以搜索得到所有的方案

    回溯算法的说明：
    1、每一个结点表示了求解全排列问题的不同的阶段，这些阶段通过一个变量的「不同的值」体现，这些变量的不同的值，称之为「状态」；
    2、撤销选择的过程称为状态重置
    3、深度优先遍历，借助系统栈空间，往下走一层的时候，path 变量在尾部追加，而往回走的时候，需要撤销上一次的选择，也是在尾部操作，
       因此 path 变量是一个栈；
    4、通过「回溯」操作，实现了全局使用一份状态变量的效果。

    设计状态变量：
    1、显然是个递归结构，每个节点：在已经选择了一些数的前提下，在剩下的还没有选择的数中，依次选择一个数。
    2、需要一个变量来表示当前程序递归到第几层，我们把这个变量叫做 depth，或者命名为 index ，
       表示当前要确定的是某个全排列中下标为 index 的那个数是多少；
    3、布尔数组 used，表示数字有没有被选择

    这些变量称为「状态变量」(可以多个)，它们表示了在求解一个问题的时候所处的阶段。

    理解回溯：
    从较深层的结点返回到较浅层结点的时候，需要做「状态重置」,见下面代码

    为什么不是广度优先遍历
    如果使用广度优先遍历，从浅层转到深层，状态的变化就很大，存储的信息多

    做题步骤：
    1、建议 先画树形图 ，画图能帮助我们想清楚递归结构，想清楚如何剪枝。
    2、分支如何产生；
    3、题目需要的解在哪里？是在叶子结点、还是在非叶子结点、还是在从跟结点到叶子结点的路径？
    4、哪些搜索会产生不需要的解的？例如：产生重复是什么原因，
      如果在浅层就知道这个分支不能产生需要的结果，应该提前剪枝，
      剪枝的条件是什么，代码怎么写？

    三种对比：
                递归终止条件                                      状态变量
    全排列   深度(注：是深度而不是index)，                        used[]数组
            因为全排列的解在树的最后一层节点(且都是同一层)！

    组合    list.size=组合数(不是begin=len                     begin变量
            因为从树可以看到 begin=len不是解)

    子集     直接add 或者 枚举深度,再利用深度(因为各个深度都是解)       begin变量

    //组合问题注意可选的数字是否可以重复 如lee39
     */
    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        // 使用一个动态数组保存所有可能的全排列
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        boolean[] used = new boolean[len];
        List<Integer> path = new ArrayList<>();

        dfs(nums, len, 0, path, used, res);
        return res;
    }

    private void dfs(int[] nums, int len, int depth,
                     List<Integer> path, boolean[] used,
                     List<List<Integer>> res) {
        if (depth == len) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 在非叶子结点处，产生不同的分支，这一操作的语义是：在还未选择的数中依次选择一个元素作为下一个位置的元素，这显然得通过一个循环实现。
        for (int i = 0; i < len; i++) {
            if (!used[i]) {
                path.add(nums[i]);
                used[i] = true;
                //可以打印出来看看
                System.out.println("  递归之前 => " + path);

                dfs(nums, len, depth + 1, path, used, res);
                // 注意：下面这两行代码发生 「回溯」，即状态变量重置
                // 回溯发生在从 深层结点 回到 浅层结点 的过程，代码在形式上和递归之前是对称的
                used[i] = false;
                path.remove(path.size() - 1);

                //可以打印出来看看
                System.out.println("递归之后 => " + path);
            }
        }
    }

}

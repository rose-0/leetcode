package leecode.Array;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

//https://leetcode-cn.com/problems/subsets/solution/hui-su-si-xiang-tuan-mie-pai-lie-zu-he-zi-ji-wen-t/
//78回溯框架
public class 组合_77 {
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>>listall=new ArrayList<>();
        List<Integer>list=new ArrayList<>();
        backtrack2(n,k,1,listall,list);
        for (List<Integer> list1:listall){
            System.out.println(list1);
        }
        return listall;
    }
    /*
    这个数结构和求子集很像！！！
    典型的回溯算法，k(2) 限制了树的高度，n(4) 限制了树的宽度，直接套我们以前讲过的回溯算法模板框架就行了：
                                              []
                                     /      /      \     \
                                    [1]    [2]      [3]    [4]
                                /  |  \     / \       \
                              1.2 1.3 1.4  2.3 2.4    3.4
     */
    public static void backtrack(int n,int k,int index,List<List<Integer>>listall,List<Integer>list){
        if(list.size()==k){//注意这个条件，list中有k个数时就输出
            listall.add(new ArrayList<>(list));
            return;//这个不写return也可以，建议写上
        }
        for (int i = index; i <n ; i++) {
            list.add(i);
            backtrack(n,k,i+1,listall,list);
            list.remove(list.size()-1);
        }
    }

    //liweiwei list相当于一个栈，记录了树上每个节点与根路径上的所有的树，递归的过程记录节点
    public static void backtrack2(int n,int k,int begin,List<List<Integer>>listall,List<Integer>list){
        //不能使用 begin==k+1,作为递归终止的条件,如下，begin是每层遍历的开始
        /*
                                                        []
                                     /               /                  \     \
                                   [1](begin=0)    [2](begin=1)     [3](begin=2)    [4](begin=3)
                                /  |  \                  / \               \
                              1.2 1.3 1.4            2.3 2.4             3.4
         */
        if(list.size()==k){
            listall.add(new ArrayList<>(list));
            return;//这个不写return也可以，建议写上
        }
        /*
        剪枝： 只有这里 i <= n - (k - path.size()) + 1   不再是 i<=n
        剪枝条件的查找 还需要 看liweiwei的题解
         */
        for (int i = begin; i <=n ; i++) {
            list.add(i);
            System.out.println("递归之前 =》" + list);
            backtrack2(n,k,i+1,listall,list);
            list.remove(list.size()-1);
            System.out.println("递归之后 =》"+ list);
        }
    }


    /*
    根据选 和不选 来解决这个题目
    https://leetcode-cn.com/problems/combinations/solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-ma-/
     */

    private void dfs(int begin, int n, int k, Deque<Integer> path, List<List<Integer>> res) {
        if (k == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 基础版本的递归终止条件：if (begin == n + 1) {
        if (begin > n - k + 1) {
            return;
        }
        // 不选当前考虑的数 begin，直接递归到下一层
        dfs(begin + 1, n, k, path, res);

        // 不选当前考虑的数 begin，递归到下一层的时候 k - 1，这里 k 表示还需要选多少个数
        path.addLast(begin);
        dfs(begin + 1, n, k - 1, path, res);
        // 深度优先遍历有回头的过程，因此需要撤销选择
        path.removeLast();
    }




    public static void main(String[] args) {
        combine(5,2);
    }
}

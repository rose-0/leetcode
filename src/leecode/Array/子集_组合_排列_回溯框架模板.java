package leecode.Array;

import java.util.ArrayList;
import java.util.List;

public class 子集_组合_排列_回溯框架模板 {
    //result = []
    //def backtrack(路径, 选择列表):
    //    if 满足结束条件:
    //        result.add(路径)
    //        return
    //    for 选择 in 选择列表:
    //        做选择
    //        backtrack(路径, 选择列表)
    //        撤销选择
    List<List<Integer>> listall=new ArrayList<>();
    List<Integer>track=new ArrayList<>();//track就是路径

    //子集
//                                                   []
//                                             /      |    \
//                                           [1]     [2]    [3]
//                                          /   \       \
//                                       [1,2] [1,3]   [2,3]
//                                          /
//                                      [1,2,3]

    public void subsets(int[]nums,int index,List<Integer>track){
        listall.add(new ArrayList<>(track));//树上每个节点都要加上
        //没有return
        for (int i = index; i <nums.length ; i++) {//从index开始，如果是1，选择列表2，3；如果是2选择列表只有3
            track.add(nums[i]);//做选择
            //回溯函数相当于一个指针，i+1 走到下一层 ，每层track里面的个数不一样
            subsets(nums,i+1,track);//i不断在循环中增加，如果写成index，则会重复回溯（例如下次循环i为index+1，但还是从index回溯）
            track.remove(track.size()-1);//撤销
        }
    }

    //组合
//    典型的回溯算法，k(2) 限制了树的高度，n(4) 限制了树的宽度，直接套我们以前讲过的回溯算法模板框架就行了：
//                          []
//            /            /      \     \
//            [1]        [2]      [3]    [4]
//           /  |  \     / \       \
//         1.2 1.3 1.4  2.3 2.4    3.4
    public  void backtrack(int n,int k,int index,List<Integer>list){
        if(list.size()==k){//注意这个条件，list中有k个数时就输出，子集几个数都可以，所以不写条件
            listall.add(new ArrayList<>(list));
            return;//这个不写return也可以，建议写上
        }
        for (int i = index; i <n ; i++) {
            list.add(i);
            backtrack(n,k,i+1,list);
            list.remove(list.size()-1);
        }
    }

    //排列
//                                 []
//
//                      1          2         3
//
//              1,2     1,3      2,1    2,3    3,1     3,2
//
//             1,2,3   1,3,2    2,1,3   2,3,1  3,1,2  3,2,1
    //排列问题的树比较对称，而组合问题的树越靠右节点越少。
    // 在代码中的体现就是，
    // 排列问题每次通过 contains 方法来排除在 track 中已经选择过的数字；
    // 而组合问题通过传入一个 start 参数，
    // 来排除 start 索引之前的数字。
    public  void huisu(List<Integer>trace,int[]nums,int index) {//全排列下标没有用
        if (trace.size() == nums.length) {//满足三个数了
            listall.add(new ArrayList<>(trace));
            return;//打印一个字符串的全部排列不需要写return
        }
        for (int i = 0; i < nums.length; i++) {//从0开始，因为全排列所有的数都要包含，不是说你到了3就不能选1了，每步都有三个选择
            // 但是访问过就不能访问了，子集是从begin开始，所以排除了访问过的
            if (trace.contains(nums[i])) {//排除不合法的选择,也可以使用visit排除，全排列_46
                continue;
            }
            //做选择
            trace.add(nums[i]);//路径.add(选择)
            huisu(trace, nums, index);//backtrack(路径, 选择列表) index没有用
            trace.remove(trace.size() - 1);
        }
    }
}

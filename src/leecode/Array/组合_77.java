package leecode.Array;

import java.util.ArrayList;
import java.util.List;

//https://leetcode-cn.com/problems/subsets/solution/hui-su-si-xiang-tuan-mie-pai-lie-zu-he-zi-ji-wen-t/
//78回溯框架
public class 组合_77 {
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>>listall=new ArrayList<>();
        List<Integer>list=new ArrayList<>();
        backtrack(n,k,0,listall,list);
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

    public static void main(String[] args) {
        combine(5,2);
    }
}

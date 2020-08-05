package leecode.Array;

import java.util.ArrayList;
import java.util.List;

public class 全排列_46 {
    //看78对回溯算法的讲解来进行写代码，n皇后问题也可以套用回溯框架

    /*
 但是根据排列问题和组合问题画出的树来看，排列问题的树比较对称，而组合问题的树越靠右节点越少。
在代码中的体现就是，排列问题每次通过visit数组方法来排除在 track 中已经选择过的数字；
而组合问题通过传入一个 start 参数，来排除 start 索引之前的数字。
                                  []

                          1          2         3

                      1,2  1,3      2,1  2,3    3,1  3,2

                   1,2,3   1,3,2    2,1,3  2,3,1  3,1,2  3,2,1
     */
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>>listall=new ArrayList<>();
        List<Integer>pre=new ArrayList<>();
        boolean[]visit=new boolean[nums.length];//标记是否访问
        huisu(listall,pre,nums,0,visit);
        for (List list:listall) {
            System.out.println(list);
        }
        return listall;
    }
    public static void huisu(List<List<Integer>>listall,List<Integer>pre,int[]nums,int index,boolean[]visit){
        if(pre.size()==nums.length){
            listall.add(new ArrayList<>(pre));
            return;//打印一个字符串的全部排列不需要写return
        }
        for (int i = 0; i <nums.length ; i++) {//从0开始，因为全排列所有的数都要包含，
            // 但是访问过就不能访问了，子集是从begin开始，所以排除了访问过的
            if(visit[i]==true){//排除不合法的选择
                continue;
            }
            //做选择
            visit[i]=true;//将选择从选择列表中移除
            pre.add(nums[i]);//路径.add(选择)
            huisu(listall,pre,nums,index,visit);//backtrack(路径, 选择列表) index没有用
            pre.remove(pre.size()-1);
            visit[i]=false;
        }
    }

    public static void main(String[] args) {
        int[]num={1,2,3};
        permute(num);
    }
}

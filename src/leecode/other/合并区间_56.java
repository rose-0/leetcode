package leecode.other;

import java.util.*;

//https://leetcode-cn.com/problems/merge-intervals/solution/pai-xu-by-powcai/
//解析https://github.com/labuladong/fucking-algorithm/blob/master/%E7%AE%97%E6%B3%95%E6%80%9D%E7%BB%B4%E7%B3%BB%E5%88%97/%E5%8C%BA%E9%97%B4%E8%B0%83%E5%BA%A6%E9%97%AE%E9%A2%98%E4%B9%8B%E5%8C%BA%E9%97%B4%E5%90%88%E5%B9%B6.md
public class 合并区间_56 {
    public int[][] merge(int[][] intervals) {
        if(intervals==null||intervals.length<=1){
            return intervals;
        }
        List<int[]>list=new LinkedList<>();
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        int i=0;
        int n=intervals.length;
        while (i<n){
            int left=intervals[i][0];
            int right=intervals[i][1];
            //确定左区间，不断的更新右区间
            while (i<n-1&&right>=intervals[i+1][0]){//right>=下一个区间的左边界
                right=Math.max(right,intervals[i+1][1]);//right= 两者中最大右边界
                i++;
            }
            //退出循环时候 right<下一个区间的左边界， 不能再合并 将此区间 left，right 加到结果集中
            list.add(new int[]{left,right});
            i++;
        }
        return list.toArray(new int[list.size()][2]);
    }

    //labuladong 感觉上面的好理解一些
    public int[][] mergetwo(int[][] intervals) {//待验证
        if(intervals==null||intervals.length<=1){
            return intervals;
        }
        List<int[]>list=new LinkedList<>();
        //按照区间的start升序排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        list.add(intervals[0]);//list存放合并后的区间

        for (int i = 0; i <intervals.length ; i++) {
            int[]cur=intervals[i];
            //最后一个元素
            int[]last=list.get(list.size()-1);

            if(cur[0]<last[1]){//说明有交集
                last[1]=Math.max(last[1],cur[1]);//list中区间的end，即把两个区间合并了
            }else {
                //处理下一个区间
                list.add(cur);
            }
        }
        return list.toArray(new int[list.size()][2]);
    }
}

package leecode.bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class 打开转盘锁_752 {
    /*
    每次旋转一次 8种情况 8个分支
    https://leetcode-cn.com/problems/open-the-lock/solution/wo-xie-liao-yi-tao-bfs-suan-fa-kuang-jia-jian-dao-/
    优化：双向bfs
     */
    public int openLock(String[] deadends, String target) {
        Set<String>deadSet=new HashSet<>();
        for(String s:deadends){
            deadSet.add(s);
        }
        Queue<String>queue=new LinkedList<>();
        queue.add("0000");
        /*
        visit的作用 防止产生死循环 因为 0000 -> 1000 1000还可以变成0000
         */
        Set<String>visit=new HashSet<>();
        int step=0;
        while (!queue.isEmpty()){
            int size=queue.size();
            for (int i = 0; i <size ; i++) {
                String cur=queue.poll();
                if(cur.equals(target)){
                    return step;
                }
                if(deadSet.contains(cur)){//根据题目给的限制条件进行剪枝
                    continue;
                }
                for (int j = 0; j <4 ; j++) {//对四位 进行向下 向上转动
                    String up=up(cur,j);
                    String down=down(cur,j);
                    if(!visit.contains(up)){//入队前判断
                        queue.offer(up);
                        visit.add(up);
                    }
                    if(!visit.contains(down)){
                        queue.offer(down);
                        visit.add(down);
                    }

                }
            }
            step++;
        }
        return -1;
    }
    public String up(String s,int index){
        char[]chars=s.toCharArray();
        if(chars[index]=='9'){
            chars[index]='0';
        }else {
            chars[index]++;
        }
        return String.valueOf(chars);
    }
    public String down(String s,int index){
        char[]chars=s.toCharArray();
        if(chars[index]=='0'){
            chars[index]='9';
        }else {
            chars[index]--;
        }
        return String.valueOf(chars);
    }
}

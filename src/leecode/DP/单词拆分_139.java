package leecode.DP;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
//01 背包问题
public class 单词拆分_139 {
    public boolean wordBreak(String s, List<String> wordDict) {
        return word_huisu(0,s,new HashSet(wordDict));
    }
    public static boolean  word_huisu(int start, String s, Set<String>wordDict){
        if(start==s.length()){
            return true;
        }
        for (int end = start; end <=s.length() ; end++) {//注意substring(int beginIndex, int endIndex)
            //子字符串从指定的 beginIndex 处开始， endIndex:到指定的 endIndex-1处结束。所以end是<=
            String res=s.substring(start,end);//start到end-1的单词是否在set中，如果在，则看从end开始的是否在
            if(wordDict.contains(res)&&word_huisu(end,s,wordDict)){
                return true;
            }
        }
        return false;
    }

    /*
    使用备忘录解法
     */
    public static boolean wordBreak2(String s, List<String> wordDict) {
        int[]visit=new int[s.length()];
        boolean flag=word_memo(0,s,new HashSet(wordDict),visit);
        for (int i = 0; i <visit.length ; i++) {
            System.out.println("i="+i+"visit="+visit[i]);
        }
        return flag;
    }

    /*
    1 true
    2 false
     */

    public static boolean word_memo(int start,String s,Set<String>set,int[]visit){
        if(start==s.length()){//指针越界是ture！！
            return true;
        }
        if(visit[start]==1){
            return true;
        }
        if(visit[start]==2){
            return false;
        }
        for (int end = start; end <=s.length() ; end++) {
            String res=s.substring(start,end);
            if(set.contains(res)&&word_memo(end,s,set,visit)){
                visit[start]=1;
                return true;

            }
        }
        visit[start]=2;
        return false;
    }
    /*
    https://leetcode-cn.com/problems/word-break/solution/shou-hui-tu-jie-san-chong-fang-fa-dfs-bfs-dong-tai/

    dp [i]长度为i的s[0，i-1]是否可以由单词表组成
    dp[0] = true。长度为 0 的子串是由单词表的单词组成的。是很诡异。
    但这纯粹是为了：让边界情况也能满足状态转移方程，
    即上图：当黄色部分为空字符串时，dp[i+1]全然取决于 [0,i]子串是否是单词表的单词
     */
    public boolean word_dp(String s, List<String> wordDict){
        Set<String>set=new HashSet<>(wordDict);
        boolean[]dp=new boolean[s.length()+1];
        dp[0]=true;
        for (int i = 0; i <=dp.length ; i++) {
            //j划分 0..i的字符串！！
            for (int j = 0; j <i ; j++) {//或者for(int j=i;j>=0;j--) j和i重合，向左
                //dp[i] 表示 s 中以 i - 1 结尾的字符串是否可被 wordDict 拆分
                //dp多一个！
                if(dp[j]&&set.contains(s.substring(j,i))){
                    dp[i]=true;
                    break;//注意这个break
                }
            }
        }
        return dp[dp.length-1];
    }

    public static void main(String[] args) {
        String s="leetcode";
        List<String>word=new ArrayList<>();
        word.add("leet");
        word.add("code");
        System.out.println(word_huisu(0,s,new HashSet<>(word)));
        System.out.println(wordBreak2(s,word));
    }
}

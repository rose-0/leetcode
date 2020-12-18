package leecode.other;


import java.util.*;
import java.util.Deque;

public class 分割回文串_131 {
    /*
    https://leetcode-cn.com/problems/palindrome-partitioning/solution/hui-su-you-hua-jia-liao-dong-tai-gui-hua-by-liweiw/
    优化：dp
     */
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        List<String> path = new ArrayList<>();
        dfs(s,res,path,0);
        return res;
    }
    public void dfs(String s, List<List<String>> res, List<String> path, int begin){
        if(begin==s.length()){
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = begin; i <s.length() ; i++) {
            if(!ishuiwen(s,begin,i)){
                continue;
            }
            path.add(s.substring(begin,i+1));
            dfs(s,res,path,i+1);
            path.remove(path.size()-1);
        }
    }
    public boolean ishuiwen(String s,int begin,int end){
        while (begin<end){
            if(s.charAt(begin)!=s.charAt(end)){
                return false;
            }
            begin++;
            end--;
        }
        return true;
    }
}

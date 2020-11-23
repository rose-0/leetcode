package leecode.dfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class 相似字符串组_839 {
    public int numSimilarGroups(String[] strs) {
        //题目求可以构成几组相似字符串组 即如果字符串之间相似则归为一组 看看可以分几组
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < strs.length; i++) {
            res.add(new ArrayList<>());
        }
        for (int i = 0; i < strs.length; i++) {
            for (int j = i + 1; j < strs.length; j++) {
                if (isSam(strs[i], strs[j])) {
                    //这里一定要建立双向，如果只写一个 则错误
                    /*
                    例如 1 2 3
                    1和3相似 2和3相似
                    如果只写一个 则dfs时将 123视为两个： 13  和 23
                    写2个 则构造时就使得 3的邻边有 1 2
                     */
                    res.get(i).add(j);
                    res.get(j).add(i);
                }
            }
        }
        int count = 0;
        boolean[] visit = new boolean[strs.length];
        for (int i = 0; i < strs.length; i++) {
            if (!visit[i]) {
                count++;
                dfs(res, i, visit);
            }
        }
        return count;
    }

    public void dfs(List<List<Integer>> res, int cur, boolean[] visit) {
        if (visit[cur]) {
            return;
        }
        visit[cur] = true;
        for (int i : res.get(cur)) {
            dfs(res, i, visit);
        }
    }

    public boolean isSam(String s1, String s2) {
        int difCount = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                difCount++;
            }
        }
        if (difCount == 2 || difCount == 0) {
            return true;
        }
        return false;
    }

    //使用并查集 看...的评论答案
    private int[]parent;
    public int numSimilarGroups2(String[] strs) {
        int n=strs.length;
        parent=new int[n];
        for (int i = 0; i <n ; i++) {
            parent[i]=i;
        }
        for (int i = 0; i <n ; i++) {
            for (int j = i+1; j <n ; j++) {
                if(isSam(strs[i],strs[j])){
                    union(i,j);
                }
            }
        }
        int res=0;
        for (int i = 0; i <n; i++) {
            if(parent[i]==i){
                res++;
            }
        }
        return res;
    }
    private int findParent(int node){
        /*
        Stack<Integer>stack=new Stack<>();
        int father=parent[node];
        while (father!=node){
            stack.push(node);
            node=father;
            father=parent[node];
        }
        while (!stack.isEmpty()){
            parent[stack.pop()]=father;
        }
         */
        while (parent[node]!=node){
            parent[node]=parent[parent[node]];//路径压缩
            node=parent[node];
        }
        return node;
    }
    private void union(int node1,int node2){
        int root1=findParent(node1);
        int root2=findParent(node2);
        if(root1==root2){
            return;
        }
        parent[root1]=root2;
    }
}

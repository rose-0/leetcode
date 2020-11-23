package leecode.dfs;

import java.util.*;

public class 交换字符串中的元素_1202 {
    /*
    //视频讲解 https://zxi.mytechroad.com/blog/graph/leetcode-1202-smallest-string-with-swaps/
    可以交换就构成连通分量，对构成连通分量的字母进行排序，再插入到对应的位置
     */
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        List<List<Integer>> neigh = new ArrayList<>();
        for (int i = 0; i <s.length() ; i++) {
            neigh.add(new ArrayList<>());
        }
        for(List<Integer>pair:pairs){
            neigh.get(pair.get(0)).add(pair.get(1));
            neigh.get(pair.get(1)).add(pair.get(0));
        }
        char[]res=s.toCharArray();
        boolean[]visit=new boolean[s.length()];
        for (int i = 0; i <s.length() ; i++) {
            if(visit[i]){
                continue;
            }
            List<Integer>index=new ArrayList<>();
            StringBuilder str=new StringBuilder();
            dfs(neigh,i,visit,s,str,index);
            char[]chars=str.toString().toCharArray();
            Arrays.sort(chars);//字符进行自然排序
            Integer []indexs=index.toArray(new Integer[index.size()]);
            Arrays.sort(indexs);//对应下标进行排序 这样下面就按照从小到大 插入到原来的位置
            for (int j = 0; j <indexs.length ; j++) {
                res[indexs[j]] =chars[j];
            }
        }
        return new String(res);
    }
    public void dfs(List<List<Integer>> neigh,int cur,boolean[]visit,String s,StringBuilder str,List<Integer>index){
        if(visit[cur]){
            return;
        }
        visit[cur]=true;
        str.append(s.charAt(cur));
        index.add(cur);
        for(Integer i:neigh.get(cur)){
            dfs(neigh,i,visit,s,str,index);
        }
    }
    /*
    class Solution {
public:
  string smallestStringWithSwaps(string s, vector<vector<int>>& pairs) {
    vector<vector<int>> g(s.length());
    for (const auto& e : pairs) {//以临接链表的形式建立图 因为是无向的 所以 从a到b 和 b到a 都进行
      g[e[0]].push_back(e[1]);
      g[e[1]].push_back(e[0]);
    }

    unordered_set<int> seen;//表示节点是否访问
    vector<int> idx;
    string tmp;

    //dfs函数 传入当前节点
    function<void(int)> dfs = [&](int cur) {
      if (seen.count(cur)) return;//当前节点访问过 退出
      seen.insert(cur);//加入到已经访问的节点
      idx.push_back(cur);//当前节点放入它所在的联通分量索引的数组中
      tmp += s[cur];//收集联通分量的字符
      for (int nxt : g[cur]) dfs(nxt);
    };

    for (int i = 0; i < s.length(); ++i) {
      if (seen.count(i)) continue;//已经访问过 退出
      idx.clear();//联通分量所有顶点的id
      tmp.clear();//联通分量所有字符
      dfs(i);//找到i所在的全部的联通分量，填充到 idx tmp
      sort(begin(tmp), end(tmp));
      sort(begin(idx), end(idx));
      for (int k = 0; k < idx.size(); ++k)//把该联通分量中的元素放入相应的位置里面
        s[idx[k]] = tmp[k];
    }
    return s;
  }
};
     */
}

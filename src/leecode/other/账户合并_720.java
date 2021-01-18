package leecode.other;

import java.util.*;

public class 账户合并_720 {
    
    //题解 https://leetcode-cn.com/problems/accounts-merge/solution/java-hao-shi-90nei-cun-50bing-cha-ji-fang-fa-by-wm/
    // 举例 https://leetcode-cn.com/problems/accounts-merge/solution/bing-cha-ji-by-jason-2/
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        findUion findUion = new findUion(accounts.size());
        
        //构造一个Map，保存邮箱到人（不能是人名，要是accounts的下标，因为人名会重复）
        //emailIndexMap是辅助建立并查集，将存在相同邮箱的下标 关联起来
        //相同的邮箱 可以认为是联通的 是同一个联通分量
        Map<String,Integer> emailIndexMap = new HashMap<>();
        for (int i = 0; i <accounts.size() ; i++) {
            List<String> account = accounts.get(i);
            for (int j = 1; j <account.size() ; j++) {
                String email = account.get(j);
                //放进去之前先检查一下，如果之前出现过这个邮箱，使用并查集把这俩下标合并。
                if(emailIndexMap.containsKey(email)){
                    int existIndex = emailIndexMap.get(email);
                    findUion.combine(existIndex,i);//是i parent[i]=existIndex;
                }
                emailIndexMap.put(email,i);
            }
        }
        //再建立一个Map，这个Map是下标和邮箱集的映射，
        //这个时候已经邮箱去重，邮箱排序，名单去重了。前两者采用TreeSet，后者通过并查集。
        //利用并查集 将同一个父节点所有子节点的邮箱挂在父节点上
        Map<Integer, Set<String>> indexEmailsMap = new HashMap<>();
        for (int i = 0; i <accounts.size() ; i++) {
            int index = findUion.find(i);
            List<String> account = accounts.get(i);//将i邮箱放在index上
            List<String> emails=account.subList(1,account.size());
            if(indexEmailsMap.containsKey(index)){
                indexEmailsMap.get(index).addAll(emails);
            }else {
                indexEmailsMap.put(index,new TreeSet<>(emails));
            }
        }

        List<List<String>> ans = new ArrayList<>();
        //输出结果
        for(Map.Entry<Integer,Set<String>> entry:indexEmailsMap.entrySet()){
            List<String> tmp= new LinkedList<>();
            int index=entry.getKey();
            String name = accounts.get(index).get(0);
            tmp.add(name);
            tmp.addAll(entry.getValue());
            ans.add(tmp);
        }
        
        return ans;
        
    }

    public class findUion {
        int[] parent;

        public findUion(int n) {
            parent = new int[n];
            for (int i = 0; i <parent.length ; i++) {
                parent[i]=-1;
            }
        }
        
        public int find(int x){
            if(parent[x]<0){
                return x;
            }else {
                parent[x]=find(parent[x]);
            }
            return parent[x];
        }
        
        public void combine(int x,int y){
            int xx=find(x);
            int yy=find(y);
            if(xx!=yy){
                parent[yy]=xx;
            }
        }
    }
}

import java.util.*;

public class tttttt {
    class Solution {
        //这个题我看了半天，怎么看怎么超时，原来是中间有规律，该说不说，这个规律真的难找
        int[] ans, count;
        List<Set<Integer>> graph;
        int N;
        public int[] sumOfDistancesInTree(int N, int[][] edges) {
            this.N = N;
            graph = new ArrayList<Set<Integer>>();
            ans = new int[N];
            count = new int[N];
            Arrays.fill(count, 1);

            for (int i = 0; i < N; ++i)
                graph.add(new HashSet<Integer>());
            for (int[] edge: edges) {
                graph.get(edge[0]).add(edge[1]);
                graph.get(edge[1]).add(edge[0]);
            }
            dfs(0, -1);
            dfs2(0, -1);
            return ans;
        }

        public void dfs(int node, int parent) {
            for (int child: graph.get(node))
                if (child != parent) {
                    dfs(child, node);
                    //count[node]是以node为根的节点个数
                    count[node] += count[child];
                    //ans[node]是所有结点道node的距离
                    //就是ans[child]+child与node的距离
                    ans[node] += ans[child] + count[child];
                }
        }

        public void dfs2(int node, int parent) {
            for (int child: graph.get(node))
                if (child != parent) {
                    // ans[node] += ans[child] + count[child];
                    //这是ans[child]的前半部分，
                    //后半部分是，不是以child为根的节点个数
                    //后半部分是，因为，我们可以发现，每一个count都是多加了自己本身的，
                    //也就是我们前面的child是多加了自己本身的，所以不是child的根节点都没加上
                    ans[child] = ans[node] - count[child] + N - count[child];
                    dfs2(child, node);
                }
        }

    }
}

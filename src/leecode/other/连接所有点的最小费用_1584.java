package leecode.other;

import java.util.ArrayList;
import java.util.Collections;

public class 连接所有点的最小费用_1584 {
    //https://leetcode-cn.com/problems/min-cost-to-connect-all-points/solution/javashuang-bai-kruskalsuan-fa-bing-cha-ji-pan-duan/
    int[]f;
    public int find(int x){
        return f[x]==x?x:find(f[x]);
    }
    public int minCostConnectPoints(int[][] points) {
        int h=points.length;
        f=new int[h+1];
        //初始化 find 数组
        for (int i = 0; i <f.length ; i++) {
            f[i]=i;
        }
        ArrayList<Edge>edges=new ArrayList<>();
        for (int i = 0; i <h ; i++) {
            for (int j = i+1; j <h ; j++) {
                int len=Math.abs(points[i][0]-points[j][0])+Math.abs(points[i][1]-points[j][1]);
                if(len!=0){//!!
                    edges.add(new Edge(i,j,len));
                }
            }
        }
        int ans=0;
        Collections.sort(edges);
        for(Edge e:edges){
            int x=e.getX();
            int y=e.getY();
            int len=e.getLen();
            if(find(x)==find(y)){//如果两个节点是同一个集合中，说明之前已经有其他路径连过了
                continue;
            }
            ans=ans+len;
            f[find(x)]=find(y);//把两集合合并
        }
        return ans;
    }
    
    public class Edge implements Comparable<Edge>{
        private int x;
        private int y;
        private int len;
        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public Edge(int x, int y, int len) {
            this.x = x;
            this.y = y;
            this.len = len;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getLen() {
            return len;
        }

        public void setLen(int len) {
            this.len = len;
        }
        @Override
        public int compareTo(Edge o) {//实现按len升序
            return Integer.compare(this.len,o.len);
        }
    }
}

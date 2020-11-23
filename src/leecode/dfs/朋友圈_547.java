package leecode.dfs;

import zuoshen.输入输出练习.H;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

//建议看例子理解
//  1 1 0
//  1 1 0
//  0 0 1
public class 朋友圈_547 {
    public static int findCircleNum(int[][] M) {
        if(M.length==0||M==null){
            return 0;
        }
        int row=M.length;
        union union=new union(row);//有三个人，所以初始化三个朋友圈
        int col=M[0].length;
//        union union=new union(row*col);
        int res=0;
        for (int i = 0; i <row; i++) {
            for (int j = 0; j <col ; j++) {
                if(M[i][j]==1&&!union.isSameSet(i,j)){ //为1的话，说明互为朋友，将它们朋友圈合并
                    union.union(i,j);
                }
            }
        }
        res=union.numofset(row);//最后，头节点的数量（利用头节点的父亲是它自己特性，遍历所有初始化节点，找头节点）
        return res;
    }
    public void infect(int[][]m,int i,int j,int col,int row){
        if(i<0||i>=row||j<0||j>=row||m[i][j]!=1){
            return;
        }
        m[i][j]=2;
        infect(m,i+1,j,col,row);
        infect(m,i-1,j,col,row);
        infect(m,i,j-1,col,row);
        infect(m,i,j+1,col,row);
    }
    //并查集的代码
    public static class union {
        public class node {

        }
        public Map<Integer, Integer> fathermap;
        public Map<Integer, Integer> sizemap;
        public union(int size){
            fathermap=new HashMap<>();
            sizemap=new HashMap<>();
            makeSets(size);
        }
        public void makeSets(int size){
            for (int i = 0; i <size ; i++) {
                fathermap.put(i,i);
                sizemap.put(i,1);
            }
        }
        public int findHead(int n){
            int father=fathermap.get(n);
            if(father!=n){
                father=findHead(father);
            }
            fathermap.put(n,father);
            return father;
        }
        public boolean isSameSet(int m,int n){
            return findHead(m)==findHead(n);
        }
        public void union(int a,int b){
            int ahead=findHead(a);
            int bhead=findHead(b);
            if(ahead!=bhead){
                int asize=sizemap.get(ahead);
                int bsize=sizemap.get(bhead);
                if(asize<=bsize){
                    fathermap.put(ahead,bhead);
                    sizemap.put(bhead,asize+bsize);
                }else {
                    fathermap.put(bhead,ahead);
                    sizemap.put(ahead,asize+bsize);
                }
            }
        }
        public int numofset(int size){
            int ans=0;
            for (int i = 0; i <size ; i++) {
                if(fathermap.get(i)==i){
                    ans++;
                }
            }
            return ans;
        }
    }

    /*
    dfs求解：题目中的矩阵相当于是图的邻接矩阵表示，每个人是顶点，
    寻找图连通块的个数，一个简单的方法就是使用深度优先搜索，从每个节点开始，我们使用一个大小为N的数组（MM 大小为 N）
    https://leetcode-cn.com/problems/friend-circles/solution/chou-yi-xia-jiu-shi-ge-qiu-wu-xiang-tu-de-lian-ton/
    题目等价于已知邻接矩阵，求图连通分量的个数

     */

    public int findCircleNum2(int[][] M) {
        boolean[]visit=new boolean[M.length];
        int ans=0;
        for (int i = 0; i <M.length ; i++) {
            if(!visit[i]){
                ans++;
                dfs(M,visit,i);
            }
        }
        return ans;
    }
    public void dfs(int[][]M,boolean[]visit,int i){
        visit[i]=true;
        for (int j = 0; j <M.length ; j++) {
            if(!visit[j]&&M[i][j]==1){//j未被访问且i 和 j之间存在边（这个没有写临街表构成边，直接通过临街数组判断边）
                //dfs相当于移动指针
                dfs(M,visit,j);
            }
        }
    }

    //bfs https://leetcode-cn.com/problems/friend-circles/solution/cu-su-yi-dong-zhe-pian-ti-jie-rang-ni-xue-hui-bing/
    public int findCircleNum3(int[][] M) {
        boolean[]visit=new boolean[M.length];
        Queue<Integer>queue=new LinkedList<>();
        int count=0;
        for (int i = 0; i <M.length ; i++) {
            if(!visit[i]){
                queue.add(i);
                while (!queue.isEmpty()){
                    int cur=queue.poll();
                    visit[cur]=true;//在出队列再设置
                    for (int j = 0; j <M.length ; j++) {
                        if(!visit[j]&&M[cur][j]==1){//这里不是M[i][j]，而是M[cur][j]
                            queue.add(j);
                        }
                    }
                }
                count++;//这里更新count
            }
        }
        return count;
    }

        public static void main(String[] args) {
        int[][]M={{1,1,0},{1,1,0},{0,0,1}};

        int[][]N={{1,0,1},{0,1,0},{1,0,1}};
        System.out.println(findCircleNum(M));
        System.out.println(findCircleNum(N));
    }
}

package leecode.dfs;

import zuoshen.输入输出练习.H;

import java.util.HashMap;
import java.util.Map;
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

    public static void main(String[] args) {
        int[][]M={{1,1,0},{1,1,0},{0,0,1}};

        int[][]N={{1,0,1},{0,1,0},{1,0,1}};
        System.out.println(findCircleNum(M));
        System.out.println(findCircleNum(N));
    }
}

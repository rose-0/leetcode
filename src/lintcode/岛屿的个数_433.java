package lintcode;
//https://blog.csdn.net/qq_21515253/article/details/99703065
//并查集专题
import zuoshen.输入输出练习.I;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class 岛屿的个数_433 {
    public int numIslands(char[][] grid) {
        if(grid==null||grid.length==0){
            return 0;
        }
        int row=grid.length;
        int col=grid[0].length;
        if(row==0||col==0){
            return 0;
        }
        int[][]isLand=new int[row][col];
        for (int i = 0; i <row ; i++) {
            for (int j = 0; j <col ; j++) {
                if(grid[i][j]=='1'){
                    isLand[i][j]=1;
                }else {
                    isLand[i][j]=0;
                }
            }
        }
        int res=0;
        union union=new union(isLand);
        for (int i = 0; i <row ; i++) {
            for (int j = 0; j <col ; j++) {
                if(isLand[i][j]==1){
                    if(i+1<row&&isLand[i+1][j]==1){
                        union.unionSet(i*col+j,(i+1)*col+j);
                    }
                    if(j+1<col&&isLand[i][j+1]==1){
                        union.unionSet(i*col+j,i*col+j+1);
                    }
                }
            }
        }
        for (int i = 0; i <row ; i++) {
            for (int j = 0; j < col; j++) {
                if(isLand[i][j]==1){
                    if(union.findhead(i*col+j)==i*col+j){
                        res++;
                    }
                }
            }
        }
        return res;
    }

    class union{
        public Map<Integer,Integer>fatherMap=new HashMap<>();
        public Map<Integer,Integer>sizeMap=new HashMap<>();
        public Set<Integer> top=new HashSet<>();


        public union(int[][]m){
            int row=m.length;
            int col=m[0].length;
            for (int i = 0; i <row ; i++) {
                for (int j = 0; j <col ; j++) {
                    int temp=i*col+j;
                    top.add(temp);
                    fatherMap.put(temp,temp);
                    sizeMap.put(temp,1);
                }
            }
        }
        public int findhead(int node){
            int father=fatherMap.get(node);
            if(father!=node){
                father=findhead(father);
            }
            fatherMap.put(node,father);
            return father;
        }
        public boolean isSameset(int node1,int node2){
            return findhead(node1)==findhead(node2);
        }
        public void unionSet(int a,int b){
            int ahead=findhead(a);
            int asize=sizeMap.get(a);
            int bhead=findhead(b);
            int bsize=sizeMap.get(b);
            if(ahead==bhead){
                return;
            }
            if(asize<=bsize){
                fatherMap.put(ahead,bhead);
                sizeMap.put(ahead,asize+bsize);
            }else {
                fatherMap.put(bhead,ahead);
                sizeMap.put(bhead,asize+bsize);
            }
        }

    }


    public void infect(int[][]isLand,int i,int j,int col,int row){
        if(i<0||i>=row||j<0||j>=col||isLand[i][j]!=1){
            return;
        }
        isLand[i][j]=2;
        infect(isLand,i+1,j,col,row);
        infect(isLand,i,j+1,col,row);
        infect(isLand,i-1,j,col,row);
        infect(isLand,i,j-1,col,row);
    }
}

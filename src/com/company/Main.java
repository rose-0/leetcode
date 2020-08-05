package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
class Node{
    public int value;
    public int in;
    public int out;

}

public class Main {

    Integer  m=100;
    ArrayList<Integer> G=new ArrayList<Integer>();
    boolean []vis=new boolean[m];
    int[]a;
    void dfs(int v){
        vis[v]=true;
        for (int i = 0; i <vis.length ; i++) {
            if(!vis[a[i]]){
               return;
            }
        }
    }


    public static void main(String[] args) {
	// write your code here



    }
}


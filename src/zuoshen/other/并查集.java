//package zuoshen.other;
//
//import zuoshen.输入输出练习.H;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Scanner;
//import java.util.Stack;
//
//public class 并查集 {
//    public class node{
//        public int value;
//        public node left;
//        public node right;
//        public node(int data){
//            this.value=data;
//        }
//    }
//    public HashMap<Integer,Integer>fatherMap;
//    public HashMap<Integer,Integer>sizeMap;
//    public 并查集(int[]arr){
//        fatherMap=new HashMap<>();
//        sizeMap=new HashMap<>();
//        makeSet(arr);
//    }
//    public void makeSet(int[]arr){
//        for (int i:
//             arr) {
//            fatherMap.put(i,i);
//            sizeMap.put(i,1);
//        }
//    }
//    //递归
//    public int findHead(int i){
//        int father=fatherMap.get(i);
//        if(father!=node){
//            father=findHead(father);
//        }
//        fatherMap.put(node,father);
//        return father;
//    }
//    //非递归
//    public node findhead2(node node){
//        Stack<node>stack=new Stack<>();
//        node father=fatherMap.get(node);
//        while (father!=node){
//            stack.push(node);
//            node=father;
//            father=fatherMap.get(node);
//        }
//        //将路径上每个节点指向根节点
//        while (!stack.empty()){
//            fatherMap.put(stack.pop(),father);
//        }
//        return father;
//    }
//    public boolean isSameSet(node a,node b){
//        return findHead(a)==findHead(b);
//    }
//    public void union(node a,node b){
//        if(a==null||b==null){
//            return;
//        }
//        node ahead=findHead(a);
//        node bhead=findHead(b);
//        if(ahead!=bhead){
//            int asize=sizeMap.get(ahead);
//            int bsize=sizeMap.get(bhead);
//            if(asize>bsize){
//                fatherMap.put(bhead,ahead);
//                sizeMap.put(ahead,asize+bsize);
//            }else {
//                fatherMap.put(ahead,bhead);
//                sizeMap.put(bhead,asize+bsize);
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        Scanner sc=new Scanner(System.in);
//        while (sc.hasNext()){
//            int n=sc.nextInt();
//            int[]arr=new int[n];
//            for (int i = 0; i <n ; i++) {
//                arr[i]=i+1;
//            }
//            int m=sc.nextInt();
//            for (int i = 0; i <m ; i++) {
//
//            }
//        }
//    }
//}

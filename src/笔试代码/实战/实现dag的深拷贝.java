package 笔试代码.实战;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//https://m.isolves.com/it/cxkf/cxy/2020-08-06/27248.html
//阿里面试代码题目 实现有向无环图dag的 deep copy
public class 实现dag的深拷贝 {
    static class Node{
        private int val;
        private Collection<Node>neighbors;

        public Node(int val,Collection<Node>neighbors){
            this.val=val;
            this.neighbors=neighbors;
        }
        public Collection<Node> getNeighbor(){
            return this.neighbors;
        }
        public int getVal(){
            return this.val;
        }
    }
    public static Collection<Node> DeepCopy(Collection<Node> nodes){
        Collection<Node> newNodes=new ArrayList<>();
        Map<Node,Node> copyMap=new HashMap<>();
        for(Node node:nodes){
            newNodes.add(copyNode(node,copyMap));
        }
        return newNodes;
    }
    private static Node copyNode(Node node,Map<Node,Node>copyMap){
        if(copyMap.containsKey(node)){
            return copyMap.get(node);
        }
        //新节点需要原节点的值以及邻居
        //对邻居的每个节点复制构成新节点的邻居集合
        Collection<Node> newNeighbors=new ArrayList<>();
        for(Node neighNode:node.getNeighbor()){
            newNeighbors.add(copyNode(neighNode,copyMap));
        }
        //构造新节点
        Node newNode=new Node(node.getVal(),newNeighbors);
        //放在map缓存
        copyMap.put(node,newNode);
        return newNode;
    }
}

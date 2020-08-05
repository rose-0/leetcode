package leecode.other;

import java.util.HashMap;
import java.util.Map;

public class LFU缓存_460 {
    class node{
        private int key;
        private int val;
        private int fre;
        private node pre;
        private node next;
        public node(int key,int val,int fre){
            this.key=key;
            this.val=val;
            this.fre=fre;
        }
    }
    class DoubleList{
        private node head;
        private node tail;
        private int size;
        public DoubleList(){
            head=new node(0,0,0);
            tail=new node(0,0,0);
            head.next=tail;
            tail.pre=head;
            this.size=0;
        }

        public void deleNode(node x){
            x.next.pre=x.pre;
            x.pre.next=x.next;
            size--;
        }
        public node removeLast(){
            if(head.next==tail){
                return null;
            }
            node prenode=tail.pre;
            deleNode(prenode);
            return prenode;
        }
        public void addFirst(node x){
            x.next=head.next;
            x.pre=head;
            x.next.pre=x;
            head.next=x;
            size++;
        }
    }

    private Map<Integer,node> map;
    private DoubleList doubleList;
    private Map<Integer,DoubleList> freMap;
    private int capacity;
    private int minFre=0;//记录缓存最小频率
    public LFU缓存_460(int capacity){
        map=new HashMap<>();
        freMap=new HashMap<>();
        doubleList=new DoubleList();
        this.capacity=capacity;
    }
    public int get(int key){
        if(map.containsKey(key)){
            node node=map.get(key);
            increment(node,false);
            return node.val;
            /*
            put(key,map.get(key).val);
            return map.get(key).val;
             */
        }else {
            return -1;
        }
    }
    public void put(int key,int value){

        if(map.containsKey(key)){
            node node=map.get(key);
            node.val=value;
            increment(node,false);//更新节点的频率，而不是移动节点到头部
            /*
            doubleList.deleNode(map.get(key));
            doubleList.addFirst(x);
            map.put(key,x);
             */
        }else {
            if(this.capacity==doubleList.size){
                delMinFreNode();//删除最小频度 最久使用
                /*
                node last=doubleList.removeLast();
                map.remove(last.key);
                 */
            }
            node node=new node(key,value,1);//初始频度为1
            increment(node,true);//添加新节点到链表

            map.put(key,node);
        }
    }
    public void increment(node node,boolean isNew){//更新节点的访问频度
        if(isNew){
            minFre=1;//跟新minfre
            addFreqList(node);//添加到频度中
        }else {
            delFreNode(node);//node从原来对应的频度链表中删除
            node.fre++;
            addFreqList(node);
            if(!freMap.containsKey(minFre)){
                ++minFre;
            }
        }
    }
    public void addFreqList(node node){
        if(!freMap.containsKey(node.fre)){
            freMap.put(node.fre,new DoubleList());//构造对应的链表，再把节点加入到链表中
        }
        DoubleList doubleList=freMap.get(node.fre);
        doubleList.addFirst(node);//添加到头节点
    }
    public void delFreNode(node node){
        DoubleList doubleList=freMap.get(node.fre);
        doubleList.deleNode(node);
        if(doubleList.head.next==doubleList.tail){//整个链表没有节点，则删除
            freMap.remove(node.fre);
        }
    }
    public void delMinFreNode(){
        DoubleList doubleList=freMap.get(minFre);
        node node=doubleList.tail.pre;//删除对应频度链表上的尾部节点
        doubleList.deleNode(node);
        if(doubleList.head.next==doubleList.tail){//链表没有节点，则移除
            freMap.remove(node.fre);
        }
    }
}

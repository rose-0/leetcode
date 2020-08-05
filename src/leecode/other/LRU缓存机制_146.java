package leecode.other;

import org.omg.CORBA.NO_IMPLEMENT;
import zuoshen.list.Node;
import zuoshen.输入输出练习.H;
import zuoshen.输入输出练习.I;

import java.util.HashMap;
import java.util.Map;

public class LRU缓存机制_146 {
    //github上这个好理解https://github.com/labuladong/fucking-algorithm/blob/master/%E9%AB%98%E9%A2%91%E9%9D%A2%E8%AF%95%E7%B3%BB%E5%88%97/LRU%E7%AE%97%E6%B3%95.md
    //完整代码 https://leetcode-cn.com/problems/lru-cache/solution/lru-ce-lue-xiang-jie-he-shi-xian-by-labuladong/

    //要定义两个class！！  Node节点（两个值！！），doublelist（使用node节点），lru使用这两个class
    //不同于单链表的节点，要有两个值，两个指针
    class Node{
        public int key;
        public int val;
        public Node next;
        public Node pre;
        public Node(int key,int val){
            this.key=key;
            this.val=val;
        }
    }

    //用重新定义的节点，且声明头尾指针
    //加上size
    class DoubleList{
        private Node head;
        private Node tail;
        private int size;
        public DoubleList(){
            head=new Node(0,0);
            tail=new Node(0,0);
            head.next=tail;
            tail.pre=head;
            size=0;
        }
        public void addFirst(Node x){
            x.next=head.next;
            x.pre=head;
            x.next.pre=x;
            head.next=x;
            size++; //不要忘记这个
        }

        public void remove(Node x){//删除x，且x一定存在
            x.pre.next=x.next;
            x.next.pre=x.pre;
            size--;//不要忘记这个
        }
        public Node removeLast(){//移除最后一个节点是，要返回的
            if(tail.pre==head){//要判断下这个
                return null;
            }
            Node last=tail.pre;
            remove(last);//调用这个就可以

            return last;
        }
        public int size(){
            return size;
        }
    }
    /*
    HashMap<Integer,Node>map; //key到Node(key,val)
    DoubleList cache;

    int get(int key){
        if(key 不存在）
            return -1；
        else
            从map中找到数据，将数据（key，val）提到开头
            return val；
    }

    void put(int key,int val){
        Node x=new Node(key,val);
        if(key 已存在）{
            旧数据删除，
            新数据放在头部
        }else{
            if(cache 已满）{
                删除链表最后一个数据；
                同时删除map中映射到最后这个节点的键；
            }
            将新节点插到开头；
            map中新建key对新节点的映射；
        }
    }
     */

    private HashMap<Integer,Node>map;
    private DoubleList cache;
    private int cap;//cap是lru缓存的大小，限制链表的大小
    public LRU缓存机制_146(int cap){
        this.cap=cap;
        map=new HashMap<>();
        cache=new DoubleList();
    }
    public int get(int key){
        if(!map.containsKey(key)){
            return -1;
        }
        int val=map.get(key).val;//先获得节点，再取得节点的值
        put(key,val);//调用put方法提前！！！
        return val;
    }
    public void put(int key,int val){
        Node x=new Node(key,val);//先构造节点
        if(map.containsKey(key)){
            cache.remove(map.get(key));//不能写成 cache.remove(x); 这错了！！!
            cache.addFirst(x);
        }else {
            if(cap==cache.size()){
                Node last=cache.removeLast();
                map.remove(last.key);//不是last
            }
            cache.addFirst(x);//后面这两句不能放在if 容量最大的里面
            map.put(key,x);
        }
    }

























    public static class LRUCache {
        private HashMap<node, Integer> nodeIntMap;
        private HashMap<Integer, node> IntnodeMap;
        private int capacity;
        private nodeDoubleList nodeDoubleList;
        public LRUCache(int capacity){
            this.nodeIntMap = new HashMap<>();
            this.IntnodeMap = new HashMap<>();
            this.capacity=capacity;
            this.nodeDoubleList = new nodeDoubleList();
        }

        public int get(int key) {
            if(IntnodeMap.containsKey(key)){
                node node=IntnodeMap.get(key);
                nodeDoubleList.moveNodeTotail(node);
                return node.value;
            }else {
                return -1;
            }
        }

        public void put(int key, int value) {
            if(IntnodeMap.containsKey(key)){
                node node=IntnodeMap.get(key);
                nodeDoubleList.moveNodeTotail(node);
                node.value=value;
            }else {
                node node=new node(value);
                nodeDoubleList.addlist(node);
                nodeIntMap.put(node,key);
                IntnodeMap.put(key,node);
                if(nodeIntMap.size()==capacity+1){
                    remove();
                }
            }
        }
        public void remove(){
            node head=nodeDoubleList.deleteHead();
            int key=this.nodeIntMap.get(head);
            this.nodeIntMap.remove(head,key);
            this.IntnodeMap.remove(key,head);
//            nodeDoubleList.deleteHead();
        }
        /*
        public void remove(){
            node head=nodeDoubleList.head;
            nodeIntMap.remove(head,head.value);
            IntnodeMap.remove(head.value,head);
            nodeDoubleList.deleteHead();
        }
         */
        public class node{
            public int value;
            public node last;
            public node next;
            public node(int value){
                this.value=value;
            }
        }
        public class nodeDoubleList{
            node head;
            node tail;
            public void addlist(node node){
                if(node==null){
                    return;
                }
                if(this.head==null){
                    this.head=node;
                    this.tail=node;
                }else {
                    this.tail.next=node;
                    node.last=this.tail;
                    this.tail=node;
                }
            }
            public void moveNodeTotail(node node){
                if(node==null){
                    return;
                }
                if(this.tail==node){
                    return;
                }
                if(this.head==node){
                    this.head=node.next;
                    this.head.last=null;
                }else {
                    node.last.next=node.next;
                    node.next.last=node.last;
                }
                this.tail.next=node;
                node.last=this.tail;
                this.tail=node;
                node.next=null;
            }

            public node deleteHead(){
                node node=this.head;
                if(this.tail==this.head){
                    this.tail=null;
                    this.head=null;
                }else {
                    this.head = head.next;
                    node.next=null;
                    this.head.last = null;
                }
                return node;
            }
        }
    }

    public static void main(String[] args) {
        LRUCache lruCache=new LRUCache(1);
        lruCache.put(2,1);
//        lruCache.put(2,2);
        System.out.println(lruCache.get(2));
        lruCache.put(3,2);
        lruCache.get(2);
        lruCache.get(3);
    }
}

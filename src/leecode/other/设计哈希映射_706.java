package leecode.other;
//设计hash表，拉链法
public class 设计哈希映射_706 {
    class Node{
        private int key;
        private int value;
        Node next;
        public Node(int key,int value){
            this.key=key;
            this.value=value;
        }
    }

    private final int size=1000;
    private Node[]data;
    public 设计哈希映射_706(){
        data=new Node[size];
    }

    //put 、 remove 删除和更新都是用的pre去遍历，get查找用的cur遍历，其实好像无所谓？,
    // 删除一定要用前一个！！更新最后跳出来要赋值next节点，也要用pre
    public void put(int key,int value){
        int hash=hash(key);
        if(data[hash]==null){
            data[hash]=new Node(-1,-1);//注意这个
            data[hash].next=new Node(key,value);
        }else {
            Node pre=data[hash];//从头节点遍历
            while (pre.next!=null){
                if(pre.next.key==key){//原来就存在，则更新
                    pre.next.value=value;
                    return;
                }
                pre=pre.next;
            }
            pre.next=new Node(key,value);//没有，则添加,记住这个
        }
    }

    public int get(int key){
        int hash=hash(key);
        if(data[hash]!=null){
            Node cur=data[hash].next;
            while (cur!=null){
                if(cur.key==key){
                    return cur.value;
                }
                cur=cur.next;
            }
        }
        return -1;
    }

    public void remove(int key){
        int hash=hash(key);
        if(data[hash]!=null){
            Node pre=data[hash];
            while (pre.next!=null){
                if(pre.next.key==key){
                    Node delNode=pre.next;
                    pre.next=delNode.next;
                    delNode.next=null;
                    return;
                }
                pre=pre.next;
            }
        }
    }

    private int hash(int key){
        return key%size;
    }
}

package 剑指offer;
//https://leetcode-cn.com/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/solution/mian-shi-ti-36-er-cha-sou-suo-shu-yu-shuang-xian-5/
public class 二叉搜索树与双向链表_36 {
    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    public Node treeToDoublyList(Node root) {
        if(root==null){
            return null;
        }
        transferList(root);
        pre.right = head;
        head.left =pre;//进行头节点和尾节点的相互指向，这两句的顺序也是可以颠倒的
        return head;
    }
    Node pre;
    Node head;
    public void transferList(Node root){
        if(root==null){
            return;
        }
        transferList(root.left);
        if(pre==null){
            head=root;
        }else {
            pre.right=root;
        }
        root.left=pre;//pre是否为null对这句没有影响,且这句放在上面两句if else之前也是可以的。
        pre=root;
        transferList(root.right);//全部迭代完成后，pre指向双向链表中的尾节点
    }

}

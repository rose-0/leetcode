package zuoshen.Tree;

import java.util.HashSet;

public class test {
    public static void main(String[] args) {
        treeNode head=new treeNode(5);
        head.left=new treeNode(1);
        head.right=new treeNode(3);
        head.left.left=new treeNode(2);
        head.left.right=new treeNode(4);
        new 调整搜索二叉树中两个错误的节点().check_tree(head);
//        new 后序非递归().postOrderNo(head);
    }

}

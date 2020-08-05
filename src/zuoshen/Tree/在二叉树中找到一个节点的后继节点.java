package zuoshen.Tree;

import zuoshen.list.Node;

public class 在二叉树中找到一个节点的后继节点 {
    public treeNode find_node(treeNode head){
        if(head==null){
            return null;
        }
        if(head.right!=null){
            treeNode temp=head.right;
            while (temp.left!=null){
                temp=temp.left;
            }
            return temp;
        }else {
            treeNode parent=head.parent;
            while (head!=parent.left&&parent!=null){
                head=parent;
                parent=head.parent;
            }
            return parent;
        }

    }
}

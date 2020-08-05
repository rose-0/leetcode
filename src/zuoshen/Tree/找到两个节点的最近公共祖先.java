package zuoshen.Tree;

public class 找到两个节点的最近公共祖先 {
    public treeNode lowestAnces(treeNode head,treeNode node1,treeNode node2){
        if(head==null||node1==head||head==node2){
            return head;
        }
        treeNode left=lowestAnces(head.left,node1,node2);
        treeNode right=lowestAnces(head.right,node1,node2);
        if(left!=null||right!=null){
            return head;
        }
        return left==null?right:left;
    }




    public treeNode method_own(treeNode root,treeNode p,treeNode q){
        if(root==null){
            return null;
        }
        if(root==p||root==q){
            return root;
        }
        treeNode left=method_own(root.left,p,q);
        treeNode right=method_own(root.right,p,q);
        if(left!=null&&right!=null){
            return root;
        }else {
            return left==null?right:left;
        }
    }
}

package leecode.dfs;

import sun.reflect.generics.tree.Tree;

import java.util.LinkedList;
import java.util.List;

public class 不同的二叉搜索树II_95 {
    public static List<TreeNode> generateTrees(int n) {
        List<TreeNode> list = new LinkedList<>();
        if(n==0){
            return null;
        }
        return gene(1,n);//从1开始，不是0，看清题目要求
    }
    public static List<TreeNode>gene(int start,int end) {
        List<TreeNode> list = new LinkedList<>();
        if (start > end) {
            list.add(null);
        }
        TreeNode head = null;
        for (int i = start; i < end + 1; i++) {
            head = new TreeNode(i);
            List<TreeNode> lsub = gene(start, i - 1);
            List<TreeNode> rsub = gene(i + 1, end);
            for (TreeNode lnode : lsub) {
                for (TreeNode rnode : rsub) {
                    head.left = lnode;
                    head.right = rnode;
                    list.add(clone(head));
                }
            }
        }
        return list;
    }
    public static TreeNode clone(TreeNode head){
        if(head==null){
            return null;
        }
        TreeNode res=new TreeNode(head.val);
        res.left=clone(head.left);
        res.right=clone(head.right);
        return res;
    }







    public static List<TreeNode> generateTrees2(int n) {

        return gene2(1,n);//从1开始，不是0，看清题目要求
    }
    public static List<TreeNode>gene2(int left,int right){
        List<TreeNode>res=new LinkedList<>();
        if(left>right){
            res.add(null);
        }
        for (int i = left; i <=right ; i++) {
            TreeNode root=new TreeNode(i);
            List<TreeNode>leftnode=gene2(left, i-1);
            List<TreeNode>rightnode=gene2(i+1,right);
            for (int j = 0; j <leftnode.size() ; j++) {
                for (int k = 0; k <rightnode.size() ; k++) {
                    root.left=leftnode.get(j);
                    root.right=rightnode.get(k);
                    res.add(clone1(root));
                }
            }
        }
        return res;
    }
    public static TreeNode clone1(TreeNode root){
        if(root==null){
            return null;
        }
        TreeNode head=new TreeNode(root.val);
        head.left=root.left;
        head.right=root.right;
        return head;
    }
}

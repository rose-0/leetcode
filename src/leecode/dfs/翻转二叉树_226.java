package leecode.dfs;

import java.util.Stack;

//剑指offer 二叉树镜像
//https://leetcode-cn.com/problems/er-cha-shu-de-jing-xiang-lcof/solution/mian-shi-ti-27-er-cha-shu-de-jing-xiang-di-gui-fu-/
public class 翻转二叉树_226 {
    public TreeNode invertTree(TreeNode root) {
        if(root==null){
            return null;
        }
        TreeNode head=new TreeNode(root.val);
        head.left=invertTree(root.right);
        head.right=invertTree(root.left);
        return head;
    }
    //不开辟空间的方式:
    public TreeNode invertTree2(TreeNode root) {
        if(root==null){
            return null;
        }
        TreeNode temp=root.left;//暂存 root 的左子节点
        root.left=invertTree2(root.right);//返回值作为 root 的 左子节点 。
        root.right=invertTree2(temp);
        return root;
    }
    //非递归
    public TreeNode invertTree3(TreeNode root) {
        if(root==null){
            return null;
        }
        Stack<TreeNode>stack=new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode cur=stack.pop();
            //因为每个节点都要处理，所以把当前节点的左右孩子也要入栈，先入左孩子还是右孩子都可以
            if(cur.left!=null){
                stack.push(cur.left);
            }
            if(cur.right!=null){
                stack.push(cur.right);
            }
            //对当前节点处理，交换当前节点的左右节点，这个放到前面弹出时就处理也可以
            TreeNode temp=cur.left;
            cur.left=cur.right;
            cur.right=temp;
        }
        return root;
    }

}

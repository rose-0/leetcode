package leecode.dfs;

import java.util.Stack;

public class 二叉树的最大深度_104 {
    public int maxDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        Stack<TreeNode>stack=new Stack<>();
        stack.push(root);
        int depth=1;
        int max=depth;
        while (!stack.empty()){
            root=stack.pop();

            if(root.right!=null){
                stack.push(root.right);
                depth++;
                max=max>depth?max:depth;
            }
            if(root.left!=null){
                stack.push(root.left);
                depth++;
                max=max>depth?max:depth;
            }
            depth--;
        }
        return max;
    }
}

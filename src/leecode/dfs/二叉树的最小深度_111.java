package leecode.dfs;

import java.util.LinkedList;
import java.util.Queue;

public class 二叉树的最小深度_111 {
    public int minDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        //题目说法是从根节点到最近叶子节点的最短距离，如1，2应该返回2不是1
        //如果根节点的左右子树有一个为空的话，空的不参与计算，只计算不空的！！
        if(root.left==null&&root.right!=null){
            return 1+minDepth(root.right);
        }
        if(root.right==null&&root.left!=null){
            return 1+minDepth(root.left);
        }
        int left=minDepth(root.left);
        int right=minDepth(root.right);

        return Math.min(left,right)+1;
    }
    //使用bfs框架，见labuladong 公众号
    public int minDepthByBfs(TreeNode root){
        Queue<TreeNode>queue=new LinkedList<>();
        int depth =1;
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i <size ; i++) {
                TreeNode cur= queue.poll();
                if(cur.left==null&&cur.right==null){
                    return depth;
                }

                if(cur.left!=null){
                    queue.offer(cur.left);
                }

                if(cur.right!=null){
                    queue.offer(cur.right);
                }
            }
            depth++;
        }
        return depth;
    }
}

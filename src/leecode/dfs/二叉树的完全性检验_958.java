package leecode.dfs;

import java.util.LinkedList;
import java.util.Queue;

//https://leetcode-cn.com/problems/check-completeness-of-a-binary-tree/solution/hen-jian-dan-de-si-lu-dai-ma-hen-jian-ji-by-yuanyb/
public class 二叉树的完全性检验_958 {
    //
    //LinkedList做队列的话支持添加null元素，而ArrayDeque不支持添加null,这个题要添加空
    public boolean isCompleteTree(TreeNode root) {
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        TreeNode pre=root;
        while (!queue.isEmpty()){
            TreeNode cur=queue.poll();
            if(pre==null&&cur!=null){
                return false;
            }
            if(cur!=null){
                queue.offer(cur.left);
                queue.offer(cur.right);
            }
            pre=cur;
        }
        return true;
    }
}

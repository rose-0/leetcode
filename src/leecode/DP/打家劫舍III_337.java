package leecode.DP;

import leecode.dfs.TreeNode;

import java.util.HashMap;

public class 打家劫舍III_337 {
    //这个是树状dp题目
    //leecode 王小二题解 +labuladong
    //暴力解法
    public int rob_baoli(TreeNode root){
        if(root==null){
            return 0;
        }
        int money= root.val;
        if(root.left!=null){
            //偷了根，就不能再偷根的左孩子，只能投他的左孙子
            money=money+rob_baoli(root.left.left)+rob_baoli(root.left.right);
        }
        if(root.right!=null){
            money=money+rob_baoli(root.right.left)+rob_baoli(root.right.right);
        }
        //比较偷根 和 不偷根 哪个大
        return Math.max(money,rob_baoli(root.left)+rob_baoli(root.right));
    }
    //备忘录
    private HashMap<TreeNode,Integer>map;
    public int rob_memo(TreeNode root){
        if(root==null){
            return 0;
        }
        if(map.containsKey(root)){
            return map.get(root);
        }
        int money=root.val;
        if(root.left!=null){
            money=money+rob_memo(root.left.left)+rob_memo(root.left.right);
        }
        if(root.right!=null){
            money=money+rob_memo(root.right.left)+rob_memo(root.right.right);
        }
        int result=Math.max(money,rob_memo(root.left)+rob_memo(root.right));
        map.put(root,result);
        return result;
    }
    //树状dp解法
    /*
    int[]result 0代表不偷 1代表偷
    不偷时最大钱=左孩子最大钱+右孩子最大钱
    偷时=左孩子不偷时+右孩子不偷时+当前节点钱
     */
    public int rob_dp(TreeNode root){
        int[]result=dp(root);
        return Math.max(result[0],result[1]);
    }
    public int[] dp(TreeNode root){
        if(root==null){
            return new int[2];
        }
        int[]result=new int[2];

        int[]left=dp(root.left);
        int[]right=dp(root.right);

        //不是result[0]=left[1]+right[1]，因为不偷根，不代表一定偷左右孩子才最大，也可以不偷左右孩子。
        result[0]=Math.max(left[0],left[1])+Math.max(right[0],right[1]);

        result[1]=left[0]+right[0]+root.val;

        return result;
    }
}

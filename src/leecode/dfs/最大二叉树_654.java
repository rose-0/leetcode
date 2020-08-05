package leecode.dfs;

public class 最大二叉树_654 {
    public class TreeNode {
     int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return method(nums,0,nums.length-1);
    }
    public TreeNode method(int[]nums,int left,int right){
//        if(left==right){
//            return new TreeNode(left);
//        }
        if(left>right){
            return null;
        }
        int max=nums[left];
        int index=left;
        for (int i = left; i <=right ; i++) {
            if(nums[i]>max){
                max=nums[i];
                index=i;
            }
        }
        TreeNode head=new TreeNode(max);
        head.left=method(nums,left,index-1);
        head.right=method(nums,index+1,right);
        return head;
    }
}

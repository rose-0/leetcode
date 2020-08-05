package leecode.dfs;
/*
构造树也是先构造根，再构造左右，找到数组里面根的下标（中间靠左靠右都可以），构造根，然后再递归构造左右
递归终止的条件是l>r

 */
public class 将有序数组转换为二叉搜索树_108 {
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums==null){
            return null;
        }
        return sort(nums,0,nums.length-1);
    }
    public TreeNode sort(int[]nums,int l,int r){
        if(l>r){//不要忘记这个
            return null;
        }
        int mid=l+(r-l)>>2;
        TreeNode head=new TreeNode(nums[mid]);
        head.left=sort(nums,l,mid-1);
        head.right=sort(nums,mid+1,r);
        return head;
    }

}

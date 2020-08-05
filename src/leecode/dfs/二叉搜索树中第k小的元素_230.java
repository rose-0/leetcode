package leecode.dfs;

public class 二叉搜索树中第k小的元素_230 {
    int num=0;
    int res;
    public int kthSmallest(TreeNode root, int k) {
        //windiang的解法
        kthdigui(root,k);
        return res;
    }
    public void kthdigui(TreeNode root,int k){
        if(root==null){
            return;
        }
        kthdigui(root.left,k);
        num++;
        if(num==k){
            res=root.val;
            return;
        }
        kthdigui(root.right,k);
    }
    //分治法
    public int kthsmall(TreeNode root,int k){
        int n=getNodeCount(root.left);
        if(n+1==k){
            return root.val;
        }else if(n+1<k){
            return kthsmall(root.right,k-n-1);
        }else {
            return kthsmall(root.left,k);
        }
    }
    private int getNodeCount(TreeNode root){
        if(root==null){
            return 0;
        }
        return 1+getNodeCount(root.left)+getNodeCount(root.right);
    }
}

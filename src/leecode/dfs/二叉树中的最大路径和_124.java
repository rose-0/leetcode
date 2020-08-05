package leecode.dfs;
//对比 二叉树节点的最大距离 P169
public class 二叉树中的最大路径和_124 {
    int len=0;

    public int maxPathSum(TreeNode root) {
//         int len = 0;这个要声明为全局变量！！
        maxPathSumMe(root);
        return len;
    }
    public int maxPathSumMe1(TreeNode root) {
        if(root==null){
            return 0;
        }

        int left=Math.max(0,maxPathSumMe(root.left));//// 如果子树路径和为负则应当置0表示最大路径不包含子树!!
        int right=Math.max(0,maxPathSumMe(root.right));
        len=Math.max(left+right+root.val,len);
//        return Math.max(len,Math.max(left,right));//这样写不对，应该返回本节点子树的最大值
        return Math.max(left,right)+root.val;//len更新的是经过根节点的最大值，left是包含左子树节点返回最大值，所以这里返回的要带上根
    }

    static int max=0;
    public static int maxPathSum2(TreeNode root) {
//        int len = 0;
        maxPathSumMe(root);//写成max=maxPathSumMe(root) 就不对了，等于max是返回的return！！为子树最大值
        return max;
    }
    public static int maxPathSumMe(TreeNode root) {
        if(root==null){
            return 0;
        }
        int left=Math.max(0,maxPathSumMe(root.left));//// 如果子树路径和为负则应当置0表示最大
        int right=Math.max(0,maxPathSumMe(root.right));
        max=Math.max(left+right+root.val,max);
//        return Math.max(len,Math.max(left,right));//这样写不对，应该返回本节点的最大值
        return Math.max(left,right)+root.val;
    }

    public static void main(String[] args) {
        TreeNode root=new TreeNode(2);
        root.left=new TreeNode(1);
        root.right=new TreeNode(3);
        System.out.println(maxPathSum2(root));
    }
}

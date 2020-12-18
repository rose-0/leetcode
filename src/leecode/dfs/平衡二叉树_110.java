package leecode.dfs;
//递归博客http://39.96.217.32/blog/4
//https://lyl0724.github.io/2020/01/25/1/
/*
递归三部曲
看那个调用图
    A
调用| |返回值
    A
   | |
   终止条件

1.找到递归的终止条件
2.知道本层递归需要做什么(知道调用的返回值是什么，即函数的返回值)
    2.1 对本层递归的处理就是对传入参数的处理
    2.2 本层递归要不要用到下层递归的返回值，知道返回的是什么，要不要将他们保存起来
    2.3如果是先序遍历，本层递归处理完成后，下一层传入的参数要不要变化
 */
public class 平衡二叉树_110 {
    public boolean isBalanced1(TreeNode root) {
        if(root==null){
            return true;
        }
        int left=height(root.left);
        int right=height(root.right);
        if( Math.abs(left-right)>1){
            return false;
        }else {
            return isBalanced1(root.left)&&isBalanced1(root.right);
        }
    }
    public int height(TreeNode root){
        if(root==null){
            return 0;
        }
        return Math.max(height(root.left),height(root.right))+1;
    }
    public class returnnode{
        int high;
        boolean flag;
        public returnnode(int high,boolean flag){
            this.flag=flag;
            this.high=high;
        }
    }
    public returnnode isBalanced_1(TreeNode root) {
        if(root==null){
            return new returnnode(0,true);
        }
        returnnode left=isBalanced_1(root.left);
        returnnode right=isBalanced_1(root.right);
        if(left.flag==false||right.flag==false){
            return new returnnode(0,false);
        }
        if(Math.abs(left.high-right.high)>1){
            return new returnnode(0,false);
        }
        int height=Math.max(left.high,right.high);
        return new returnnode(height+1,true);
    }
}

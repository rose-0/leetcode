package leecode.dfs;
/*
二叉搜索树中序遍历升序，所以进行中序遍历，temp的初始化要声明在外面，因为函数是递归的
 */
//先序遍历也可以，看看这个
//https://github.com/labuladong/fucking-algorithm/blob/master/%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84%E7%B3%BB%E5%88%97/%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91%E6%93%8D%E4%BD%9C%E9%9B%86%E9%94%A6.md
public class 验证二叉搜索树_98 {
    double temp=-Double.MAX_VALUE;
    public boolean isBst(TreeNode root){
//         double  temp=-Double.MAX_VALUE;
        if(root==null){
            return true;
        }
        if(!isBst(root.left)){
            return false;
        }
        if(temp<root.val){
            temp=root.val;
        }else {
            return false;
        }
        if(!isBst(root.right)){
            return false;
        }
        return true;
    }
    TreeNode pre=null;
    public boolean method(TreeNode root){
        if(root==null){
            return true;
        }
        if(method(root.left)){
            if(pre!=null){
                if(pre.val>root.val){
                    return false;
                }
            }
            pre=root;
            return method(root.right);
        }else {
            return false;
        }
    }
}

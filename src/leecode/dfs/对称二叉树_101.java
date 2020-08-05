package leecode.dfs;
/*
判断二叉树对称，两个指针，分别走左子树和右子树（这儿处理根是否有两个子树节点的情况），
走的方向相反，判断树是否完全相同，即调用相同的树的方法，只是走的方向不同
 */
public class 对称二叉树_101 {
    public boolean isSymmetric(TreeNode root) {
        if(root==null){
            return true;
        }
        //注释掉这个叶子节点也可以 issame里面有相同的逻辑
        if((root.left==null)&&(root.right==null)){
            return true;
        }
        //注释掉这个也可以！！  issame里面有相同的逻辑
        if((root.left==null&&root.right!=null)||(root.left!=null&&root.right==null)){
            return false;
        }
        return issame(root.left,root.right);
    }
    public boolean issame(TreeNode tree1,TreeNode tree2){
        if(tree1==null&&tree2==null){
            return true;
        }
        if((tree1==null&&tree2!=null)||(tree1!=null&&tree2==null)){
            return false;
        }
        if(tree1.val==tree2.val){
            return issame(tree1.left,tree2.right)&&issame(tree1.right,tree2.left);
        }else {
            return false;
        }
    }
}

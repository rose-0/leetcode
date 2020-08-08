package leecode.dfs;

import sun.reflect.generics.tree.Tree;

import java.util.Stack;

public class 恢复二叉搜索树_99 {
    TreeNode t1,t2,pre;
    public void recoverTree(TreeNode root) {

        reverseNode(root);//t1 t2 是记录下来两个错误的节点
        int temp = t1.val;
        t1.val=t2.val;
        t2.val=temp;
    }
    public void reverseNode(TreeNode root){
        if(root==null)return;
        reverseNode(root.left);
        if(pre!=null&&pre.val>root.val){
            if(t1==null)t1=pre;//为啥要判断t1为空 3  1  4   2, [3,1,4,null,null,2]（数组是先序） 3和2被交换了
            // 3和1 都不满足 3>1，4>2 t1要找第一次大于的点 t1记录3 t2记录2
            // 因为两个点前后交换，第一个错误节点 是大的 第二个错误节点是 小的
            t2=root;
        }
        pre=root;//pre记录根节点，不断移动
        reverseNode(root.right);
    }
    //自己写的
    TreeNode cur=null;
    TreeNode[] result=new TreeNode[2];
    public void recoverTree2(TreeNode root) {
        findNode(root);
        int temp=result[0].val;
        result[0].val=result[1].val;
        result[1].val=temp;
    }

    public void findNode(TreeNode root){
        if(root==null){
            return;
        }
        findNode(root.left);//这个放在前面，记得是中序

        if(cur==null){
            cur=root;
        }else {
            if(cur.val>root.val){
                result[0]=result[0]==null?cur:result[0];
                result[1]=root;
            }
        }
        cur=root;
        findNode(root.right);
    }



    //非递归 中序 P134
    public void reverseUndp(TreeNode root){
        TreeNode pre=null;
        TreeNode[]err=new TreeNode[2];
        if(root!=null){
            Stack<TreeNode>stack=new Stack<>();
            while (!stack.isEmpty()||root!=null){

                if(root!=null){//不是 if (root.left!=null)
                    stack.push(root);
                    root=root.left;
                }else {
                    root=stack.pop();
                    if(pre!=null&&pre.val>root.val){
                        if(err[0]==null)err[0]=pre;
                        err[1]=root;
                    }
                    pre=root;
                    root=root.right;
                }
            }
        }
    }

}

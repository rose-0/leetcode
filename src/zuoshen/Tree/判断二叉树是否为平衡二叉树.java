package zuoshen.Tree;

import leecode.dfs.TreeNode;

public class 判断二叉树是否为平衡二叉树 {
    public boolean isbalance(treeNode head){
        boolean flag=true;
        getheight(head,1,flag);
        return flag;
    }
    public int getheight(treeNode head,int level,boolean flag){
        if(head==null){
            return level;
        }
        int lh=getheight(head.left,level+1,flag);
        if(!flag){
            return level;
        }
        int rh=getheight(head.right,level+1,flag);
        if(!flag){
            return level;
        }
        if(Math.abs(lh-rh)>1){
            flag=false;
        }
        return Math.max(lh,rh);
    }



    public class returnnode{
        boolean flag;
        int height;
        public returnnode(boolean flag,int height){
            this.flag=flag;
            this.height=height;
        }
    }
    public returnnode isbanlance(treeNode root){
        if(root==null){
            return new returnnode(true,0);
        }
        returnnode left=isbanlance(root.left);
        returnnode right=isbanlance(root.right);
        if(left.flag==false||right.flag==false||Math.abs(left.height-right.height)>1){
            return new returnnode(false,Math.max(left.height,right.height)+1);
        }
        return new returnnode(true,Math.max(left.height,right.height)+1);
    }
}

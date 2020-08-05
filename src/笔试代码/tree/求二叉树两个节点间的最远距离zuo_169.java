package 笔试代码.tree;

public class 求二叉树两个节点间的最远距离zuo_169 {
    public int max_tree(treeNode head){
        int[]record=new int[1];
        return post(head,record);

    }
    public int post(treeNode head,int[]record){
        if(head==null){
            record[0]=0;//树的深度
            return 0;
        }
        int lmax=post(head.left,record);
        int maxleft=record[0];
        int rmax=post(head.right,record);
        int maxright=record[0];
        record[0]=Math.max(maxleft,maxright)+1;
        return Math.max(Math.max(lmax,rmax),maxleft+maxright+1);
    }
}

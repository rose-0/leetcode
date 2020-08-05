package zuoshen.Tree;

public class 找二叉树中的最大搜索二叉子树 {
    public treeNode biggestSubBst(treeNode head){
        int []record=new int[3];
        return posOrder(head,record);
    }
    public treeNode posOrder(treeNode head,int[]record) {
        if(head==null){
            record[0]=0;
            record[1]=Integer.MAX_VALUE;
            record[2]=Integer.MIN_VALUE;
        }
        int value=head.value;
        treeNode left=head.left;
        treeNode right=head.right;
        treeNode lbst=posOrder(left,record);
        int lsize=record[0];
        int lmin=record[1];
        int lmax=record[2];
        treeNode rbst=posOrder(right,record);
        int rsize=record[0];
        int rmin=record[1];
        int rmax=record[2];
        record[1]=Math.min(lmin,value);
        record[2]=Math.max(rmax,value);
        if(lbst==left&&rbst==right&&lmax<value&&value<rmin){
            record[0]=lsize+rsize+1;
            return head;
        }
        record[0]=Math.max(lsize,rsize);
        return lsize>rsize?lbst:rbst;

    }
}

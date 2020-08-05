package zuoshen.Tree;

public class 二叉树节点间的最大距离 {
    public int maxDistance(treeNode head){
        int[]record=new int[1];
        return posOrder(head,record);
    }
    public int posOrder(treeNode head,int[]record){
        if(head==null){
            record[0]=0;
            return 0;
        }
        int lmax=posOrder(head.left,record);
        int maxkeft=record[0];
        int rmax=posOrder(head.right,record);
        int maxright=record[0];
        int curmax=maxkeft+maxright+1;
        record[0]=Math.max(maxkeft,maxright)+1;
        return Math.max(Math.max(lmax,rmax),curmax);
    }
}

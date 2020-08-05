package leecode.dfs;
//https://blog.csdn.net/weixin_43338519/article/details/98084408
public class 完全二叉树的节点个数_222 {
    //labuladong的方法
    public int countNodesbyla(TreeNode root){
        TreeNode left=root;
        TreeNode right=root;
        int hleft=0;
        int hright=0;
        while (left!=null){
            left=left.left;
            hleft++;
        }
        while (right!=null){
            right=right.right;
            hright++;
        }
        if(hleft==hright){
            return (int)Math.pow(2,hleft)-1;
        }
        return 1+countNodesbyla(root.left)+countNodesbyla(root.right);
    }

    public static void main(String[] args) {
        String s="abba";
        System.out.println(s.substring(1,2));
    }


    public int countNodes(TreeNode root) {
        if(root==null){
            return 0;
        }
        return method(root,0,high(root,1));
    }
    public int method(TreeNode root,int l,int h){
        if(l==h){
            return 1;
        }
        if(high(root.right,l+1)==h){
            return (1<<(h-l))+method(root.right,l+1,h);
        }else {
            return (1<<(h-l-1))+method(root.left,l+1,h);
        }
    }
    public int high(TreeNode node,int l){
        while (node!=null){
            l++;
            node=node.left;
        }
        return l-1;
    }
}

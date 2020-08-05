package leecode.dfs;
/*
构造一个二叉树是先构造根节点，再递归构造左右子树，所以是先序遍历
根节点是使用先序数组的第一个节点构造，通过先序的第一个值为key，在中序里面找index，则找到了
左右子树的范围
 */
import java.util.HashMap;

public class 从前序与中序遍历序列构造二叉树_105 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder==null||inorder==null){
            return null;
        }
        HashMap<Integer,Integer>map=new HashMap<>();
        for (int i = 0; i <inorder.length ; i++) {
            map.put(inorder[i],i);
        }
        return pre(preorder,0,preorder.length-1,inorder,0,inorder.length-1,map);
    }
    public  TreeNode pre(int[]preorder,int preStart,int preEnd,int[]inorder,int inStart,int inEnd,HashMap<Integer,Integer>inMap){
        if(preStart>preEnd){
            return null;
        }
        TreeNode head=new TreeNode(preorder[preStart]);//先序第一个节点就是头节点
        int inRoot=inMap.get(preorder[preStart]);//根在中序中的位置，来找左右子树
        //inRoot-inStart 不是prestart！！不然就错了
        int numsLeft=inRoot-inStart;//这里是root-start 所以直接是左子树节点的数量，不需要加1,这个只给先序确定边界使用，只用在了两个地方
        head.left=pre(preorder,preStart+1,preStart+numsLeft,inorder,inStart,inRoot-1,inMap);
        head.right=pre(preorder,preStart+numsLeft+1,preEnd,inorder,inRoot+1,inEnd,inMap);
        return head;
    }
}

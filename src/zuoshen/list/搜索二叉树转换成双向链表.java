package zuoshen.list;
//对比lee 114
public class 搜索二叉树转换成双向链表 {
    public TreeNode_two head=null;//指针，已经转化好链表的尾部，这个head是始终指向已经转换链表的尾部节点
    public TreeNode_two realHead=null;//双向链表的头部
    // 把树的left，right 转为链表的前后指针，所以没有使用链表节点，只使用树节点就可以
    public void transfer(TreeNode_two treenode){//要转化二叉搜索树的头部
        if(treenode==null){//树节点先考虑null，递归终止，对节点单独处理时考虑head为null
            return ;
        }
        transfer(treenode.left);//搜索二叉树，转化为有序链表，所以中序遍历
        if(head==null){
            head=treenode;
            realHead=treenode;
        }else {
            head.right=treenode;
            treenode.left=head;
            head=treenode;
        }
        transfer(treenode.right);
    }
}

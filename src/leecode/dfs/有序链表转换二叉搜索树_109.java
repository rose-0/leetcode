package leecode.dfs;
//对比将搜索二叉树转化为双向链表 左神P75
public class 有序链表转换二叉搜索树_109 {
    public TreeNode sortedListToBST(ListNode head) {
        if(head==null){
            return null;
        }else if(head.next==null){
            return new TreeNode(head.val);
        }
        ListNode slow=head;
        ListNode fast=head;
        ListNode pre=head;//如果初始化slow为head.next,fast为head.next.next则
        // 相当于先循环了一次,这种方式slow会走到中间靠左节点

        while (fast!=null&&fast.next!=null){
            //因为你下面用到fast.next.next如果fast为空，则fast.next报错，
            // 如果fast.next为空，则fast.next.next报错，因为不能访问空指针
            fast=fast.next.next;
            slow=slow.next;
        }
        while (pre.next!=slow){
            pre=pre.next;
        }
        pre.next=null;//这个是把slow前面链表与slow断开
        TreeNode root=new TreeNode(slow.val);
        root.left=sortedListToBST(head);
        root.right=sortedListToBST(slow.next);//局部变量和全局变量
        return root;
    }


}

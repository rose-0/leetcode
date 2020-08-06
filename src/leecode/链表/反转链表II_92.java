package leecode.链表;
//链表问题一定要画图判断，知道临界的时候指针在哪个地方
//反转链表最后两个语句一定不要颠倒
//while终止条件一定知道最后是哪个节点进不去循环
//如：cur.next!=null则next为空的节点进不去循环，即最后一个节点进不去
//cur！=null即空节点进不去，最后一个节点不是空节点，可以进去
public class 反转链表II_92 {
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode fpre=null;
        ListNode tpos=null;
        ListNode cur=head;
        int count=1;
        while (cur!=null){//cur.next!=null则最后一个节点进不去循环，cur！=null则可以对最后一个节点处理
            fpre=count==m-1?cur:fpre;
            tpos=count==n+1?cur:tpos;
            cur=cur.next;
            count++;
        }

        ListNode node1=fpre==null?head:fpre.next;
        ListNode node2=null;
        ListNode pre=tpos;
        int i=0;
        while (node1!=tpos){//tpos节点不进入循环
            node2=node1.next;
            node1.next=pre;
            pre=node1;
            node1=node2;

            System.out.println("i="+(i++)+":"+"pre "+pre.val+" node1 "+node1.val);
        }
//        System.out.println("pre "+pre.val);
//        System.out.println("node1 "+node1.val);
        if(fpre!=null) {
            fpre.next = pre;
            return head;
        }
        return pre;
    }

    //头插
    public static ListNode reverseBetween2(ListNode head, int m, int n){
        ListNode dummy=new ListNode(-1);
        dummy.next=head;
        ListNode pre=dummy;//反转节点的头一个
        /*
        while (m>1){//大于1 !!
            pre=pre.next;
            m--;//这样写的话，下面还会使用m，m的值就变了！
        }
        */
        for(int i=1;i<m;i++){
            pre=pre.next;
        }
        /*
        1->2->3->4->5  m=2 n=4
        pre=1
        reverHead=2;
         */
        ListNode reverHead=pre.next;


        for(int i=m;i<n;i++){
            /*
             1->2->3->4->5
      调整： 1->3->2->4->5
            pre不变 一直指向1
            这个过程是个头插的过程

            pre相当于头插部分的头节点，把节点插到pre的后面
            每次把reverHead的next节点插到pre的后面
            先连后断
            next先和pre.next连上 即next.next=pre.next;

            reverHead始终指向2，第一次把2后面的3移到1后面   1 3 2 4 5 （pre始终指向1）
                               第二次把2后面的4移到1后面   1 4 3 2 5
             */
            System.out.println("i="+i+" "+"reverseBetween2"+reverHead.val+"next"+reverHead.next.val);
            ListNode next=reverHead.next;//next为3,
            reverHead.next=next.next;//2指向4

            //下面这两个不能颠倒 先连后断
            next.next=pre.next;//3指向2
            pre.next=next;//1指向3
        }
        return dummy.next;
    }
    public static void main(String[] args) {
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);
//        head1.next.next.next.next.next = new ListNode(11);
//        head1.next.next.next.next.next.next = new ListNode(13);
        reverseBetween2(head1,2,4);
        while (head1!=null){
            System.out.print(head1.val+" ");
            head1=head1.next;
        }
    }
}

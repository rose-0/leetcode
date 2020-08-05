package leecode.链表;

public class 奇偶链表_328 {
    public static ListNode oddEvenList(ListNode head) {
        if(head==null||head.next==null||head.next.next==null){
            return head;
        }
        ListNode odd=head;
        ListNode ou=head.next;
        while (odd.next!=null&&odd.next.next!=null){
            odd.next=odd.next.next;
            odd=odd.next;
        }
        System.out.println(odd.val);
        while (ou.next!=null&&ou.next.next!=null){//上面奇数把链表的结构给破坏了，所以不能这样找
            ou.next=ou.next.next;
            ou=ou.next;
        }
        System.out.println(ou.val);
        ou.next=null;
        odd.next=ou;
        return head;
    }
    //两个指针就够了 奇偶指针 就够了！！！
    public static ListNode oddEvenList1(ListNode head) {
        if(head==null||head.next==null||head.next.next==null){
            return head;
        }
        ListNode odd=head;
        ListNode node=head.next;//
        ListNode ou=head.next;
        /*
        1：只看while循环，以原链表奇数个还是偶数个分开考虑
        2：如奇数个时，1->2->3->4->5,跳出循环时，1->3->5; 2->4->NULL, 此时odd为5，even为NULL
        3：偶数个时，1->2->3->4->5->6,跳出循环时，1->3->5, 2->4->6,此时odd为5，even为6，
           未给6之后添加NULL，但是6后边本来就是NULL
        4：综上，当奇数个时，将NULL也看做一个节点，则此时也是偶数个节点了，
           那么循环进行结束后odd必然指向倒数第二个节点，even指向最后一个节点
        5：若能保证一定有偶数个节点，那么循环退出条件就是even指向了最后一个节点，
           由第4点可得，两种情况为even == NULL 或者 even->next == NULL
         */
        //偶指针总比奇指针快
        while (ou!=null&&ou.next!=null){//一定先判断奇数的next是不是空,偶指针此时是奇数的next
            odd.next=ou.next;
            odd=odd.next;
            ou.next=odd.next;
            ou=ou.next;
        }
        //最后跳出的时候相当于奇，偶都指向各自链表的尾部节点，
        odd.next=node;
        return head;
    }
    public static ListNode oddEvenList3(ListNode head){
        ListNode oddhead=head;
        ListNode next=head.next;
        ListNode ouhead=head.next;
        /*
        while (ouhead.next!=null)//最后一次循环进入的节点是倒数第二个节点，即退出循环时ouhead指向最后一个节点
         while (ouhead!=null)//最后一次循环进入的节点是尾节点，最后退出循环时ouhead指向空节点
         */
        while (ouhead.next!=null){
            oddhead.next=ouhead.next;
            oddhead=oddhead.next;//找到next就移动！
            if(oddhead.next!=null) {
                ouhead.next = oddhead.next;
            }
//            else {//这儿一定要写，否则偶节点next连接到了奇节点的尾部，既最后一个节点
//                break;
//            }
            ouhead=ouhead.next;
        }
        oddhead.next=next;
        ouhead.next=null;
        return head;
    }
    public static void main(String[] args) {
        ListNode head=new ListNode(1);
        head.next=new ListNode(2);
        head.next.next=new ListNode(3);
        head.next.next.next=new ListNode(4);
        head.next.next.next.next=new ListNode(5);
        oddEvenList3(head);
        while (head!=null){
            System.out.println(head.val);
            head=head.next;
        }
    }
}

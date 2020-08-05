package zuoshen.list;

public class 约瑟夫环问题 {
    public Node delete_huan(Node head,int m){
        Node last=head;
        while (last.next!=head){
            last=last.next;
        }
        int count=0;
        while (last!=head){
            if(++count==m){
                last.next=head.next;
                count=0;
            }else {
                last=last.next;
            }
                head=last.next;
        }
        return head;
    }
}

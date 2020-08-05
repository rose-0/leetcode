package zuoshen.list;

import java.util.HashMap;

public class 复制含有随机指针节点的链表 {
    public void copy_list_map(RanNode head){
        HashMap<RanNode,RanNode>map=new HashMap<>();
        RanNode cur=head;
        while (cur!=null){
            map.put(cur,new RanNode(cur.value));
            cur=cur.next;
        }
        cur=head;
        while (cur!=null){
            map.get(cur).next=map.get(cur.next);
            map.get(cur).rand=map.get(cur.rand);
            cur=cur.next;
        }
    }
    public RanNode copy_list(RanNode head){
        RanNode cur=head;
        RanNode next=null;
        while (cur!=null){
            next=cur.next;
            cur.next=new RanNode(cur.value);
            cur.next.next=next;
            cur=next;
        }
        cur=head;
        RanNode curCopy=null;
        while (cur!=null){
            next=cur.next.next;
            curCopy=cur.next;
            curCopy.rand=cur.rand==null?null:cur.rand.next;
            cur=next;
        }
        cur=head;
        RanNode res=head.next;
        while (cur!=null){
            next=cur.next.next;
            curCopy=cur.next;
            cur.next=next;
            curCopy.next=next==null?null:next.next;
            cur=next;
        }
        return res;
    }








    public RanNode copy(RanNode head){
        if(head==null){
            return null;
        }
        RanNode cur=head;
        RanNode next=null;
        while (cur!=null){
            next=cur.next;
            cur.next=new RanNode(cur.value);
            cur.next.next=next;
            cur=next;
        }
        cur=head;
        next=null;
        RanNode curcopy=null;
        while (cur!=null){
            next=cur.next.next;
            curcopy=cur.next;
            curcopy.rand=cur.rand==null?null:cur.rand.next;
            cur=next;
        }
        cur=head;
        RanNode newhead=cur.next;
        next=null;
        RanNode nextcur=head.next;
        while (cur!=null){
            next=cur.next.next;
            curcopy=cur.next;
            cur.next=next;
            curcopy.next=next==null?null:next.next;
            cur=next;

        }
        return newhead;
    }
}

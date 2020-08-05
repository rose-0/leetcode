package zuoshen.list;

public class 单向链表按某值划分 {
    public Node partition(Node head,int num){
        Node cur=head;
        int length=0;
        while (cur!=null){
            length++;
            cur=cur.next;
        }
        cur=head;
        Node[]data=new Node[length];
        for (int i = 0; i <data.length ; i++) {
            data[i]=cur;
            cur=cur.next;
        }
        arrpartition(data,num);
        int i;
        for ( i = 1; i <data.length ; i++) {
            data[i-1].next=data[i];
        }
        data[i-1].next=null;
        return data[0];
    }
    public void arrpartition(Node []arr,int num){
        int small=-1;
        int big=arr.length;
        int index=0;
        while (index!=big){
            if(arr[index].value<num){
                swap(arr,++small,index++);
            }else if(arr[index].value==num){
                index++;
            }else {
                swap(arr,--big,index);
            }
        }
    }
    public void swap(Node[] arr,int i,int j){
        Node temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

}

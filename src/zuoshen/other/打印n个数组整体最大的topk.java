package zuoshen.other;

import java.util.LinkedList;
import java.util.List;

//堆顶堆结构
//每个数组拿出自己最大的元素放在堆里面，所有数组的最大元素构成堆后，用堆对它们再进行排序选出最大的
public class 打印n个数组整体最大的topk {
    public static class heapNode{
        public int value;
        public int arrNum;//来自哪个数组
        public int index;
        public heapNode(int value,int arrNum,int index){
            this.arrNum=arrNum;
            this.index=index;
            this.value=value;
        }
    }
    public static void printTopk(int[][]matrix,int topk){

        /*
        设置堆的大小，初始化堆
         */
        int heapsize=matrix.length;//堆的大小即是数组的数量
        heapNode[]heap=new heapNode[heapsize];//堆里面存放的是堆节点
        for (int i = 0; i <heapsize ; i++) {//先初始化heapNode节点，再把它插入堆中
            int index=matrix[i].length-1;//index来源数组的下标
            heap[i]=new heapNode(matrix[i][index],i,index);//matrix[i][index]为对应的值
            heapInsert(heap,i);
        }
        for (int i = 0; i <topk ; i++) {//这是只输出topk个元素，和堆排序不同
            if(heapsize==0){
                break;
            }
            System.out.print(heap[0].value+" ");
            if(heap[0].index!=0){//将这个元素所在数组前面的元素放入堆里面,把堆顶元素的值更新即可
                heap[0].value=matrix[heap[0].arrNum][--heap[0].index];
            }else {
                swap(heap,0,--heapsize);//堆的大小减一
            }
            heapify(heap,0,heapsize);
        }
    }
    public static void heapInsert(heapNode[]heap,int index){
        int parent=(index-1)/2;
        while (heap[index].value>heap[parent].value){
            swap(heap,index,parent);
            index=parent;
            parent=(index-1)/2;
        }
    }
    public static void heapify(heapNode[]heap,int index,int heapsize){
        int left=index*2+1;
        while (left<heapsize){
            int largest=left+1<heapsize&&heap[left+1].value>heap[left].value?left+1:left;
            largest=heap[largest].value>heap[index].value?largest:index;
            if(largest==index){
                break;
            }
            swap(heap,index,largest);
            index=largest;
            left=index*2+1;
        }
    }
    public static void swap(heapNode[]heap,int i,int j){
        heapNode temp=heap[i];
        heap[i]=heap[j];
        heap[j]=temp;
    }

    public static void main(String[] args) {
        int[][]matrix={
                {219,405,538,845,971},
                {148,558},
                {52,99,348,691}
        };
        printTopk(matrix,5);
    }
}

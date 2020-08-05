package Sort;

public class heapSort {
    public static void heapsort(int[]arr){
        if(arr==null||arr.length<2){
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            heapSwim(arr,i);//变成大根堆，堆顶元素最大
        }
        int end=arr.length-1;//数组元素全部插入后变成堆，此时堆的长度和数组长度相同

        swap(arr,0,end);//堆顶元素为最大值元素，放在
        // 数组末尾后，因为现在堆顶可能已经不是最大，所以下面进行调整
        while (end>0){
            heapsink(arr,0,end);//0位置元素下沉，此时0位置元素不是最大，需要调整
            end--;
            //此时0位置又是最大值，放在末尾
            swap(arr,0,end);//最大值放到数组末尾
            //最终形成一个升序数组
        }
    }
    public static void heapSwim(int[]arr,int index){//heapInsert   上浮操作， 第二个参数是下标
        /*
                0
              1   2
          2的根节点是0，（2-1）/2
         */
        while (arr[index]>arr[(index-1)/2]){//头结点(index-1)/2  子节点>头节点 上浮
            //传入的index是0的时候，(-1)/2=0;
            swap(arr,index,(index-1)/2);//交换的时候，传入的参数是下标；
            index=(index-1)/2;
        }
    }
    public static void heapsink(int[]arr,int index,int size){//heapify  这个size是堆的size，而不是数组的长度
        //下沉操作：
        int left=index*2+1;
        while (left<size){
            //largest找到子节点哪个大
            int largest=left;
            if(left+1<size){//存在右孩子
                largest=arr[left+1]>arr[left]?left+1:left;
            }

            //当前节点最大，不下沉
            if(arr[index]>arr[largest]){
                break;
            }

            swap(arr,largest,index);
            //下沉
            index=largest;
            left=index*2+1;//记得更新这个！
        }
    }
    public static void swap(int[]arr,int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

    public static void main(String[] args) {
        int[]arr={1,3,6,5,2};
        System.out.println((0-3)/2);
        heapsort(arr);
        for (int i = 0; i <arr.length ; i++) {
            System.out.print(arr[i]);
        }
    }
}

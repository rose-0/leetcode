package Sort;

public class 堆排序_labuladong {
    /*
    索引呈递增关系
            0                                         1
          1   2                                     2    3
  2的根节点是0，（2-1）/2 2/2-1也可以          3的根节点是1，（3）/2 即可
   0位置不放元素 对应数组下标    0位置有元素时，对应的下标
    parent      x/2                     (x-1)/2
    root        x                       x
    root.left   x*2                     x*2+1
    root.right  x*2+1                   x*2+2
     */
    //这个是实现优先级队列
    //存储元素的数组
    private int[]pq;
    //当前优先级队列中的元素
    private int n=0;

    public 堆排序_labuladong(int cap){
        pq=new int[cap+1];//0位置不放元素，所以多申请一个空间
    }
    public int max(){//返回最大元素
        return pq[1];
    }
    public void insert(int e){

    }
    //上浮
    private void swim(int k){
        //k代表第k个元素
        //k>1表示k不在堆顶，且第k个元素比父节点大
        while (k>1&&pq[k/2]<pq[k]){
            swap(pq[k/2],pq[k]);
            k=k/2;
        }
    }
    private void sink(int k){
        //k*2<=n 不在堆底
        while (k*2<=n){
            int older=pq[k*2];//左边
            if(k*2+1<=n&&older<pq[k*2+1]){//右边节点存在，比一下大小
                older=pq[k*2+1];
            }
            if(pq[k]>older){//节点k比两个孩子都大，就不必下沉了
                break;
            }
            swap(older,pq[k]);

        }
    }
    private void swap(int i,int j){
        int temp=i;
        i=j;
        j=temp;
    }
}

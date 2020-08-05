package 笔试代码.array;

import zuoshen.输入输出练习.E;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class 合并k个排序数组lin_486 {
    public int[] mergekSortedArrays(int[][] arrays) {
        // write your code here
        if(arrays==null||arrays.length==0){
            return null;
        }
        return help(arrays,0,arrays.length-1);
    }
    /*
    归并排序没有返回值，而且是原址排序
    合并k个有序数组是有返回值的，而且对原来的结果做了改变
    所以 if(l+1==r){//不需要写这个也是对的
            return merge(arrays[l],arrays[r]);
        }
     */
    public int[]help(int[][]arrays,int l,int r){
        if(l==r){
            return arrays[l];
        }
        if(l+1==r){//与归并排序不同的地方，这段代码注释到也是对的
            // ，如果l和r差1，则mid=l，mid+1=r，最后相当于合并两个相同的数组
            return merge(arrays[l],arrays[r]);
        }
        int mid=(l+r)/2;
        int[]left=help(arrays,l,mid);
        int[]right=help(arrays,mid+1,r);//这里也是mid+1 ！！
        return merge(left,right);
    }
    public static int[]merge(int[]num1,int[]num2){//与归并排序不同
        //归并排序是对一个无序数组归并排序，这个是对两个有序数组合并为一个有序
        //归并排序也是两个指针指向一个数组，一个从left开始，一个从mid+1开始，这个题是两个指针都从0开始
        //归并排序最后要把中间数组结果转到原来的数组，这个题不需要
        int m=num1.length;
        int n=num2.length;
        int[]num=new int[m+n];
        int p=m+n;
        m--;
        n--;
        p--;
        //从后向前！！！不是从前往后
        //从前往后好像也可以，归并排序就是从前往后
        while (m>=0&&n>=0){
            if(num1[m]>num2[n]){
                num[p--]=num1[m--];
            }else {
                num[p--]=num2[n--];
            }
        }
        while (n>=0){//链表是if
            num[p--]=num2[n--];
        }
        while (m>=0){
            num[p--]=num1[m--];
        }
        return num;
    }


    //使用优先级队列实现，这个和左神的堆顶堆类似 打印n个数组整体最大的topk
    class Element{
        public int val,row,col;
        public Element(int row,int col,int val){//要记录索引
            this.col=col;
            this.row=row;
            this.val=val;
        }
    }
    private Comparator<Element> comparator=new Comparator<Element>() {
        @Override
        public int compare(Element o1, Element o2) {
            return o1.val-o2.val;
        }
    };

    public int[] mergeKSortArray(int[][]arr){
        if(arr==null){
            return new int[0];
        }
        int sumLen=0;
        Queue<Element>queue=new PriorityQueue<>(arr.length,comparator);
        for (int i = 0; i <arr.length ; i++) {
            if(arr[i].length>0){
                Element e = new Element(i,0,arr[i][0]);
                queue.add(e);
                sumLen=sumLen+arr[i].length;//记录二维转化为一维数组的总长度
            }
        }
        int[]res=new int[sumLen];//存放结果的一维数组
        int index=0;
        while (!queue.isEmpty()){
            Element e = queue.poll();//弹出最小值
            res[index++]=e.val;
            //e 当前行的后面一个节点加入队列
            if(e.col+1<arr[e.row].length){
                queue.add(new Element(e.row,e.col+1,arr[e.row][e.col+1]));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[]num1={1,5,8};
        int[]num2={3,6};
        int[]num=merge(num1,num2);
        for (int i = 0; i <num.length ; i++) {
            System.out.println(num[i]);
        }
    }
}

package leecode.Array;

public class 数组的相对排序_1122 {
    //arr1 按照 arr2 顺序进行排序
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[]m = new int[1001];//大小设置为数的范围，才可以当作map来用，值是其出现的次数

        for (int i = 0; i <arr1.length ; i++) {
            m[arr1[i]]++;//记录arr1的数出现的次数
        }

        int[] ref= new int[arr1.length];//存放排序后的arr1

        int cnt=0;
        for (int i = 0; i <arr2.length ; i++) {
            while (m[arr2[i]]>0){//不是>=0!! m[arr[i]]>0 arr2中arr1的数
                ref[cnt++]=arr2[i];
                m[arr2[i]]--;
            }
        }
        //这里可以保证升序排序！！
        //使用map的话不好保证升序排序
        for (int i = 0; i <1001 ; i++) {
            while (m[i]>0){
                ref[cnt++]=i;
                m[i]--;
            }
        }
        return ref;
    }
}

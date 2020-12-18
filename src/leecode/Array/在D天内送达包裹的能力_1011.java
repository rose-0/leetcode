package leecode.Array;

public class 在D天内送达包裹的能力_1011 {

    public int shipWithinDays(int[] weights, int D) {
        int left=0;
        int right=0;
        for (int i = 0; i <weights.length ; i++) {
            left=left<weights[i]?left:weights[i];
            right+=weights[i];//注意这个right
        }
        while (left<right){
            int mid=(left+right)/2;
            if(calship(weights,mid,D)){
                right=mid;
            }else{
                left=mid+1;
            }
        }
        return left;
    }
    public boolean calship(int[]weight,int num,int D){
        int cur=num;
        for (int i = 0; i <weight.length ; i++) {
            if(weight[i]>num){
                return false;
            }
            if(cur<weight[i]){
                cur=num;
                D--;
            }
            cur-=weight[i];
        }
        return D>0;
    }
}

package leecode.Array;

public class 公交站间的距离_1184 {
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        if(start>destination){
            int temp=start;
            start=destination;
            destination=temp;
        }
        int sum=0;
        int res=0;
        for (int i = 0; i <distance.length-1 ; i++) {
            sum+=distance[i];//distance[i]表示 i到 i+1的距离
            if(i>=start&&i<=destination-1){//注意这里 <=
                res+=distance[i];
            }
        }
        return Math.min(sum-res,res);
    }
}

package zuoshen.DP;

public class 换钱的方法数 {
    public int process(int[]arr,int aim,int index,int cur){
        int method=0;
        if(index==arr.length){
            if(cur==0){
                  method=1;
                  return method;
            }else {
                  method=0;
                  return method;
            }
        }
//        int method=0;
        for (int i = 0; index<arr.length&&(i*arr[index] <=cur) ; i++) {
             method+=process(arr,aim,index+1,aim-i*arr[index]);
        }
        return method;
    }
    public static int process_method(int[]arr,int aim,int index,int cur){
        int res=0;
        if(index==arr.length){
            res=cur==0?1:0;
        }else {
            for (int i = 0; i*arr[index] <=cur ; i++) {
                res+=process_method(arr,aim,index+1,aim-arr[index]*i);
            }
        }
        return res;
    }
    public static int method_dp(int[][]map,int aim,int cur,int index,int[]arr){
        int res=0;
        if(index==arr.length){
             res=cur==0?1:0;
        }else {
            for (int i = 0; i*arr[index] <=cur ; i++) {
                int value=map[index+1][cur-arr[index]*i];
                if(value!=0){
                    res+=value==-1?0:value;
                }else {
                    res+=method_dp(map,aim,aim-arr[index]*i,index+1,arr);
                }
                map[index][cur]=res==0?-1:res;
            }
//            map[index][cur]=res==0?-1:res;
        }
//        map[index][cur]=res==0?-1:res;
        return res;
    }
    public static int method_dp2(int []arr,int aim){
        int[][]dp=new int[arr.length+1][aim+1];
        int row=dp.length;
        int col=dp[0].length;
        for(int i=row-1,j=0;j<col;j++){
            dp[i][j]=j==0?1:0;
        }
        for(int i=row-2;i>=0;i--){
            for(int j=col-1;j>=0;j--){
                for(int z=0;arr[i]*z<=j;z++)
                dp[i][j]+=dp[i+1][j-arr[i]*z];
            }
        }
        return dp[0][col-1];
    }
    public static int method_dp3(int[]arr,int aim){
        int[][]dp=new int[arr.length][aim+1];
        for (int i = 0; i <arr.length ; i++) {
            dp[i][0]=1;
        }
        for (int i = 1; i <=aim ; i++) {
            dp[0][i]=i%arr[0]==0?1:0;
        }
        for (int i = 1; i <dp.length ; i++) {
            for (int j = 1; j <dp[0].length ; j++) {
                if(j-arr[i]>=0) {//这里要写成 >= 0 而不是 >0,>0就错了
                    dp[i][j] = dp[i - 1][j] + dp[i][j - arr[i]];
                }else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        printdp(dp);
        return dp[arr.length-1][aim];
    }
    // 都多一行一列
    public static int method_dp4(int[]arr,int aim){
        int[][]dp=new int[arr.length+1][aim+1];
        for (int i = 0; i <arr.length ; i++) {
            dp[i][0]=1;
        }
//        for (int i = 1; i <=aim ; i++) {
//            dp[0][i]=i%arr[0]==0?1:0;
//        }
        for (int i = 1; i <dp.length ; i++) {
            for (int j = 1; j <dp[0].length ; j++) {
                if(j-arr[i-1]>=0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - arr[i-1]];
                }else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        printdp(dp);
        return dp[arr.length][aim];
    }
    public static void printdp(int[][]dp){
        for (int i = 0; i <dp.length ; i++) {
            for (int j = 0; j <dp[0].length ; j++) {
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[]arr={1,5,10,25};
        System.out.println(method_dp3(arr,5));
        System.out.println("-------");
        System.out.println(method_dp4(arr,5));
    }
}

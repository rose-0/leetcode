package zuoshen.DP;
//背包问题
public class 数组选择数累加为aim {
    public static boolean find_sum(int[]arr,int aim,int index,int cur){
        if(index==arr.length){
            return cur==aim?true:false;
        }
        return find_sum(arr,aim,index+1,cur)||find_sum(arr,aim,index+1,cur+arr[index]);
    }
//    public static boolean find_sum_baoli(int[]arr,int aim){
//
//    }
    public static boolean find_sum_dp2(int[]arr,int aim){
        int sum=0;
        for (int i = 0; i <arr.length ; i++) {
            sum+=arr[i];
        }
        int m=arr.length+1;
        int n=aim+1;//申请的时候以aim为列！不是sum,所以不需要计算sum
        boolean[][]dp=new boolean[m][n];
        for (int i = 0; i <n ; i++) {
            dp[m-1][i]=i==aim?true:false;//初始化最后一行
        }
        for (int i = m-2; i >=0 ; i--) {
            for (int j = 0; j <n ; j++) {
                if(j+arr[i]>=n){
                    dp[i][j]=dp[i+1][j];
                }else {//这儿确实是应该加arr[i]而不是arr[i-1]
                    /*
                    arr={3,2,5}
                    dp:
                    3 0
                    2 1(选3或者不选3)
                    5 2
                      3(选5或者不选5)
                     */
                    dp[i][j]=dp[i+1][j]||dp[i+1][j+arr[i]];
                }
            }
        }
        return dp[0][0];
    }









    public static boolean find_sum_dp(int []arr,int aim){
        int sum=0;
        for (int i = 0; i <arr.length ; i++) {
            sum+=arr[i];
        }
        int length=arr.length;
        boolean[][]dp=new boolean[length+1][sum+1];
        int row=dp.length;
        int col=dp[0].length;
        for(int i=0;i<dp.length;i++){
            for (int j = 0; j <dp[0].length ; j++) {
                if(j==aim){
                    dp[i][j]=true;
                }else {
                    dp[i][j]=false;
                }
            }
        }
        for (int i = row-2; i >=0 ; i--) {
            for (int j = col-1; j >=0&&(j+arr[i]<col) ; j--) {
                dp[i][j]=dp[i+1][j]||dp[i+1][j+arr[i]];
            }
        }
        return dp[0][0];
    }

    public static  boolean find_sum_dp_one(int[]arr,int aim){//一维dp看看可不可以
        boolean[]dp=new boolean[aim+1];
        dp[0]=true;
        for (int i = 1; i <dp.length ; i++) {
            for (int j = 0; j <arr.length ; j++) {
                if(i-arr[j]>=0){
                    dp[i]=dp[i-arr[j]];
                }
            }
        }
        return dp[aim];
    }

    public static void main(String[] args) {
        int[]arr={3,5};
        System.out.println(find_sum(arr,11,0,0));
        System.out.println(find_sum_dp(arr,11));
        System.out.println(find_sum_dp2(arr,11));
        System.out.println(find_sum_dp_one(arr,11));
    }
}

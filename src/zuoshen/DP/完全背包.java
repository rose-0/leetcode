package zuoshen.DP;

public class 完全背包 {
    public static int method_re(int[]v,int[]w,int c,int index){
        int result=0;
        if(c==0||index==0){
            result=0;
        }else if(c<w[index]){
            result=method_re(v,w,c,index-1);
        }else {
            int temp1=method_re(v,w,c,index-1);
            for (int i = 0; i *w[index]<=c ; i++) {
                int temp2=method_re(v,w,c-i*w[index],index-1)+i*v[index];
                result=Math.max(temp1,temp2);
//                if(result<temp2){
//                    result=temp2;
//                }
            }

        }
        return result;
    }
    public static int method_map(int[]w,int[]v,int index,int c){
        int result=0;
        Integer[][]map=new Integer[w.length][c+1];
        if(map[index][c]!=null){
            result=map[index][c];
            return result;
        }
        int row=map.length;
        int col=map[0].length;
        if(index==0||c==0){
            result=0;
        }
        else if(c<w[index]){
            result=method_map(w,v,index-1,c);
        }
        else {
            for (int i = 0; i*w[index] <=c ; i++) {
                int temp=method_map(w,v,index-1,c-i*w[index])+i*v[index];
                if(temp>result){
                    result=temp;
                }
            }
            map[index][c]=result;
        }
//        for (int i = 0; i <row ; i++) {
//            for (int j = 0; j <col ; j++) {
//                System.out.print(map[i][j]+" ");
//            }
//            System.out.println();
//        }
        return result;
    }
    public static  int method_dp(int[]w,int[]v,int index,int c){
        int[][]dp=new int[index+1][c+1];
        int row=dp.length;
        int col=dp[0].length;
        for (int i = 1; i < row; i++) {
            for (int j = 1; j <col ; j++) {
                for (int k = 0; k*w[i] <=j ; k++) {
                    int temp=dp[i-1][j-k*w[i]]+k*v[i];
                    if(dp[i][j]<temp){
                        dp[i][j]=temp;
                    }
                }
            }
        }
        for (int i = 0; i <row ; i++) {
            for (int j = 0; j <col ; j++) {
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
        return dp[row-1][col-1];
    }
    public static void main(String[] args) {
        int[]v={0,6,5,10,2,16,8};
        int[]w={0,3,2,5,1,6,4};
        int c=10;
//        System.out.println(method_re(v,w,c,6));
        System.out.println(method_map(w,v,6,10));
        System.out.println(method_dp(w,v,6,10));
    }
//    public static int method_

}

package zuoshen.DP;

public class n皇后 {
    public int method_num(int[]record,int i,int n){
        if(i==n){
            return 1;
        }
        int res=0;
        for (int j = 0; j <n ; j++) {
            if(isValid(record,i,j)){    // 第i行棋子放在第j列
                record[i]=j;
                res+=method_num(record,i+1,n);
            }
        }
        return res;
    }
    public boolean isValid(int[]record,int i,int j){//record[k]是列坐标，k是行坐标
        for (int k = 0; k <i ; k++) { //0到i行检测冲突
            if(record[k]==j||Math.abs(i-k)==Math.abs(record[k]-j)){
                return false;
            }
        }
        return true;
    }
}

package zuoshen.DP;

public class 多重背包 {
    public static int method(int[]w,int[]v,int[]m,int c,int index){
        int result=0;
        if(c==0||index==0){
            result=0;
        }else if(c<w[index]){
            result=method(w,v,m,c,index-1);
        }else {
            for (int i = 0; i <m[index]&&i*w[index]<c ; i++) {
                int temp=method(w,v,m,c-i*w[index],index-1)+i*v[index];
                if(temp>result){
                    result=temp;
                }
            }
        }
        return result;
    }
}

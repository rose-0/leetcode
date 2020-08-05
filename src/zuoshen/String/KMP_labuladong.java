package zuoshen.String;
/*
txt  C A B A A B A B A C
pat  A B A B C
 */
public class KMP_labuladong {
    private int [][]dp;//dp也要声明为成员
    private String pat;
    public KMP_labuladong(String pat){
        this.pat=pat;
        int M = pat.length();
        // dp[j][c]=next 当前状态是j，如果遇到了 字符c，应该转移到next
        // dp[当前状态][c]=下个状态
        dp=new int[M][256];//最后一个状态不用存，所以长度是M
        dp[0][pat.charAt(0)]=1;//base case

        int X=0;//影子状态初始为0

        for (int j = 1; j <M ; j++) {//从1开始
            for (int c = 0; c <256 ; c++) {
                if(pat.charAt(j)==c){
                    dp[j][c]=j+1;
                }else {
                    dp[j][c]=dp[X][c];//状态j遇到字符c时不能前进，找前面 状态X遇到状态c时候 的前进状态
                }
            }
            X=dp[X][pat.charAt(j)];//更新在影子状态 遇到j时候的值
        }
    }

    public int search(String txt){
        int M=pat.length();
        int N=txt.length();
        //pat的初始态为0
        int j=0;
        for (int i = 0; i <N ; i++) {
            //计算pat的下一个状态
            j=dp[j][txt.charAt(i)];
            if(j==M){
                return i-M+1;
            }
        }
        return -1;
    }
}

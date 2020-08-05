package leecode.dfs;
//左神书175页
public class 不同的二叉搜索树_96 {
    //下面有一种非dp，采用递归做
    public static int numTrees(int n) {
        if(n<2){
            return 1;
        }
        int[]dp=new int[n+1];
        dp[0]=1;
        for (int i = 1; i <n+1 ; i++) {//num[i]表示i个节点的树的个数
            for (int j = 1; j <i+1 ; j++) {//计算总共i个节点时，以j为头结点，
                // 左右子树可能数再加起来，左子树num[j-1] 右子树[i-j]
                dp[i]+=dp[j-1]*dp[i-j];//一定是相加
            }

        }
        return dp[n];
    }
    public static int num_tree(int n){
        if(n==0){
            return 1;
        }
        int[]num=new int[n+1];
        num[0]=1;//不要漏掉这个
        for (int i = 1; i <num.length ; i++) {
            for (int j = 1; j <i+1 ; j++) {//以i为头节点时
                //
                num[i]+=num[j-1]*num[i-j];
            }
        }
        return num[n];
    }



    public static int method_tree(int n){
        if(n==0){
            return 1;
        }
        //采用递归
        int[] num=new int[n+1];
        for (int i = 1; i <=n ; i++) {
            for (int j = 1; j <=i ; j++) {
                num[i]+=method_tree(j-1)*method_tree(i-j);
            }
        }
        return num[n];
    }
    public static void main(String[] args) {
        System.out.println(method_tree(5));
        System.out.println(numTrees(5));
        System.out.println(num_tree(5));
    }
}

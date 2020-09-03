package leecode.other;
//对比石子游戏877
public class 预测赢家_486 {
    public boolean PredictTheWinner(int[] nums) {
        return false;
    }
    //https://leetcode-cn.com/problems/predict-the-winner/solution/chao-duan-zhe-ke-neng-shi-zui-jie-jin-zheng-chang-/
    public boolean dfs1(int[]nums,int left,int right,int ascore,int bscore,boolean flag){
        if(left>right){
            return ascore>=bscore;
        }
        if(flag){//a选牌
            // a左右一种胜出即可
            return dfs1(nums,left+1,right,ascore+nums[left],bscore,false)||
                    dfs1(nums,left,right-1,ascore+nums[right],bscore,false);
        }else {
            //b左右都要输
            return dfs1(nums,left+1,right,ascore,bscore+nums[left],true)&&
                    dfs1(nums,left,right-1,ascore,bscore+nums[right],true);
        }
    }

    //https://leetcode-cn.com/problems/predict-the-winner/solution/python-minimax-ji-yi-hua-sou-suo-by-alienjiren/
    public boolean PredictTheWinnerfun(int[] nums) {
        int meScore=fun(nums,0,nums.length-1);//我可以得到的最大分数
        int sum=0;
        for (int i = 0; i <nums.length ; i++) {
            sum+=nums[i];
        }
        return meScore>=sum-meScore;//看我方能得到的最大分数是否超过对方能得到的最大分数
    }
    public int fun(int[]num,int i,int j){
        if(i>j){//不要忘记这个
            return 0;
        }
        if(i==j){
            return num[i];
        }
        //如果我们先选了左边i    对手一定会选择一个让我们总和最小的数
        //对手选择范围 [i+1,j]   fun(num,i+2,j)表示 对手选择i+1，自己的只能选[i+2,j]
        //                     fun(num,i+1,j-1）表示 对手选择j，自己的分数
        int left=num[i]+Math.min(fun(num,i+2,j),fun(num,i+1,j-1));
        int right=num[j]+Math.min(fun(num,i+1,j-1),fun(num,i,j-2));
        int MaxScore=Math.max(left,right);
        return MaxScore;
    }

    //改造为记忆化的递归,i和j是变量 所以是二维的dp
    //https://leetcode-cn.com/problems/predict-the-winner/solution/ji-yi-hua-di-gui-dong-tai-gui-hua-java-by-liweiwei/
    public int fun(int[]num,int i,int j,int[][]memo){
        if(i>j){//不要忘记这个
            return 0;
        }
        if(memo[i][j]!=Integer.MAX_VALUE){//memo要初始化为最大值
            return memo[i][j];
        }
        if(i==j){
            return num[i];
        }
        /*
        int left=num[i]+Math.min(fun(num,i+2,j),fun(num,i+1,j-1));
        int right=num[j]+Math.min(fun(num,i+1,j-1),fun(num,i,j-2));
        int MaxScore=Math.max(left,right);
         */
        //或者计算净胜分,这样感觉更好转化为dp
        int left=num[i]-fun(num,i+1,j,memo);
        int right=num[j]-fun(num,i,j-1,memo);
        int MaxScore=Math.max(left,right);
        memo[i][j]=MaxScore;
        return MaxScore;
    }

    //dp
    //https://leetcode-cn.com/problems/predict-the-winner/solution/ji-yi-hua-di-gui-dong-tai-gui-hua-java-by-liweiwei/
    //评论对dp的解释
    //甲乙比赛，甲先手面对区间[i...j]时，dp[i][j]表示甲对乙的净胜分。
    //最终求的就是，甲先手面对区间[0...n-1]时，甲对乙的净胜分dp[0][n-1]是否>=0。
    public boolean PredictTheWinnerdp(int[] nums) {
        int len = nums.length;
        int[][] dp = new int[len][len];

        for (int i = 0; i < len; i++) {
            dp[i][i] = nums[i];
        }

        //对角线遍历技巧
        for (int i = len - 2; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        return dp[0][len - 1] >= 0;




    }
}

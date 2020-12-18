package leecode.DP;

public class 多米诺和托米诺平铺_790 {
    /*
    https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-790-domino-and-tromino-tiling/
    可以看花花酱的视频讲解，十分清楚
    当只有一种瓷砖时的dp => 有两种瓷砖时的dp

    两个瓷砖:
    铺满i个col，且没有突出
    dp[i][0] = dp[i-1][0](加一个竖向)+dp[i-2][0](加两个横向)+dp[i-1][1](加一个L)+dp[i-1][2](加一个L)
    铺满i个col，且上面突出
    dp[i][1] = dp[i-2][0]+dp[i-1][2](下面突出+一个横条)
    铺满i个col，且下面突出
    dp[i][2] = dp[i-2][0]+dp[i-1][1]

    发现dp[i][1]和dp[i][2]是对称的，所以可以直接把2改为1
     */
    public int numTilings(int N) {
        int mod=1000000007;
        long[][]dp=new long[N+1][3];//改为long就通过了，int过不了
        dp[0][0]=1;
        dp[1][0]=1;
        for (int i = 2; i <=N ; i++) {
            dp[i][0]=(dp[i-1][0]+dp[i-2][0]+dp[i-1][1]+dp[i-1][2])%mod;
            dp[i][1]=(dp[i-2][0]+dp[i-1][2])%mod;
            dp[i][2]=(dp[i-2][0]+dp[i-1][1])%mod;
        }
        return (int) dp[N][0];
    }
}

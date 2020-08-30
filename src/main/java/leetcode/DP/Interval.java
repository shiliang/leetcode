package leetcode.DP;

/*
    区间dp
 */
public class Interval {
    //no.1000合并石头的最低成本
    public int mergeStones(int[] stones, int K) {
        int n = stones.length;
        int[][][] dp = new int[n + 1][n + 1][K + 1]; //dp[i][j][k]把区间i到j合并成k堆的最小代价
        int[] sum = new int[n + 1]; //前缀和数组
        if ((n - 1) % (K - 1) != 0) return -1;
        if (n < 2) return 0;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k <= K; k++) {
                    dp[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + stones[i - 1];
            dp[i][i][1] = 0;
        }


        for (int len = 2; len <= n; len++) { //区间长度
            for (int i = 1; i <= n - len + 1; i++) { //区间左边端点
                int j = i + len - 1;  //区间右边端点
                for (int k = 2; k <= K; k++) {  //通过迭代算出dp[i][j][K]的最小值
                    for (int p = i; p < j; p++) { //分隔成1，k-1
                        dp[i][j][k] = Math.min(dp[i][j][k], dp[i][p][k - 1] + dp[p + 1][j][1]);
                    }
                }
                if (dp[i][j][K] < Integer.MAX_VALUE) {
                    dp[i][j][1] = dp[i][j][K] + sum[j] - sum[i - 1];
                }
            }

        }
        return dp[1][n][1];
    }
}

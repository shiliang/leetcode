package leetcode.DP;

/*
    最长公共子序列和最长公共子串

 */
public class LCS {
    /*
                                0      i = 0 || j = 0
     子序列   dp[i][j]  =     dp[i-1][j-1] + 1   xi = yj
                        max(dp[i-1][j],dp[i][j-1])  xi 不等于yj

                                0      i = 0 || j = 0
     子序列   dp[i][j]  =     dp[i-1][j-1] + 1   xi = yj
                                0  xi 不等于yj
     */

    //no.718 子数组要连续的
    public int findLength(int[] A, int[] B) {
        if (A.length == 0 || B.length == 0) return 0;
        int[][] dp = new int[A.length + 1][B.length + 1];
        int res = 0;
        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= B.length; j++) {
                if ( A[i - 1] == B[j - 1] ) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }
}

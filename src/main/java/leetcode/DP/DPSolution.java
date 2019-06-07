package leetcode.DP;

public class DPSolution {
    private double[] sums;
    private double[][] dp;

    //no.542
    public int[][] updateMatrix(int[][] matrix) {
        int rows = matrix.length;
        if (rows == 0) {
            return matrix;
        }
        int cols = matrix[0].length;
        int[][] dist = new int[rows][cols];
        //第一步检查左边上边
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    dist[i][j] = 0;
                } else {
                    if (i > 0) {
                        dist[i][j] = Math.min(dist[i-1][j] ,dist[i][j]) + 1;
                    }

                    if (j > 0) {
                        dist[i][j] = Math.min(dist[i][j-1] ,dist[i][j]) + 1;
                    }
                }
            }

        }

        //检查右边下边
        for (int i = rows - 1; i >= 0 ; i--) {
            for (int j = cols - 1; j >= 0 ; j--) {
                if (i < rows - 1) {
                    //下边
                    dist[i][j] = Math.min(dist[i][j], dist[i+1][j] + 1);
                }

                if (j < cols - 1) {
                    //右边
                    dist[i][j] = Math.min(dist[i][j], dist[i][j+1] + 1);
                }
            }

        }

        return dist;
    }

    //no.516并打印出字符串，最长回文子串
    public int longestPalindromeSubseq(String s) {
        int[][] dp = new int[s.length()][s.length()];
        //从i开始到j结束
        for (int i = s.length() - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i+1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1] + 2;  //dp[i+1][j-1]表示中间的对称部分+2表示两端两个字符
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        return dp[0][s.length()-1];
    }

    //813. Largest Sum of Averages
    public double largestSumOfAverages(int[] A, int K) {
        int n = A.length;
        sums = new double[n + 1];
        dp = new double[K + 1][n + 1];  //dp[k][i] 前i个元素被划分成k组
        //求前i个元素的和,便于后面求平均数
        for (int i = 1; i <= n; i++) {
            sums[i] = sums[i - 1] + A[i];
        }

        return LSA(A, n, K);
    }

    //前n个元素分成k个组取得最大值
    private double LSA(int[] A, int n, int k){
        if (dp[k][n] > 0) return dp[k][n];  //子问题求解过
        if (k == 1) return sums[n] / n;

        //i为数组的分割点,k-1组，1组
        for (int i = k - 1; i < n; i++) {
            dp[k][n] = Math.max(dp[k][n],
                    LSA(A, i, k - 1) + (sums[n] - sums[i]) / (n - i));
        }
        return dp[k][n];
    }


    //最长公共子序列

}

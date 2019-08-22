package leetcode.DP;

import java.util.Arrays;
import java.util.Comparator;

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


    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";
        int len = s.length();
        int maxLen = 0, left = 0, right = 0;
        boolean[][] dp = new boolean[len][len];
        for (int i = len - 1; i >= 0; i--) {
            dp[i][i] = true;
            for (int j = i + 1; j < len; j++) {
                dp[i][j] = (s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i + 1][j - 1]));
                if (dp[i][j] && maxLen < j - i + 1) {
                    maxLen = j - i + 1;
                    left = i;
                    right = j + 1;
                }
            }
        }
        return s.substring(left, right);
    }

    //no 354
    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        Arrays.sort(envelopes, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] == b[0] ? b[1] - a[1] : a[0] - b[0];
            }
        });
        int[] height = new int[n];
        for (int i = 0; i < n; i++) {
            height[i] = envelopes[i][1];
        }
        return lengthLIS(height);
    }

    public int lengthLIS(int[] height) {
        int lenlis = 1;
        if (height == null || height.length == 0) return 0;
        int len = height.length;
        int[] dp = new int[len];
        Arrays.fill(dp, 1);
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (height[j] < height[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            lenlis = Math.max(lenlis, dp[i]);
        }
        return lenlis;

    }

}

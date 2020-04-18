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

    //一维数组最长递增子序列  dp[i] = max(dp[i], dp[j] + 1)
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

    //no53,最长连续子数组的和
    //dp[i] = max(dp[i-1]+a[i], a[i])
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int res = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = Math.max(sum + nums[i], nums[i]);
            res = Math.max(res, sum);
        }
        return res;
    }

    //no.91解码方式,dp[i]表示前i个字符组成的子串的解码个数
    //dp[i] = dp[i - 1] + dp[i - 2]，类似于爬楼梯，和前面两个状态有关
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            dp[i] = (s.charAt(i - 1) == '0' ? 0 : dp[i - 1]);
            //前面两位为10到26之间
            if (i > 1 && (s.charAt(i - 2) == '1' || (s.charAt(i - 2) == '2' && s.charAt(i - 1) <= '6'))) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }

    //no.639 解码方式中*可以表示1-9的数字
    public int numDecodings2(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') return 0;
        int n = s.length();
        long[] dp = new long[n + 1];
        dp[0] = 1;
        dp[1] = (s.charAt(0) == '*' ? 9 : 1);
        for (int i = 2; i <= n; i++) {
            char first = s.charAt(i - 2);
            char second = s.charAt(i - 1);

            if (second == '*') {
                dp[i] += 9 * dp[i - 1];  //和前面一位的排列数,前一位有dp[i-1]种方式，dp[i]有9种方式
            } else if (second > '0') {
                dp[i] += dp[i - 1];
            }

            if (first == '*') {
                if (second == '*') {
                    dp[i] += 15 * dp[i - 2]; //两位数可以合并 11....26
                } else if (second <= '6') {
                    dp[i] += 2 * dp[i - 2];  //第二位可以取1或者2
                } else {
                    dp[i] += dp[i - 2];
                }

            } else if (first == '1' || first == '2') {
                if (second == '*') {
                    if (first == '1') {
                        dp[i] += 9 * dp[i - 2];
                    } else {
                        dp[i] += 6 * dp[i - 2];
                    }
                } else if (((first - '0') * 10 + (second - '0') ) <= 26) {
                    dp[i] += dp[i - 2];
                }
            }
            dp[i] %= 1000000007;
        }
        return (int) dp[n];
    }

    //no.801有两个状态0表示不交换，1表示交换
    public int minSwap(int[] A, int[] B) {
        int n = A.length;
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[0][0] = 0;
        dp[0][1] = 1;
        for (int i = 1; i < n; i++) {
            if (A[i] > A[i - 1] && B[i] > B[i - 1]) {
                dp[i][0] = dp[i - 1][0]; //不交换
                dp[i][1] = dp[i - 1][1] + 1; //交换的话前面的数也要交换
            }

            if (A[i] > B[i - 1] && B[i] > A[i - 1]) {
                dp[i][0] = Math.min(dp[i][0], dp[i - 1][1]); //当前位固定，交换前面一位
                dp[i][1] = Math.min(dp[i][1], dp[i - 1][0] + 1); //交换当前位，固定前一位
            }

        }
        return Math.min(dp[n - 1][0], dp[n - 1][1]);
    }

    //no.926 移动字符使得字符串变成递增
    public int minFlipsMonoIncr(String S) {
        return 0;
    }

    //no.10正则表达式匹配
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                if (p.charAt(j - 1) == '*') {
                    if (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.') {
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 1] || dp[i][j - 2];
                    }

                    else if (p.charAt(j - 2) != s.charAt(i - 1)) {
                        dp[i][j] = dp[i][j - 2];
                    }
                }
            }

        }
        return dp[m][n];
    }



}

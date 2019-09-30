package leetcode.DP;

public class Palindromic {
    //求一个字符串的所有回文子串的个数
    public int countSubstrings(String s) {
        int n = s.length();
        int res = 0;
        boolean[][] dp = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                dp[i][j] = (s.charAt(i) == s.charAt(j) && ((j - i) < 3 || dp[i + 1][j - 1]));
                if (dp[i][j]) res++;
            }

        }
        return res++;
    }

    //最长的回文子串
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
}

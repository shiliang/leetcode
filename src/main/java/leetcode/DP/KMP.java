package leetcode.DP;

/*
    通过模式串来生成dp数组
 */
public class KMP {
    private int[][] dp;  //dp[j][c]状态j遇到字符c的下一个状态
    private String pat;

    public KMP(String pat) {
        this.pat = pat;
        int M = pat.length();
        dp = new int[M][256];
        dp[0][pat.charAt(0)] = 1;
        int X = 0;  //影子状态和j有相同的前缀的位置
        for (int j = 1; j < M; j++) {
            for (int c = 0; c < 256; c++) {
                if (pat.charAt(j) == c) {
                    dp[j][c] = j + 1; //匹配上状态往前推进1
                } else {
                    dp[j][c] = dp[X][c]; //匹配不上回到前面的某状态,获得最近的匹配状态
                }
            }
            X = dp[X][pat.charAt(j)];
        }
    }

    public int search(String txt) {
        int M = pat.length();
        int N = txt.length();
        //pat的状态为0
        int j = 0;
        for (int i = 0; i < N; i++) {
            j = dp[j][txt.charAt(i)];
            //到达终止状态
            if (j == M) {
                return i - M + 1;
            }
        }
        return -1;
    }
}

package leetcode.array;


import java.util.Arrays;
import java.util.Comparator;

/*
 最长递增子序列
 */
public class LIS {
    //354,信封包含
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                //逆序保证相同的只有一个能选上,如果是顺序后面最长递增子序列会选多个
                return o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0];
            }
        });
        //把高加入一个一维数组
        int n = envelopes.length;
        int[] high = new int[n];
        for (int i = 0; i < n; i++) {
            high[i] = envelopes[i][1];
        }
        int res = 1;
        //求出最长递增子序列
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (high[j] < high[i]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}

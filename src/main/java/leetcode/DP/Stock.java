package leetcode.DP;

public class Stock {
    //no.121最多只能买卖一次股票，在最低价格买入，在最高价格卖出
    //L(i)为到i天的最小价格，P(i)为到i天的最大收益 P[i] = max(P[i - 1], price[i] - L(i))
    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        int low = prices[0];
        int[] profit = new int[prices.length];
        for (int i = 1; i < prices.length; i++) {
            low = Math.min(low, prices[i]);
            profit[i] = Math.max(profit[i - 1], prices[i] - low);  //卖出或者什么都不做
        }
        return profit[prices.length - 1];
    }

    //no.122 买卖股票的最佳时机||,不限买卖次数，但是买之前必须出售掉之前的股票
    public int maxProfit2(int[] prices) {
        int profit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i] < prices[i+1]) {   //递增序列
                profit += prices[i+1] - prices[i];
            }
        }
        return profit;

    }

    //no.123 最多有两次买卖,买入之前必须先卖出持有的股票
    public int maxProfit3(int[] prices) {
        if (prices.length == 0) return 0;
        int n = prices.length;
        int max_k = 2;
        int[][][] dp = new int[n][max_k + 1][2];
        for (int i = 0; i < n; i++) {
            for (int k = max_k; k >= 1; k--) {
                if (i - 1 == -1) {
                    dp[i][k][0] = 0;
                    dp[i][k][1] = -prices[i];
                } else {
                    dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                    dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
                }
            }

        }
        return dp[n - 1][max_k][0];
    }

    //no.188 最多有k笔交易
    public int maxProfit(int k, int[] prices) {
        if (prices.length == 0) return 0;
        int n = prices.length;
        if (k > n / 2) {
            return maxProfit2(prices);  //如果超过,想当与无限次数
        }
        int max_k = k;
        int[][][] dp = new int[n][max_k + 1][2];
        for (int i = 0; i < n; i++) {
            for (int j = max_k; j >= 1; j--) {
                if (i - 1 == -1) {
                    dp[i][j][0] = 0;
                    dp[i][j][1] = -prices[i];
                } else {
                    dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                    dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
                }
            }

        }
        return dp[n - 1][max_k][0];
    }

    //309
}

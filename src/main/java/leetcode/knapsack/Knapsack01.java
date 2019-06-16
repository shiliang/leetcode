package leetcode.knapsack;

import java.util.Arrays;

/*
   01背包（ZeroOnePack）: 有N件物品和一个容量为V的背包。
 （每种物品均只有一件）第i件物品的费用是c[i]，价值是w[i]。
   求解将哪些物品装入背包可使价值总和最大。
 */

public class Knapsack01 {
    //no 416. Partition Equal Subset Sum 能否把一个数组分成两个相同和的子数组
    public boolean canPartition(int[] nums) {
        int sum  = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum % 2 != 0) return false;


        sum = sum / 2;
        int len = nums.length;
        boolean[][] dp = new boolean[len + 1][sum + 1];   //dp[i][j]表示和为j可以从前面i个数里面取到
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], false);
        }

        dp[0][0] = true;

        for (int i = 1; i < len + 1; i++) {
            dp[i][0] = true;   //和为0的话，前面不取数就可以了
        }

        for (int j = 1; j < sum + 1; j++) {
            dp[0][j] = false;   //前面不取数的话，和始终为0
        }

        for (int i = 1; i < len + 1; i++) {
            for (int j = 1; j < sum + 1; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= nums[i - 1]) {
                    dp[i][j] = (dp[i][j] || dp[i-1][j-nums[i-1]]);
                }
            }
        }
        return dp[len][sum];

    }
}

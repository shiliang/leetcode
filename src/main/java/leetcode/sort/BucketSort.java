package leetcode.sort;

import java.util.Arrays;

public class BucketSort {
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length <= 1) return 0;
        if (nums.length == 2) {
            return Math.abs(nums[0] - nums[1]);
        }
        int imax = nums[0], imin = nums[0];
        for (int num : nums) {
            imax = Math.max(imax, num);
            imin = Math.min(imin, num);
        }
        int len = nums.length;
        int gap = (int)Math.ceil((double) (imax - imin) / (len - 1));
        int[] bucketsMIN = new int[len - 1];
        int[] bucketsMAX = new int[len - 1];
        Arrays.fill(bucketsMIN, Integer.MAX_VALUE);
        Arrays.fill(bucketsMAX, Integer.MIN_VALUE);

        for (int num : nums) {
            if (num == imax || num == imin) continue;
            int index = (num - imin) / gap;
            bucketsMIN[index] = Math.min(num, bucketsMIN[index]);
            bucketsMAX[index] = Math.max(num, bucketsMAX[index]);

        }
        int maxGap = Integer.MIN_VALUE;
        int previous = imin;
        for (int i = 0; i < len - 1; i++) {
            if (bucketsMAX[i] == Integer.MIN_VALUE && bucketsMIN[i] == Integer.MAX_VALUE)
                continue;
            maxGap = Math.max(bucketsMIN[i] - previous, maxGap);
            previous = bucketsMAX[i];
        }
        maxGap = Math.max(maxGap, imax - previous);
        return maxGap;
    }
}

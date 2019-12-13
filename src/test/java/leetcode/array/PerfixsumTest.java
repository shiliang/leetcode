package leetcode.array;

import org.junit.Test;

import static org.junit.Assert.*;

public class PerfixsumTest {
    Perfixsum perfixsum = new Perfixsum();

    @Test
    public void maxSubArrayLen() {
        int[] nums = {-2,-1,2,1};
        int res = perfixsum.maxSubArrayLen(nums, 1);
    }
}
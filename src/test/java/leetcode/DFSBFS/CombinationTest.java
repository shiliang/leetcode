package leetcode.DFSBFS;

import org.junit.Test;

import static org.junit.Assert.*;

public class CombinationTest {
    Combination combination = new Combination();

    @Test
    public void subsets() {
        int[] nums = {1,2,3};
        combination.subsets(nums);
    }
}
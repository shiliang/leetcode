package leetcode.DP;

import org.junit.Test;

import static org.junit.Assert.*;

public class IntervalTest {
    Interval interval = new Interval();

    @Test
    public void mergeStones() {
        int[] input = {6,4,9,3,1};
        interval.mergeStones(input, 3);
    }
}
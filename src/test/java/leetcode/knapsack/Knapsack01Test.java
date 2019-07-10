package leetcode.knapsack;

import org.junit.Test;

import static org.junit.Assert.*;

public class Knapsack01Test {

    Knapsack01 knapsack01 = new Knapsack01();

    @Test
    public void canPartition() {
        int[] input = {1,5,11,5};
        boolean res = knapsack01.canPartition(input);
    }
}
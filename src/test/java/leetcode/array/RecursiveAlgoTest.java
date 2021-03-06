package leetcode.array;

import org.junit.Test;

import static org.junit.Assert.*;

public class RecursiveAlgoTest {

    @Test
    public void getNumberOfK() {
        RecursiveAlgo ra = new RecursiveAlgo();
        int[] input = {1,2,3,3,3,3,4,5};
        int count = ra.GetNumberOfK(input, 3);
    }

    @Test
    public void combinationSum() {
        RecursiveAlgo ra = new RecursiveAlgo();
        int[] input = {2,3,6,7};
        ra.combinationSum(input, 7);
    }
}

package leetcode.divide_conquer;

import org.junit.Test;

import static org.junit.Assert.*;

public class InversePairsTest {
    InversePairs inversePairs = new InversePairs();

    @Test
    public void count() {
        int[] array = {2, 4, 3, 1, 5, 6};
        inversePairs.count(array);

    }
}
package leetcode.sort;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SortAlgoTest {

    SortAlgo sa = new SortAlgo();
    @Test
    public void sortColors() {
        int[] input = {1,0};

        sa.sortColors(input);
    }

    @Test
    public void threeSum() {
        int[] input = {0,0,0};
        List<List<Integer>> res = sa.threeSum(input);
    }
}

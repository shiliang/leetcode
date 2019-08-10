package leetcode.sort;

import org.junit.Test;

import static org.junit.Assert.*;

public class QuickSortTest {

    QuickSort quickSort = new QuickSort();
    @Test
    public void threeWayQuickSort() {
        int[] input = {4,2,1,3,9,0,6,8};
        quickSort.threeWayQuickSort(input, 0, input.length - 1);
    }
}
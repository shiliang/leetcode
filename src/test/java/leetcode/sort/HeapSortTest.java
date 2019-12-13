package leetcode.sort;

import org.junit.Test;

import static org.junit.Assert.*;

public class HeapSortTest {

    @Test
    public void heapSort() {
        HeapSort heapSort = new HeapSort();
        int[] input = {4,6,3,9,1,5};
        heapSort.heapSort(input);
    }
}
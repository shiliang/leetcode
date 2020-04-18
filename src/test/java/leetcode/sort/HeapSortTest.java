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

    @Test
    public void findKthLargest() {
        HeapSort heapSort = new HeapSort();
        int[] input = {3,2,3,1,2,4,5,5,6};
        heapSort.findKthLargest(input, 4);
    }
}
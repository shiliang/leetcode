package leetcode.array;

import org.junit.Test;

import static org.junit.Assert.*;

public class BinarySearchTest {

    BinarySearch binarySearch = new BinarySearch();
    @Test
    public void search() {
        int[] input = {4,5,6,7,0,1,2};
        int res = binarySearch.search(input, 3);
    }

    @Test
    public void search2() {
        int[] input = {2,5,6,0,0,1,2};
        binarySearch.search2(input, 0);
    }


}

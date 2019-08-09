package leetcode.sort;

import org.junit.Test;

import static org.junit.Assert.*;

public class BucketSortTest {
    BucketSort bucketSort = new BucketSort();
    @Test
    public void maximumGap() {
        int[] nums = {3,6,9,1};
        bucketSort.maximumGap(nums);
    }
}

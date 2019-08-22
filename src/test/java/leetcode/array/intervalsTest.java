package leetcode.array;

import org.junit.Test;

import static org.junit.Assert.*;

public class intervalsTest {

    intervals inter = new intervals();
    @Test
    public void merge() {
        int[][] input = {{1,3},{2,6},{8,10},{15,18}};
        inter.merge(input);
    }

    @Test
    public void insert() {
        int[][] input = {{1,3}, {6, 9}};
        int[] newarr = {2,5};
        inter.insert(input, newarr);
    }
}

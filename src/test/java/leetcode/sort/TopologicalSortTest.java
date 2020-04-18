package leetcode.sort;

import org.junit.Test;

import static org.junit.Assert.*;

public class TopologicalSortTest {
    TopologicalSort topologicalSort = new TopologicalSort();

    @Test
    public void alienOrder() {
        String[] inputs = {"wrt", "wrf", "er", "ett", "rftt"};
        topologicalSort.alienOrder(inputs);
    }
}
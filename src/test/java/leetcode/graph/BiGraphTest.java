package leetcode.graph;

import org.junit.Test;

import static org.junit.Assert.*;

public class BiGraphTest {
    BiGraph biGraph = new BiGraph();

    @Test
    public void possibleBipartition() {
        int[][] input = {{1,2},{1,3},{2,4}};
        biGraph.possibleBipartition(4,input);
    }
}
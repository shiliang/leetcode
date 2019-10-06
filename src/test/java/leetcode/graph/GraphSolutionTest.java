package leetcode.graph;

import org.junit.Test;

import static org.junit.Assert.*;

public class GraphSolutionTest {

    GraphSolution graphSolution = new GraphSolution();

    @Test
    public void shortestAlternatingPaths() {

        int[][] red = {{0,1},{0,2}};
        int[][] blue = {{1,0}};
        graphSolution.shortestAlternatingPaths(3, red, blue);
    }
}
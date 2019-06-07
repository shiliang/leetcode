package leetcode.greedy;

import org.junit.Test;

import static org.junit.Assert.*;

public class GreedyAlgoTest {

    @Test
    public void candy() {
        GreedyAlgo greedyAlgo = new GreedyAlgo();
        int[] input = {1,3,2,2,1};
        int res = greedyAlgo.candy(input);
    }

    @Test
    public void twoCitySchedCost() {
        GreedyAlgo greedyAlgo = new GreedyAlgo();
        int[][] input = {{259,770}, {448, 54}, {926, 667}, {184, 139},
                {840, 118}, {577, 469}};
        int res = greedyAlgo.twoCitySchedCost(input);
    }
}
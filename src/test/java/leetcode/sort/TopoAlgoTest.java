package leetcode.sort;

import org.junit.Test;

import static org.junit.Assert.*;

public class TopoAlgoTest {

    @Test
    public void canFinish() {
        TopoAlgo topoAlgo = new TopoAlgo();
        int[][] input = {{0,1}};
        topoAlgo.canFinish(2, input);
    }
}

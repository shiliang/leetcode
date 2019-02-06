package leetcode;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import leetcode.graph.GraphSolution;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        GraphSolution graphSolution = new GraphSolution();
        int[][] arr = {{1,0},{0,1}};
        if (graphSolution.canFinish(2,arr)) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }
}

package leetcode.DP;

import org.junit.Test;

import static org.junit.Assert.*;

public class DPSolutionTest {

    @Test
    public void longestPalindromeSubseqTest(){
        DPSolution dpSolution = new DPSolution();
        String s = "bbbab";
        dpSolution.longestPalindromeSubseq(s);
    }


    @Test
    public void maxEnvelopes() {
        DPSolution dpSolution = new DPSolution();
        int[][] input = {{4,5},{4,6},{6,7},{2,3},{1,1}};
        dpSolution.maxEnvelopes(input);
    }

    @Test
    public void isMatch() {
        DPSolution dpSolution = new DPSolution();
        dpSolution.isMatch("aa", ".*");
    }


}

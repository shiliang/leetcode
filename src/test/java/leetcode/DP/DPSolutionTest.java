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
    public void longestPalindrome() {
        DPSolution dpSolution = new DPSolution();
        String s = "cbbd";
        dpSolution.longestPalindrome(s);
    }
}

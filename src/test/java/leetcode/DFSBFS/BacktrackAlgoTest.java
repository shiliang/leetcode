package leetcode.DFSBFS;

import org.junit.Test;

import static org.junit.Assert.*;

public class BacktrackAlgoTest {
    BacktrackAlgo backtrackAlgo = new BacktrackAlgo();

    @Test
    public void solveNQueens() {
        backtrackAlgo.solveNQueens(4);
    }
}
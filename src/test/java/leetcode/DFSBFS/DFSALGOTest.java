package leetcode.DFSBFS;

import org.junit.Test;

import static org.junit.Assert.*;

public class DFSALGOTest {
    DFSALGO dfsalgo = new DFSALGO();


    @Test
    public void exist() {
        char[][] board = {{'a'},{'a'}};
        dfsalgo.exist(board, "aaa");
    }

    @Test
    public void solveSudoku() {
        char[][] board = {{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}};

        dfsalgo.solveSudoku(board);
    }
}

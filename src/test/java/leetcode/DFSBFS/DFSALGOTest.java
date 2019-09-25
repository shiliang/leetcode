package leetcode.DFSBFS;

import org.junit.Test;

import static org.junit.Assert.*;

public class DFSALGOTest {
    DFSALGO dfsalgo = new DFSALGO();

    @Test
    public void permuteUnique() {
        int[] input = {1,1,2};
        dfsalgo.permuteUnique(input);
    }

    @Test
    public void numSquarefulPerms() {
        int[] input = {65,44,5,11};
        dfsalgo.numSquarefulPerms(input);
    }
}
package leetcode.list;

import org.junit.Test;

import static org.junit.Assert.*;

public class ListAlgoTest {

    @Test
    public void permute() {
        int[] input = {1,2,3,3};
        ListAlgo la = new ListAlgo();
        la.permute(input);
    }
}
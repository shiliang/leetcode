package leetcode.list;

import org.junit.Test;

import static org.junit.Assert.*;

public class ListAlgoTest {

    @Test
    public void permute() {
        int[] input = {1,2,3,3};
        ListAlgo la = new ListAlgo();
        //la.permute(input);
    }

    @Test
    public void IsPopOrder() {
        int[] pushA = {1,2,3,4,5};
        int[] popA = {4,3,5,1,2};
        ListAlgo la = new ListAlgo();
        boolean a = la.IsPopOrder(pushA, popA);
    }
}
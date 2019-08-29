package leetcode.array;

import org.junit.Test;

import static org.junit.Assert.*;

public class LISTest {

    @Test
    public void maxEnvelopes() {
        LIS lis = new LIS();
        int[][] env = {{5,4},{6,4},{6,7},{2,3}};
        lis.maxEnvelopes(env);
    }
}

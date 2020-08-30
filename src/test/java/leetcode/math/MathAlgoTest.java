package leetcode.math;

import org.junit.Test;

import static org.junit.Assert.*;

public class MathAlgoTest {

    MathAlgo mathAlgo = new MathAlgo();

    @Test
    public void sqrt() {
        double res  = mathAlgo.sqrt(2);
        System.out.println(res);
    }
}
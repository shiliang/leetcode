package leetcode.string;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {

    @Test
    public void calculate() {
        Calculator calculator = new Calculator();
        String input = "2*(5+5*2)/3+(6/2+8)";
        int res = calculator.calculate(input);
    }
}

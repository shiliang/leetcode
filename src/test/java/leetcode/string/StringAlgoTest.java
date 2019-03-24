package leetcode.string;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringAlgoTest {

    @Test
    public void validIPAddress() {
        StringAlgo stringAlgo = new StringAlgo();
        stringAlgo.validIPAddress("20EE:FGb8:85a3:0:0:8A2E:0370:7334");
    }

    @Test
    public void pri() {
        int num = 0;
        foo(num);
        System.out.println(num);

    }

    public void foo(int value) {
        value = 100;
    }
}
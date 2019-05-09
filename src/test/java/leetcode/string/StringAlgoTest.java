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
    public void cal() {
        StringAlgo stringAlgo = new StringAlgo();
        int res = stringAlgo.calculate("3+2*2");
    }

    @Test
    public void decodeString() {
        StringAlgo stringAlgo = new StringAlgo();
        String a = stringAlgo.decodeString("3[a]2[bc]");
    }

    @Test
    public void match() {
        StringAlgo stringAlgo = new StringAlgo();
        char[] text = {};
        char[] pattern = {'.'};
        boolean res = stringAlgo.match(text, pattern);
    }
}
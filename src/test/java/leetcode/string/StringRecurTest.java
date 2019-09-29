package leetcode.string;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringRecurTest {

    StringRecur stringRecur = new StringRecur();
    @Test
    public void countAndSay() {
        stringRecur.countAndSay(4);
    }

    @Test
    public void numDecodingsRecu() {
        stringRecur.numDecodingsRecu("12");
    }
}

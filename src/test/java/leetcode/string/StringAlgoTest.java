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

    @Test
    public void comparison() {
        StringAlgo stringAlgo = new StringAlgo();
        String s = "aa";
        String p = "*";
        boolean res = stringAlgo.comparison(s, p);
    }

    @Test
    public void lengthOfLongestSubstring() {
        StringAlgo stringAlgo = new StringAlgo();
        int res = stringAlgo.lengthOfLongestSubstring("abba");
    }

    @Test
    public void multiply() {
        StringAlgo stringAlgo = new StringAlgo();
        String a = stringAlgo.multiply("123","456");
    }

    @Test
    public void longestCommonPrefix() {
        StringAlgo stringAlgo = new StringAlgo();
        String[] strings = {"flower","flow","flight"};
        String a = stringAlgo.longestCommonPrefix(strings);
    }

    @Test
    public void parseChineseNumber() {
        StringAlgo stringAlgo = new StringAlgo();
        long a = stringAlgo.parseChineseNumber("三千二百零一万九千七百六十五亿四千三百二十一万九千八百七十六");
        System.out.println(a);
    }

    @Test
    public void findSubstring() {
        StringAlgo stringAlgo = new StringAlgo();
        String s = "wordgoodgoodgoodbestwordgoodbestword";
        String[] words = {"word","good","best","word"};
        stringAlgo.findSubstring(s, words);

    }
}

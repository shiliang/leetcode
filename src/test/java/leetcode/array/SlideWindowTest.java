package leetcode.array;

import org.junit.Test;

import static org.junit.Assert.*;

public class SlideWindowTest {

    SlideWindow slideWindow = new SlideWindow();

    @Test
    public void lengthOfLongestSubstring() {
        //tmsmfdut, bbb
        int res = slideWindow.lengthOfLongestSubstring("pwwkew");
    }

    @Test
    public void maxWindows() {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int[] res = slideWindow.maxSlidingWindow(nums, 3);
    }

    @Test
    public void minWindow() {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        slideWindow.minWindow(s, t);

    }

    @Test
    public void minSqeWindow() {
        String S = "abcdebdde";
        String T = "bde";
        String res = slideWindow.minSqeWindow(S, T);
    }


    @Test
    public void findAnagrams() {
        String s = "aa";
        String p = "bb";
        slideWindow.findAnagrams(s, p);
    }

    @Test
    public void lengthOfLongestSubstringTwoDistinct() {
        String s = "cdaba";
        slideWindow.lengthOfLongestSubstringTwoDistinct(s);
    }
}
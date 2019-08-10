package leetcode.array;

import org.junit.Test;

import static org.junit.Assert.*;

public class SlideWindowTest {

    SlideWindow slideWindow = new SlideWindow();

    @Test
    public void lengthOfLongestSubstring() {
        //tmsmfdut, bbb
        int res = slideWindow.lengthOfLongestSubstring("bbb");
    }

    @Test
    public void maxWindows() {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int[] res = slideWindow.maxSlidingWindow(nums, 3);
    }
}
package leetcode.array;

/*
    一般用在数组和字符串问题当中，定义前后两个指针表示窗口的范围
 */

import java.util.HashMap;
import java.util.Map;

public class SlideWindow {

    public int lengthOfLongestSubstring(String s) {
        if (s.length()==0) return 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int max=0;
        for (int i=0, j=0; i<s.length(); ++i){
            if (map.containsKey(s.charAt(i))){
                j = Math.max(j,map.get(s.charAt(i))+1); //max操作防止j往前移动,+1是相同的字母往前一个位置即下一段的开始位置
            }
            map.put(s.charAt(i),i);
            max = Math.max(max,i-j+1);
        }
        return max;

    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        int[] res = new int[len];
        return res;
    }
}

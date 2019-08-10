package leetcode.array;

/*
    一般用在数组和字符串问题当中，定义前后两个指针表示窗口的范围
 */

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
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
        int[] res = new int[nums.length - k + 1];
        int index = 0;
        Deque<Integer> queue = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            int currentVal = nums[i];

            while (!queue.isEmpty() && nums[queue.peekLast()] < currentVal) {
                queue.pollLast();
            }

            if (!queue.isEmpty() && queue.peekFirst() == i - k) {
                queue.pollFirst();
            }

            queue.offerLast(i);

            if (i < k - 1) continue;
            res[index++] = nums[queue.peekFirst()];


        }
        return res;
    }


}

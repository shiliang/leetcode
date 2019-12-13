package leetcode.array;

/*
    一般用在数组和字符串问题当中，定义前后两个指针表示窗口的范围
 */

import java.util.*;

public class SlideWindow {


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

    //no.76最小覆盖子串
    public String minWindow(String s, String t) {
        if (s == null || t == null) return "";
        int left = 0, right = 0;
        HashMap<Character, Integer> window = new HashMap<>(); //记录left到right区间字符的个数
        HashMap<Character, Integer> needs = new HashMap<>();
        int minLen = Integer.MAX_VALUE, start = 0;
        for (char c : t.toCharArray()) {
            needs.put(c, needs.getOrDefault(c , 0) + 1);
        }
        int match = 0;
        while (right < s.length()) {
            char c1 = s.charAt(right);
            if (needs.containsKey(c1)) {
                window.put(c1, window.getOrDefault(c1, 0) + 1);
                if (window.get(c1) == needs.get(c1)) {
                    match++;
                }
            }
            right++;
            //window中的字符串已符合needs的要求了
            while (match == needs.size()) {
                if (right - left < minLen) {
                    //更新最小子串的位置和长度
                    start = left;
                    minLen = right - left;
                }
                char c2 = s.charAt(left);
                //缩小窗口的范围
                if (needs.containsKey(c2)) {
                    window.put(c2, window.get(c2) - 1);
                    if (window.get(c2) < needs.get(c2)) {
                        match--;
                    }
                }
                left++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }


    //no.3无重复字符的最长子串
    public int lengthOfLongestSubstring(String s) {
        int left = 0, right = 0;
        HashMap<Character, Integer> window = new HashMap<>();
        int res = 0;
        while (right < s.length()) {
            char c1 = s.charAt(right);
            window.put(c1, window.getOrDefault(c1, 0) + 1);
            right++;
            while (window.get(c1) > 1) {  //指针走到后面第一个不带有重复字符的地方
                char c2 = s.charAt(left);
                window.put(c2, window.get(c2) - 1);
                left++;
            }
            res = Math.max(res, right - left);

        }
        return res;
    }

    //no.727最小窗口子序列
    public String minSqeWindow(String S, String T) {
        int i = 0, j = 0;
        int start = -1, minLen = Integer.MAX_VALUE;
        while (i < S.length()) {
            if (S.charAt(i) == T.charAt(j)) {
                if (++j == T.length()) {
                    int end = i + 1;
                    while (--j >= 0) {
                        while (S.charAt(i--) != T.charAt(j)){}; //往前搜索
                    }
                    ++i;  //回到满足条件最开始的地方,即跑到前面一个i和j对应字符相等的地方
                    ++j;
                    if (end - i < minLen) {
                        minLen = end - i;
                        start = i;
                    }
                }
            }
            i++;
        }
        return (start != -1) ? S.substring(start, start + minLen) : "";

    }

    //no.438
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<Integer>();
        int n = s.length();
        int l = p.length();
        if (n < l) return res;
        int[] hs = new int[26];
        int[] hp = new int[26];
        for (int i = 0; i < l; i++) {
            hp[p.charAt(i) - 'a']++;
        }
        for (int i = 0; i < n; i++) {
            if (i >= l) {
                hs[s.charAt(i - l) - 'a']--; //把最左边的字符状态删除
            }
            hs[s.charAt(i) - 'a']++;
            if (isSame(hs, hp)) res.add(i-l+1);
        }
        return res;
    }

    //比较两个hash数组所含有的字符是否相同
    private boolean isSame(int[] a, int[] b) {
        for (int i = 0; i < 26; i++) {
            if (a[i] != b[i]) return false;
        }
        return true;
    }

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int res = 0;
        if (s.length() <= 0) return res;
        int left = 0, right = 0;
        HashMap<Character, Integer> map = new HashMap<>(); //字符，最右的下标
        while (right < s.length()) {
            if (map.size() < 3) {
                map.put(s.charAt(right), right++);
            }

            if (map.size() == 3) {
                int index = Collections.min(map.values());
                map.remove(s.charAt(index));
                left = index + 1;
            }
            res = Math.max(res, right - left);
        }
        return res;
    }


}

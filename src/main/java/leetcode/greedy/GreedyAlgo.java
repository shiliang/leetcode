package leetcode.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

public class GreedyAlgo {
    /*
        累加在每个位置的left += gas[i] - cost[i], 就是在每个位置剩余的油量,
        如果left一直大于0, 就可以一直走下取. 如果left小于0了,
        那么就从下一个位置重新开始计数, 并且将之前欠下的多少记录下来,
        如果最终遍历完数组剩下的燃料足以弥补之前不够的, 那么就可以到达,
        并返回最后一次开始的位置.否则就返回-1.

     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int total_tank = 0;
        int curr_tank = 0;
        int start = 0;
        for (int i = 0; i < n; i++) {
            total_tank += gas[i] - cost[i];
            curr_tank += gas[i] - cost[i];
            if (curr_tank < 0) {
                start = i + 1;
                curr_tank = 0;
            }
        }
        return  total_tank >= 0 ? start : 1;
    }

    /*
        每个小孩先至少有一个糖果
        再从前到后和从后到前两次遍历
     */
    public int candy(int[] ratings) {
        if (ratings.length == 0) {
            return 0;
        }
        int[] candy = new int[ratings.length];
        int res = 0;
        Arrays.fill(candy, 1);
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candy[i] = candy[i - 1] + 1;
            }
        }

        for (int i = ratings.length - 2; i >= 0 ; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candy[i] = Math.max(candy[i], candy[i + 1] + 1);
            }
        }

        for (int i = 0; i < candy.length; i++) {
            res += candy[i];
        }
        return res;
    }

    //去除k个数字使得整体值最小
    public String removeKdigits(String num, int k) {
        int len = num.length();
        if (k == len) return "0";

        Stack<Character> stack = new Stack<>();
        int i = 0;
        while (i < num.length()) {
            //如果当前的元素比栈顶的元素大则丢弃栈顶的元素
            while (k > 0 && !stack.isEmpty() && stack.peek() > num.charAt(i)) {
                stack.pop();
                k--;
            }
            stack.push(num.charAt(i));
            i++;
        }

        //如果还有剩余把栈顶的元素删掉，因为栈顶的最大,尽可能删除高位大的元素体现贪心的思想
        while (k > 0) {
            stack.pop();
            k--;
        }

        //拼接字符串
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        sb.reverse();

        //把字符串头部的0去掉
        while (sb.length() > 1 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }

    //除去重复的数字，返回新的字符串是字典顺序排序最小的（小的尽可能的排在前面）字符的相对顺序保持不变
    // 看前面的字符后面会不会出现如果出现则排在前面比当前大的字符删除
    public String removeDuplicateLetters(String s) {
        if (s == null) return null;
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        Stack<Character> stack = new Stack<>();
        boolean[] visited = new boolean[26]; //标记每个字符是不是被访问过
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']--;
            if (visited[s.charAt(i) - 'a']) continue; //前面访问过说明重复删除
            //查看栈顶的字符是否比当前的字符大且后面还有栈顶的字符
            while (!stack.isEmpty() && stack.peek() > s.charAt(i)
                && count[stack.peek() - 'a'] > 0) {
                visited[stack.peek() - 'a'] = false;
                stack.pop();
            }
            stack.push(s.charAt(i));
            visited[s.charAt(i) - 'a'] = true;
        }
        StringBuilder res = new StringBuilder();
        for (char ch: stack
             ) {
            res.append(ch);
        }
        return res.toString();
    }

    //递归版
    public String removeDuplicateLetters2(String s) {
        if (s == null) return null;
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        int pos = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < s.charAt(pos)) pos = i;
            if (--count[s.charAt(i) - 'a'] == 0) break;

        }
        return s.length() == 0 ? "" : s.charAt(pos) +
                removeDuplicateLetters2(s.substring(pos + 1).replaceAll("" + s.charAt(pos), ""));
    }

    public int twoCitySchedCost(int[][] costs) {
        //根据A城市和B城市的差值排序
        Arrays.sort(costs, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return (a[1] - a[0]) - (b[1] - b[0]);
            }
        });
        int cost = 0;
        for (int i = 0; i < costs.length / 2; i++) {
            cost += costs[i][1] + costs[costs.length-i-1][0];
        }
        return cost;
    }
}

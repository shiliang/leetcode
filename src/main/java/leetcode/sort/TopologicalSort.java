package leetcode.sort;


import java.util.*;

public class TopologicalSort {

    //no.269火星词典
    public String alienOrder(String[] words) {
        //创建有向图
        Map<Character, Set<Character>> map = new HashMap<>();
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = 0; j < words[i].length() && j < words[i + 1].length(); j++) {
                if (words[i].charAt(j) == words[i + 1].charAt(j)) continue;
                //找到第一个不相同的字符
                Set<Character> set = map.getOrDefault(words[i].charAt(j), new HashSet<>());
                set.add(words[i + 1].charAt(j));
                map.put(words[i].charAt(j), set);
                break;
            }
        }

        //拓扑排序
        int[] degrees = new int[26];
        Arrays.fill(degrees, -1);
        for (String str : words) {
            //将出现过的字符初始化为0
            for (char c : str.toCharArray()) {
                degrees[c - 'a'] = 0;
            }

        }
        for (char key : map.keySet()) {
            for (char val : map.get(key)) {
                degrees[val - 'a']++;
            }
        }
        StringBuilder sb = new StringBuilder();
        Queue<Character> queue = new LinkedList<>();
        int count = 0;
        //把节点入度为0的数先压入队列
        for (int i = 0; i < 26; i++) {
            if (degrees[i] != -1) count++;
            if (degrees[i] == 0) queue.offer((char)(i + 'a'));
        }

        while (!queue.isEmpty()) {
            Character cur = queue.poll();
            sb.append(cur);
            if (map.containsKey(cur)) {
                Set<Character> set = map.get(cur);
                for (Character c : set) {
                    degrees[c - 'a']--;
                    if (degrees[c - 'a'] == 0) queue.offer(c);
                }
            }
        }
        return sb.toString();

    }
}

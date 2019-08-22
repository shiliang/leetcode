package leetcode.list;

import java.util.HashMap;
import java.util.LinkedHashSet;

public class LFUCache {
    private HashMap<Integer, Integer> keyVals;
    private HashMap<Integer, Integer> keyCounts; //每个key的使用次数，主要作用是存储当前key的频率便于放到countKeySets中
    private HashMap<Integer, LinkedHashSet<Integer>> countKeySets;  //相同的次数对应的key，如果在前面说明最近最少使用

    private int capacity;
    private int min;  //记录使用的最小次数，如果超过容量则把最小的给置换出来
    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.min = -1;
        keyVals = new HashMap<>();
        keyCounts = new HashMap<>();
        countKeySets = new HashMap<>();
        countKeySets.put(1, new LinkedHashSet<>());  //初始化为频率1
    }

    public int get(int key) {
        if (!keyVals.containsKey(key) || capacity <= 0) {
            return -1;
        }
        int count = keyCounts.get(key);
        keyCounts.put(key, count + 1);
        countKeySets.get(count).remove(key);
        if (count == min && countKeySets.get(count).size() == 0) {
            min++;
        }
        if (!countKeySets.containsKey(count + 1)) {
            countKeySets.put(count + 1, new LinkedHashSet<>());
        }
        countKeySets.get(count + 1).add(key);
        return keyVals.get(key);
    }

    public void put(int key, int value) {
        if (keyVals.containsKey(key)) {
            keyVals.put(key, value);
            get(key);
            return;
        }

        if (keyVals.size() >= capacity) {
            int leastFreq = countKeySets.get(min).iterator().next(); //如果频率相同，返回最近最少使用的那个,能否用链表代替？
            keyVals.remove(leastFreq);
            keyCounts.remove(leastFreq);
            countKeySets.get(min).remove(leastFreq);
        }
        keyVals.put(key, value);
        keyCounts.put(key, 1);
        countKeySets.get(1).add(key);
        min = 1;
    }
}

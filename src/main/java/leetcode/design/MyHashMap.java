package leetcode.design;

import java.util.ArrayList;
import java.util.List;

public class MyHashMap<K, V> implements MyMap<K, V> {

    //数组默认初始化长度
    private static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;

    //装载因子
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private int defaultInitSize;
    private float defaultLoadFactor;

    //Map中entry的数量
    private int entryUseSize;

    //数组
    private Entry<K, V>[] table = null;

    static class Entry<K, V> implements MyMap.Entry<K, V> {
        private K key;
        private V value;
        private Entry<K, V> next;

        public Entry() {

        }

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }
    }

    //构造函数，初始化
    public MyHashMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap(int defaultInitialCapacity, float defaultLoadFactor) {
        if (defaultInitialCapacity < 0)
            throw new IllegalArgumentException("Illegal initial capacity : " +
                    defaultInitialCapacity);
        if (defaultLoadFactor <= 0 || Float.isNaN(defaultLoadFactor))
            throw new IllegalArgumentException("Illegal load factor: " +
                    defaultLoadFactor);
        this.defaultInitSize = defaultInitialCapacity;
        this.defaultLoadFactor = defaultLoadFactor;
        table = new Entry[this.defaultInitSize];
    }



    @Override
    public V put(K k, V v) {
        V oldValue = null;
        //是否需要扩容
        if (entryUseSize + 1 >= defaultInitSize * defaultLoadFactor) {
            resize(2 * defaultInitSize);
        }
        //hash & (n - 1) 等于 hash % (length - 1)
        int index = hash(k) & (defaultInitSize - 1);
        if (table[index] == null) {
            table[index] = new Entry<K, V>(k, v, null);

        } else {  //有冲突需要遍历单链表
            Entry<K, V> p = table[index];
            while (p.next != null) {
                if (k == p.getKey() || k.equals(p.getKey())) {
                    oldValue = p.value;
                    p.value = v;
                    return oldValue;
                }
                p = p.next;
            }
            p.next = new Entry<K, V>(k, v, null);
        }
        ++entryUseSize;
        return oldValue;
    }

    @Override
    public V get(K k) {
        int index = hash(k) & (defaultInitSize - 1);

        if (table[index] == null) {
            return null;
        } else {
            Entry<K, V> entry = table[index];
            while (entry != null) {
                if (k == entry.getKey() || k.equals(entry.getKey())) {
                    return entry.value;
                }
                entry = entry.next;
            }
        }
        return null;
    }

    //参数为扩容的大小
    private void resize(int size) {
        Entry[] newTable = new Entry[size];
        defaultInitSize = size;
        entryUseSize = 0;
        rehash(newTable);
    }

    private void rehash(Entry<K, V>[] newTable) {
        //得到原来所有的节点
        List<Entry<K, V>> entryList = new ArrayList<>();
        for (Entry<K, V> entry : table) {
            while (entry != null) {
                entryList.add(entry);
                entry = entry.next;
            }
        }

        //覆盖旧的引用
        if (newTable.length > 0) {
            table = newTable;
        }

        //重新放到hashmap中
        for (Entry<K, V> entry : entryList) {
            put(entry.getKey(), entry.getValue());
        }
    }

    //把key与高16位异或，尽量减少碰撞
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}

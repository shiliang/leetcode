package leetcode.array;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    /*
    在hashmap里面维护一个双向链表
    双向链表是为了增删节点的时间复杂度在O(1)
     */
    class Node {
        int key, value;
        Node prev, next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;
    private int size;
    private Node head, tail;
    private HashMap<Integer, Node> map = new HashMap<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
        head.prev = null;
        tail.next = null;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            //删除节点插入到头结点前
            remove(node);
            add(node);
            return node.value;
        } else {
            return -1;
        }

    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            //重新赋值，并把节点插到头结点
            Node node = map.get(key);
            node.value = value;
            remove(node);
            add(node);
        } else {
            Node node = new Node(key, value);
            if (size < capacity) {
                map.put(key, node);
                add(node);
                size++;
            } else {
                //空间不够，删除尾节点
                remove(tail);
                add(node);
            }
        }
    }

    public void remove(Node node) {
        if (node == tail) {
            Node tmp = tail;
            tail = tail.prev;
            tmp.prev = null;
            tail.next = null;

        } else {
            Node prev = node.prev;
            Node next = node.next;
            prev.next = next;
            next.prev = prev;
        }

    }

    public void add(Node node) {

            head.prev = node;
            node.next = head;
            node.prev = null;

    }


}

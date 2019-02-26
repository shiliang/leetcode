package leetcode.array;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    /*
    在hashmap里面维护一个双向链表
    双向链表是为了增删节点的时间负责度在O(1)
     */
    private class Node {
        Node prev, next;
        int key, value;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    //head,tail的节点为空不存数据
    private Node head = new Node(0,0);
    private Node tail = new Node(0,0);
    private Map<Integer, Node> map = new HashMap<Integer, Node>();

    private int capacity;

    public LRUCache(int _capacity) {
        this.capacity = _capacity;
        head.next = tail;
        tail.prev = head;
    }

    private void insert(Node node) {
        map.put(node.key, node);
        //插入头结点后面
        Node headnext = head.next;  //headnext指向head.next的引用
        head.next = node;
        node.prev = head;
        headnext.prev = node;
        node.next = headnext;
    }

    private void remove(Node node) {
        map.remove(node.key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            remove(node);
            insert(node);
            return node.value;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            remove(map.get(key));
        }
        if (map.size() == capacity) {
            remove(tail.prev);
        }
        insert(new Node(key, value));
    }

}

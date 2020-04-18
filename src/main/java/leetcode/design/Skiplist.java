package leetcode.design;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Skiplist {
    private static final class Node {
        private final int val;
        private Node left, right, up, down;

        private Node(int val) {
            this.val = val;
        }
    }
    private static final double DEFAULT_PROB = 0.5;
    private final Random rand = new Random();
    private List<Node> sentinels = new ArrayList<>(); //层的哨兵结点

    public Skiplist() {
        sentinels.add(new Node(Integer.MIN_VALUE));
    }

    public boolean search(int target) {
        Node smallerOrEquals = getSmallerOrEquals(target);
        return smallerOrEquals.val == target;
    }

    public void add(int num) {
        Node cur = getSmallerOrEquals(num);
        Node toInsert = new Node(num);
        append(cur, toInsert);
        populateLevelUp(toInsert);
    }

    public Node getSentinel() {
        return sentinels.get(sentinels.size() - 1);
    }

    //小于等于target的节点
    private Node getSmallerOrEquals(int target) {
        Node cur = getSentinel();  //获取顶层的哨兵结点
        while (cur != null) {
            if (cur.val == target) {
                return cur;
            } else if (cur.right == null || cur.right.val > target) {
                if (cur.down == null) break; //找到最底层直接退出返回
                cur = cur.down;
            } else {
                cur = cur.right;
            }
        }
        return cur;
    }

    //在prev后面插入节点
    private void append(Node prev, Node cur) {
        Node next = prev.right;
        prev.right = cur;
        cur.left = prev;
        if (next != null) {
            next.left = cur;
            cur.right = next;
        }
    }

    //往上层增加
    private void populateLevelUp(Node toInsert) {
        Node curPrev = toInsert.left, cur = toInsert;

        while (flipCoin()) {
            //跑到了顶层
            while (curPrev.left != null && curPrev.up == null) {
                curPrev = curPrev.left; //跑到顶层的最左边即头结点
            }
            if (curPrev.up == null) {
                Node newSentinel = new Node(Integer.MIN_VALUE);  //增加哨兵结点
                curPrev.up = newSentinel;
                newSentinel.down = curPrev;
                sentinels.add(curPrev.up);
            }
            curPrev = curPrev.up;
            Node tmp = curPrev.right; //在curPrev和curPrev之间插入节点
            Node newNode = new Node(toInsert.val);
            cur.up = newNode;
            newNode.down = cur;
            cur = cur.up;
            curPrev.right = cur;
            cur.left = curPrev;
            if (tmp != null) {
                cur.right = tmp;
                tmp.left = cur;
            }

        }
    }

    //扔硬币看概率是否小于设定的值
    private boolean flipCoin() {
        return rand.nextDouble() < DEFAULT_PROB;
    }

    public boolean erase(int num) {
        Node toRemove = getSmallerOrEquals(num);
        if (toRemove.val != num) return false;
        Node cur = toRemove;
        while (cur != null) {
            Node prev = cur.left, next = cur.right;
            prev.right = next;
            if (next != null) {
                next.left = prev;
            }
            cur = cur.up; //往上层走
        }
        return true;
    }

    private void linkBack(Node p, Node q) {
        p.right = q;
        q.left = p;
    }

    private void linkVertical(Node p, Node q) {
        p.down = q;
        q.up = p;
    }


}

package leetcode.design;

import org.junit.Test;

import static org.junit.Assert.*;

public class SkiplistTest {

    @Test
    public void Search() {
        Skiplist skiplist = new Skiplist();
        skiplist.add(1);
        skiplist.add(2);
        skiplist.add(3);
        skiplist.search(0);   // 返回 false
        skiplist.add(4);
        skiplist.search(1);   // 返回 true
        skiplist.erase(0);    // 返回 false，0 不在跳表中
        skiplist.erase(1);    // 返回 true
        skiplist.search(1);   // 返回 false，1 已被擦除

    }

}
package leetcode;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import leetcode.array.LRUCache;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        LRUCache cache = new LRUCache(2);
        int val = 0;
        cache.put(1, 1);
        cache.put(2, 2);
        val = cache.get(1);       // returns 1
        System.out.println(val);
        cache.put(3, 3);    // evicts key 2
        val = cache.get(2);       // returns -1 (not found)
        System.out.println(val);
        cache.put(4, 4);    // evicts key 1
        val = cache.get(1);       // returns -1 (not found)
        System.out.println(val);
        val = cache.get(3);       // returns 3
        System.out.println(val);
        val = cache.get(4);       // returns 4
        System.out.println(val);

    }
}

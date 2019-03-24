package leetcode.array;


import java.util.HashSet;
import java.util.TreeSet;

//递归解决
public class RecursiveAlgo {
/*    //no79. Word Search，只能是邻接的字符
    public boolean exist(char[][] board, String word) {
        if (board.length == 0) {
            return false;
        }

        int n = board.length;
        int m = board[0].length;
        boolean[][] visited = new boolean[n][m];
        
    }

    private boolean dfs(char[][] board, boolean[][] visited, String word,
                        int n, int m, int x, int y, int index) {
        if (index == word.length()) {
           return true;
        }
        if (x < 0 || x > n - 1 || y < 0 || y > m - 1 ||
        visited[x][y] || board[x][y] != word.charAt(index)) {
            visited[x][y] = true;
            boolean exist = dfs()
        }
    }

    //no215. Kth Largest Element in an Array,使用优先队列建立最大堆
    public int findKthLargest(int[] nums, int k) {

    }*/

    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) return true;

            set.add(nums[i]);
        }

        return true;
    }

    //220. Contains Duplicate III,用TreeSet内部排好序的HashSet
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int len = nums.length;
        if (len <= 1 || k <= 0) return false;

        TreeSet<Integer> treeSet = new TreeSet<>();

        for (int i = 0; i < len; i++) {
            if (treeSet.floor(nums[i]) != null) {
                long farMin = treeSet.floor(nums[i]);
                if (nums[i] - farMin <= t) {
                    return true;
                }
            }

            if (treeSet.ceiling(nums[i]) != null) {
                long leastMax = treeSet.ceiling(nums[i]);
                if (leastMax - nums[i] <= t) {
                    return true;
                }
            }

            treeSet.add(nums[i]);

            //k个元素的窗口向前滑动
            if (treeSet.size() > k) {
                treeSet.remove(nums[i - k]);
            }
        }

        return false;
    }
}

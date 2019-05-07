package leetcode.array;


import java.util.HashSet;
import java.util.TreeSet;

//递归解决
public class RecursiveAlgo {

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

    public int GetNumberOfK(int [] array , int k) {
        int count = 0;
        int length = array.length;
        if (array != null && length > 0) {
            int first = GetFirstK(array, length, k, 0, length - 1);
            int last = GetLastK(array, length, k, 0, length - 1);
            if (first > -1 && last > -1) {
                count = last - first + 1;
            }
        }
        return count;
    }

    //统计一个数字在排序数组中出现的次数。
    public int GetFirstK(int[] array, int length, int k, int start, int end) {
        if (start > end) return -1;
        int middleIndex = (start + end) / 2;
        int middleData = array[middleIndex];
        if (middleData == k) {
            //前半段不含k,中间值是k说明中间的那个值是第一个等于k的值
            if ((middleIndex > 0 && array[middleIndex - 1] != k)
                    || middleIndex == 0) {
                return middleIndex;
            } else {
                end = middleIndex - 1;
            }
        } else if (middleData > k) {
            end = middleIndex - 1;
        } else {
            start = middleIndex + 1;
        }

        return GetFirstK(array, length, k, start, end);
    }

    public int GetLastK(int[] array, int length, int k, int start, int end) {
        if (start > end) return -1;
        int middleIndex = (start + end) / 2;
        int middleData = array[middleIndex];
        if (middleData == k) {
            //最后一个的情况
            if ((middleIndex < length - 1 && array[middleIndex + 1] != k)
                    || middleIndex == length - 1) {
                return middleIndex;
            } else {
                start = middleIndex + 1;
            }
        } else if (middleData > k) {
            end = middleIndex - 1;
        } else {
            start = middleIndex + 1;
        }

        return GetLastK(array, length, k, start, end);
    }
}

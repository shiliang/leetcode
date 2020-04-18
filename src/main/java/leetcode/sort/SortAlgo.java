package leetcode.sort;

import leetcode.list.ListNode;

import java.util.TreeMap;

public class SortAlgo {

    //计数排序
    public void sortColors(int[] nums) {
        int[] count = new int[3];

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int val = count[num] + 1;
            count[num] = val;
        }

        //填充
        int k = 0;
        for (int i = 0; i < 3; i++) {
            while (count[i] > 0) {
                nums[k++] = i;
                count[i]--;
            }
        }


    }

    //非减排序数组,输出旋转数组的最小值
    public int minNumberInRotateArray(int [] array) {
        int len = array.length;
        if (len <= 0) return 0;

        int start = 0;
        int end = len - 1;
        int mid = start;
        while (array[start] >= array[end]) {
            if (end - start == 1) {
                mid = end;
                break;
            }

            mid = (start + end) / 2;
            //如果下标start, mid,end指向的三个数字相等，顺序查找
            if (array[start] == array[end]
            && array[mid] == array[start]) {
                return minInOrder(array, start, end);
            }

            if (array[mid] >= array[start]) {
                start = mid;
            } else if (array[mid] <= array[end]) {
                end = mid;
            }
        }

        return array[mid];
    }

    public int minInOrder(int[] array, int start, int end) {
        int result = array[start];
        for (int i = start + 1; i <= end ; i++) {
            if (result > array[i]) {
                result = array[i];
            }
        }
        return result;
    }

    //三路快排


    //




}

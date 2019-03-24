package leetcode.array;

import sun.tools.jconsole.inspector.Utils;

import java.lang.reflect.Array;
import java.util.*;

public class ArrayAlgo {
    //快排
    public void quickSort(int[] input, int start, int end) {
        if (start >= end) return;

        int index = partition(input, start, end);
        quickSort(input, start, index - 1);
        quickSort(input, index + 1, end);
    }

    public int partition(int[] input, int low, int high) {
        int pivot = input[low];  //选择最低点作为支点
        while (low < high) {
            //从后面开始比较，小于支点的填入前面的坑
            while (low < high && input[high] >= pivot) high--;
            if (low < high) {
                input[low] = input[high];
                low++;
            }

            //从前往后找，大于支点的填入后面的坑
            while (low < high && input[low] <= pivot) low++;
            if (low < high) {
                input[high] = input[low];
                high--;
            }

        }

        //最后把支点的值赋值
        input[low] = pivot;
        return low;  //返回支点的下标
    }

    private void quickSwap(int[] input, int i, int j) {
        int tmp = input[i];
        input[i] = input[j];
        input[j] = tmp;
    }

    //求最大连续子数组采用分治方法
    public int maxSubArray(int[] nums) {
        int low = 0, high = nums.length - 1;
        int max = maxSubArraySum(nums, low, high);
        return max;
    }

    private int maxSubArraySum(int[] nums, int low, int high) {
        if (low >= high) {
            return nums[low];
        }

        int mid = low + (high - low) / 2;
        int leftSum = maxSubArraySum(nums, low, mid);
        int rightSum = maxSubArraySum(nums, mid + 1, high);
        int crossSum = maxCrossMidSum(nums, low, mid, high);
        int maxVal = Math.max(leftSum,rightSum);
        return Math.max(maxVal, crossSum);

    }

    private int maxCrossMidSum(int[] nums, int low, int mid, int high) {
        int sum = 0;
        int leftSum = Integer.MIN_VALUE;
        for (int i = mid; i >= low; i--) {
            sum += nums[i];
            if (sum > leftSum) {
                leftSum = sum;
            }
        }
        sum = 0;
        int rightSum = Integer.MIN_VALUE;
        for (int i = mid+1; i <= high ; i++) {
            sum += nums[i];
            if (sum > rightSum) {
                rightSum = sum;
            }
        }
        return leftSum + rightSum;
    }

    //no.31
    public void nextPermutation(int[] nums) {
        int len = nums.length;
        if (len < 2) return;

        int[] res = new int[len];
        int lastEle = nums[len - 1];
        int k = len - 2;
        //从后往前找出不满足(nums[k],nums[n])非递增的数
        for (; k >= 0; k--) {
            if (lastEle > nums[k]) {
                break;
            } else {
                lastEle = nums[k];
            }

        }

        //当前为最大排列，逆序
        if (k < 0) {
            for (int i = 0; i < (len + 1) / 2; i++) {
                swap(nums, i, len -1 - i);
            }
        } else {
            //在nums[k+1,n-1]中寻找大于nums[k]的最小数
            for (int i = len - 1; i > k ; i--) {
                if (nums[i] > nums[k]) {
                    swap(nums, i, k);
                    break;
                }

            }


            //将nums[k+1,n-1]逆序
            for (int i = k + 1; i < (k + len + 2) / 2; i++) {
                swap(nums, i, k + len - i);  //len-1-(i-(k+1))
            }
        }

        for (int i = 0; i < len; i++) {
            System.out.println(nums[i]);
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    //没有重复数字的全排列,no46
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        backtrack(list, new ArrayList<Integer>(), nums);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tmpList, int[] nums) {
        if (tmpList.size() == nums.length) {
            list.add(new ArrayList<Integer>(tmpList));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (tmpList.contains(nums[i])) continue;  //如果已经在临时链表中的表示已经递归过
                tmpList.add(nums[i]);
                backtrack(list, tmpList, nums);
                tmpList.remove(tmpList.size() - 1);
            }
        }
    }



}

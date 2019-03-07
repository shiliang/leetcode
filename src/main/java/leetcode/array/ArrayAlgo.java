package leetcode.array;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayAlgo {
    //快排
    /*public void quickSort(int[] input, int start, int end) {
        if (start >= end) return;

        int index = partition(input, start, end);
        quickSort(input, start, index - 1);
        quickSort(input, index + 1, end);
    }*/

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
            int index = 0;
            for (int i = len - 1; i > k ; i--) {
                if (nums[i] > nums[k]) {
                    swap(nums, i, k);
                    index = i;
                    break;
                }

            }

            if (index == 0) {
                swap(nums, k, len - 1);
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


}

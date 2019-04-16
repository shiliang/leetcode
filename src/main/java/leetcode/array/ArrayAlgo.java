package leetcode.array;

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

    //数组中出现次数超过长度的一半
    public int MoreThanHalfNum_Solution(int [] array) {
        int len = array.length;
        if (len == 0) return 0;
        int result = array[0];
        int times = 1;
        for (int i = 1; i < len; i++) {
            if (times == 0) {
                result = array[i];
                times = 1;
            } else if (array[i] == result) {
                times++;
            } else {
                times--;
            }
        }
        //检查result是否过半
        int count = 0;
        for (int val: array
             ) {
            if (val == result) count++;
        }
        if (count <= len / 2) return 0;
        return result;
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
                tmpList.remove(tmpList.size() - 1); //把后面的数字除去添加其他数字
            }
        }
    }

    //no.78求数组的子集
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        subsetsBacktrack(lists, new ArrayList<Integer>(), nums, 0);

        return lists;
    }

    private void subsetsBacktrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start) {
        list.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);
            subsetsBacktrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);

        }

    }

    //no.322 换硬币
    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        //dp[i]表示到金额i所需要的最小硬币数
        for (int i = 1; i <= amount; i++ ) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {  //如果不满足条件说明硬币的大小大于所需要的金额
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1); //加上硬币j
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
    
    //求立方根，牛顿迭代法
    public void getCubeRoot(double input) {
        double y = input;
        double x0 = 1.0;
        double x1 = (2*x0 + y/x0/x0) / 3;  //第一次迭代
        while (Math.abs(x1*x1*x1 - y) > 0.00001) {
            x0 = x1;
            x1 = (2*x0 + y/x0/x0) / 3;
        }
        System.out.printf("%.1f",x1);

    }

    //约瑟夫环,编号从0开始
    public int josephus(int n, int m) {
        int[] f = new int[n+1];
        f[1] = 0;  //只有一个人的时候编号0的出列
        for (int i = 2; i <= n; i++) {
            f[i] = (f[i-1] + m) % i;
        }
        return f[n];
    }

    //两个有序的数组找中位数
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        //如果n1大于n2交换一下
        if (n1 > n2) return findMedianSortedArrays(nums2, nums1);

        int k = (n1 + n2 + 1) / 2;
        int left = 0;
        int right = n1;
        //对小的数组进行二分法搜索
        //m1和m2指合成数组中nums1数组占前m1个元素,nums2数组占前m2个元素, m1+m2=k
        while (left < right) {
            int m1 = left + (right - left) / 2;
            int m2 = k - m1;
            //如果小的话num1中m个元素太少继续增加
            if (nums1[m1] < nums2[m2 - 1]) {
                left = m1 + 1;
            } else {
                right = m1;
            }
        }
        int m1 = left;
        int m2 = k - left;
        int c1 = Math.max(m1 <= 0 ? Integer.MIN_VALUE : nums1[m1 - 1],
                m2 <= 0 ? Integer.MIN_VALUE : nums2[m2 - 1] );

        if ((n1 + n2) % 2 == 1) {
            return c1;
        }

        //偶数的中位数
        int c2 = Math.min(m1 >= n1 ? Integer.MAX_VALUE : nums1[m1],
                         m2 >= n2 ? Integer.MAX_VALUE: nums2[m2]);
        return (c1 + c2)  * 0.5;
    }




}

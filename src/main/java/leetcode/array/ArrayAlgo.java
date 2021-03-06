package leetcode.array;

import java.util.*;

public class ArrayAlgo {

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

    //no.31，按数组中的数字顺序求下一个排列
    public void nextPermutation(int[] nums) {
        int len = nums.length;

        for (int i = len - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                int next = find(i - 1, len - 1, nums);
                swap(nums, i - 1, next);
                reverse(nums, i, len - 1);
                return;
            } else if (i == 1) {
                reverse(nums, 0, len - 1);
                return;
            }
        }
    }

    public int find(int i, int j, int[] nums) {
        int next = 0;
        while (j > i) {
            if (nums[j] > nums[i]) {
                next = j;
                break;
            }
            j--;
        }
        return next;
    }

    public void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    //有重复的字符
    public List<String> permuteUnique2(String str) {
        List<String> res = new ArrayList<>();
        if (str == null || str.length() == 0) return res;
        boolean[] used = new boolean[str.length()];  //初始化全部为false
        StringBuilder sb = new StringBuilder();
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        dfs(chars, used, sb, res);
        return res;

    }

    public void dfs(char[] chars, boolean[] used, StringBuilder sb, List<String> res) {
        if (sb.length() == chars.length) {
            res.add(sb.toString());
            return;
        }
        for (int i = 0; i < chars.length; i++) {
            if (used[i]) continue;
            if (i > 0 && chars[i - 1] == chars[i] && !used[i - 1]) continue; //!used[i - 1]为了第一次后面相同的可以加上
            used[i] = true;
            sb.append(chars[i]);
            dfs(chars, used, sb, res);
            used[i] = false;
            sb.deleteCharAt(sb.length() - 1);
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

    //no.416 把一个数组分成两个相同和的子数组
    public boolean canPartition(int[] nums) {
        return false;
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
        int m = nums1.length;
        int n = nums2.length;
        int len = m + n;
        if (n == 0) return findMedianSortedArrays(nums2, nums1);
        int left = -1, right = -1;
        int index1 = 0, index2 = 0;
        for (int i = 0; i <= len / 2; i++) {
            left = right;
            if (index1 < m && (index2 >= n || nums1[index1] < nums2[index2])) {
                right = nums1[index1++];  //index2 >= n说明num2已经遍历完了但是还没到一半
            } else {
                right = nums2[index2++];
            }


        }

        if (len % 2 == 0) return (left + right) / 2.0;
        else return right;
    }

    //丑数，空间换时间
    public int GetUglyNumber_Solution(int index) {
        if (index <= 0) return 0;
        int[] uglyNumbers = new int[index];
        uglyNumbers[0] = 1;
        int nextUglyIndex = 1;
        int pMultiply2 = 0;
        int pMultiply3 = 0;
        int pMultiply5 = 0;

        while (nextUglyIndex < index) {
            int tmpMin = Math.min(uglyNumbers[pMultiply2], uglyNumbers[pMultiply3]);
            int min = Math.min(tmpMin, uglyNumbers[pMultiply5]);

            uglyNumbers[nextUglyIndex] = min;

            while (uglyNumbers[pMultiply2] * 2 <= uglyNumbers[nextUglyIndex]) {
                pMultiply2++;
            }

            while (uglyNumbers[pMultiply3] * 3 <= uglyNumbers[nextUglyIndex]) {
                pMultiply3++;
            }

            while (uglyNumbers[pMultiply5] * 5 <= uglyNumbers[nextUglyIndex]) {
                pMultiply5++;
            }

            nextUglyIndex++;


        }


        return uglyNumbers[nextUglyIndex - 1];
    }

    public ArrayList<Integer> FindNumbersWithSum(int [] array,int sum) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (array == null || array.length < 2) {
            return res;
        }

        int small = 0;
        int big = array.length - 1;
        int currentSum = 0;
        while (small < big) {
            currentSum = array[small] + array[big];
            if (currentSum == sum) {
                res.add(array[small]);
                res.add(array[big]);
                break;
            } else if (currentSum < sum) {
                small++;
            } else {
                big--;
            }
        }
        return res;
    }

    public void reverseString(char[] s) {
        helper(0, s);
    }

    public void helper(int index, char[] s) {
        if (s == null || index >= s.length) return;
        helper(index + 1, s);
        System.out.print(s[index]);
    }


    public int NumberOf1Between1AndN_Solution(int n) {
        if (n < 1) return 0;
        int count = 0;
        int base = 1;  //权重
        int round = n;  //次数
        while (round > 0) {
            int weight = round % 10;  //每一位的值
            round /= 10;
            count += round * base;

            if (weight == 1) {
                count += (n % base) + 1;  //1后面的个数
            } else if (weight > 1) {
                count += base;   //round * base + base
            }
            base *= 10;
        }
        return count;
    }


    //数组中只出现一次的数字
    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        int length = array.length;
        if (array == null || length < 2) {
            return;
        }
        int resultExclusiveOR = 0; //整个数组异或的结果
        for (int i = 0; i < length; i++) {
            resultExclusiveOR ^= array[i];
        }
        //两个不同的数不同位置异或的位置
        int indexOf1 = FindFirstBitIs(resultExclusiveOR);

        for (int i = 0; i < length; i++) {
            if (IsBit1(array[i], indexOf1)) {
                num1[0] ^= array[i];
            } else {
                num2[0] ^= array[i];
            }

        }


    }

    //找出二进制右边第一个1的下标
    public int FindFirstBitIs(int num) {
        int indexBit = 0;
        while (((num & 1) == 0) && (indexBit < 32)) {
            num = num >> 1;
            indexBit++;
        }
        return indexBit;
    }

    //判断num的二进制表示中是否有从右边数起最右边是1的位，把原数组分成两个数组
    boolean IsBit1(int num, int indexBit) {
        num = num >> indexBit;
        return (num & 1) == 1;
    }

    public int longestOnes(int[] A, int K) {
        int zeroCount = 0, start = 0, end = 0, res = 0;
        for (end = 0; end < A.length; end++) {
            if (A[end] == 0) zeroCount++;
            while (zeroCount > K) {
                if (A[start] == 0) zeroCount--;
                start++;
            }
            res = Math.max(res, end - start + 1);
        }
        return res;
    }

    public int numIslands(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int num = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    destroyIsland(grid, i, j); //每次深度优先搜索完都代表一个岛
                    num++;

                }
            }

        }
        return num;
    }

    public void destroyIsland(char[][] grid, int i, int j) {
        grid[i][j] = '0';
        if (i > 0 && grid[i - 1][j] == '1') {
            destroyIsland(grid, i - 1, j);
        }
        if (i < grid.length - 1 && grid[i + 1][j] == '1') {
            destroyIsland(grid, i + 1, j);
        }
        if (j > 0 && grid[i][j - 1] == '1') {
            destroyIsland(grid, i, j - 1);
        }
        if (j < grid[0].length - 1 && grid[i][j + 1] == '1') {
            destroyIsland(grid, i, j + 1);
        }

    }

    public int splitArray(int[] nums, int m) {
        //上界与下界
        long lb = Integer.MIN_VALUE, ub = 0;
        for (int i = 0; i < nums.length; i++) {
            ub += nums[i];
            if (lb < nums[i]) {
                lb = nums[i];
            }
        }
        while (lb < ub) {
            long mid = (lb + ub) / 2;
            int temp = 0, cnt = 1;
            for (int num : nums) {
                temp += num;
                if (temp > mid) {  //换到下一组
                    temp = num;
                    cnt += 1;
                }
            }
            if (cnt > m) {
                lb = mid + 1;
            }else if (cnt <= m) {
                ub = mid;
            }
        }
        return (int) lb;

    }

   //547，朋友圈的个数
   public int findCircleNum(int[][] M) {
        if (M == null || M.length == 0) return 0;
        int n = M.length;
        UnionFind unionFind = new UnionFind(n);
       for (int i = 0; i < n; i++) {
           for (int j = i + 1; j < n; j++) {
               if (M[i][j] == 1) {
                   unionFind.union(i, j);
               }
           }
       }
       return unionFind.getCount();
   }


}

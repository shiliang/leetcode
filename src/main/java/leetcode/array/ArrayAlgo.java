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

    //有重复的字符
    public List<String> permuteUnique(String str) {
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
            if (i > 0 && chars[i - 1] == chars[i] && !used[i - 1]) continue;
            used[i] = true;
            sb.append(chars[i]);
            dfs(chars, used, sb, res);
            used[i] = false;
            sb.deleteCharAt(sb.length() - 1);
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


    //数组中的逆序对
    public int InversePairs(int[] array) {
        int len = array.length;
        if (len == 0 ) return 0;

        int[] copy = new int[len];

        return InversePairsCore(array, copy, 0, len - 1);
    }

    private int InversePairsCore(int[] array, int[] copy, int start, int end) {
        if (start == end) {
            copy[start] = array[start];
            return 0;
        }
        int len = (end - start) / 2; //对半分归并排序
        int left = InversePairsCore(array, copy, start, start + len) % 1000000007;
        int right = InversePairsCore(array, copy, start + len + 1, end) % 1000000007;

        //i初始化为前半段最后一个数字的下标
        int i = start + len;
        //j初始化为后半段最后一个数字的下标
        int j = end;
        int indexCopy = end;
        int count = 0;

        //统计数组之间的逆序对个数,数组内是有序的
        while (i >= start && j >= start + len + 1) {  //两个指针指向数组的末尾
            if (array[i] > array[j]) {
                copy[indexCopy--] = array[i--];  //存在逆序对两个指针往前移动
                count += j - start - len; //因为j是有序的，前面的部分肯定比i小
                if (count >= 1000000007) {
                    count %= 1000000007;
                }
            } else {
                copy[indexCopy--] = array[j--];
            }
        }

        for (; i >= start ; --i) {
            copy[indexCopy--] = array[i];
        }

        for (;  j >= start + len + 1 ; --j) {
            copy[indexCopy--] = array[j];

        }

        //把排序后的数组复制到原数组中
        for (int k = start; k <=end ; k++) {
            array[k] = copy[k];
        }
        return (left + right + count) % 1000000007;

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





}

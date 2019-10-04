package leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    双指针算法
    分两种，快慢指针，左右指针
 */
public class TwoPoints {

    //no42.接雨水
    //ans += min(l_max[i], r_max[i]) - height[i];
    public int trap(int[] height) {
        if (height == null || height.length == 0) return 0;
        int n = height.length;
        int left = 0, right = n - 1;
        int ans = 0;
        //左边和右边的最高点
        int l_max = height[0], r_max = height[n - 1];
        while (left <= right) {
            l_max = Math.max(l_max, height[left]);
            r_max = Math.max(r_max, height[right]);

            if (l_max < r_max) {
                ans += l_max - height[left];
                left++;
            } else {
                ans += r_max - height[right];
                right--;
            }
        }
        return ans;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        //排序数组，从扫描的后一个数用首尾双指针查询,跳过重复的数字
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length < 3) return res;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int target = 0 - nums[i];
                int curSum = nums[left] + nums[right];
                if (curSum == target) {
                    List<Integer> ls = new ArrayList<>();
                    ls.add(nums[i]);
                    ls.add(nums[left]);
                    ls.add(nums[right]);
                    res.add(ls);
                    left++;
                    right--;
                    //遇到重复的跳过
                    while (nums[left] == nums[left - 1]) {
                        left++;
                        if (left > nums.length - 1) break;
                    }
                    while (nums[right] == nums[right + 1]) {
                        right--;
                        if (right < 0) break;
                    }

                } else if (curSum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;

    }

    //no16.最接近三个数之和
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closestSum = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length; i++) {
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int threeSum = nums[i] + nums[left] + nums[right];
                if (Math.abs(threeSum - target) < Math.abs(closestSum - target)) {
                    closestSum = threeSum;
                }
                if (threeSum > target){
                    right--;
                } else if (threeSum < target) {
                    left++;
                } else {
                    return target;
                }
            }
        }
        return closestSum;
    }


}

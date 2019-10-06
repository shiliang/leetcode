package leetcode.DFSBFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    排列问题
 */
public class Permutation {

    //no.526优美排列的个数
    public int countArrangement(int N) {
        int res = 0;
        return res;
    }

    //60.第k个排列
    public String getPermutation(int n, int k) {
        List<String> nums = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            nums.add(String.valueOf(i));
        }
        if (n == 1) return nums.get(0);
        k--; //除去当前的数还有k--个数的排列
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            int kth = kth(n--);  //n - 1的阶乘
            int at = k / kth;  //k个数的下标
            k %= kth;  //选第k个数作为首字母做全排列
            sb.append(nums.get(at));
            nums.remove(at);
        }
        return sb.toString();

    }

    public int kth(int n) {
        int t = 1;
        for (int i = 1; i < n; i++) {
            t *= i;
        }
        return t;
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


    //no.47 全排列II包含相同的数字
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res =new ArrayList<>();
        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];
        permuteUniqueDFS(nums, visited, res, new ArrayList<>());
        return res;
    }

    public void permuteUniqueDFS(int[] nums, boolean[] visited, List<List<Integer>> res, List<Integer> tmp) {
        if (tmp.size() == nums.length) {
            res.add(new ArrayList<>(tmp));
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) continue;
            if (i > 0 && nums[i - 1] == nums[i] && !visited[i - 1]) {
                continue;
            }
            visited[i] = true;
            tmp.add(nums[i]);
            permuteUniqueDFS(nums, visited, res, tmp);
            tmp.remove(tmp.size() - 1);
            visited[i] = false;
        }
    }

    //no.996 一个数组的排列组合，相邻的元素之和是一个完全平方数，对重复的数进行剪枝
    int res = 0;
    public int numSquarefulPerms(int[] A) {
        Arrays.sort(A);
        boolean[] visited = new boolean[A.length];
        numSquarefulPermsDFS(A, new ArrayList<>(), visited);
        return res;
    }

    private boolean squareful(int x, int y) {
        double s = Math.sqrt(x + y);
        return s - (int)s == 0;
    }

    public void numSquarefulPermsDFS(int[] A, List<Integer> tmp, boolean[] visited) {
        if (tmp.size() == A.length) {
            res++;
            return;
        }

        for (int i = 0; i < A.length; i++) {
            if (visited[i]) continue;
            //对重复的元素进行剪枝
            if (i > 0 && (A[i] == A[i - 1] && !visited[i - 1])) continue;
            if (!tmp.isEmpty() && !squareful(tmp.get(tmp.size() - 1), A[i])) continue;
            tmp.add(A[i]);
            visited[i] = true;
            numSquarefulPermsDFS(A, tmp, visited);
            visited[i] = false;
            tmp.remove(tmp.size() - 1);
        }
    }


}

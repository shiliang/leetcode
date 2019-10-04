package leetcode.DFSBFS;

import java.util.ArrayList;
import java.util.List;

/*
    组合问题
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


}

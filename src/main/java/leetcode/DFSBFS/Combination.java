package leetcode.DFSBFS;

import java.util.ArrayList;
import java.util.List;

/*
    组合问题
 */
public class Combination {
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
}

package leetcode.list;

import java.util.ArrayList;
import java.util.List;

public class ListAlgo {

    //链表反转

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        backTrack(lists, new ArrayList<Integer>(), nums, 0);
        return lists;

    }

    private void backTrack(List<List<Integer>> lists, ArrayList<Integer> tempList, int[] nums, int start) {
        lists.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            if(i > start && nums[i] == nums[i-1]) continue;
            tempList.add(nums[i]);
            backTrack(lists, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }
}

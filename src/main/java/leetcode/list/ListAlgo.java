package leetcode.list;

import java.util.*;

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

    //最小的k个数
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> result = new ArrayList<>();
        int length = input.length;
        if (k > length || k == 0) {
            return result;
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k,
                new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o2.compareTo(o1);
                    }
                });
        for (int i = 0; i < length; i++) {
            if (maxHeap.size() != k) {

                maxHeap.offer(input[i]);
            } else if (maxHeap.peek() > input[i]) {
                Integer temp = maxHeap.poll();
                maxHeap.offer(input[i]);
            }
        }

        for (Integer val : maxHeap) {
            result.add(val);
        }

        return  result;
    }

    //栈压入弹出序列
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        if (pushA.length == 0) return false;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0, j = 0; i < pushA.length;) {
            stack.push(pushA[i++]);
            while (j < popA.length && stack.peek() == popA[j]) {
                stack.pop();
                j++;
            }

        }
        return stack.isEmpty();
    }

    public ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> outList = new ArrayList<>();
        int tR = 0, tC = 0;
        int dR = matrix.length - 1;
        int dC = matrix[0].length - 1;
        while (tR <= dR && tC <= dC) {
            printEdge(matrix, tR++, tC++, dR--, dC--, outList);
        }
        return outList;
    }

    public void printEdge(int[][] matrix, int tR, int tC, int dR, int dC,ArrayList<Integer> outList) {
        if (tR == dR) {  //只有一行
            for (int i = tC; i <= dC; i++) {
                outList.add(matrix[tR][i]);
            }
        } else if (tC == dC) { //只有一列
            for (int i = tR; i <= dR; i++) {
                outList.add(matrix[i][tC]);
            }
        } else {
            int curC = tC;
            int curR = tR;
            while (curC != dC) {
                outList.add(matrix[tR][curC]);
                curC++;
            }

            while (curR != dR) {
                outList.add(matrix[curR][dC]);
                curR++;
            }

            while (curC != tC) {
                outList.add(matrix[dR][curC]);
                curC--;
            }

            while (curR != tR) {
                outList.add(matrix[curR][tC]);
                curR--;
            }
        }
    }

    //单链表快排
    public void linklistQuickSort(ListNode root) {
        

    }
}

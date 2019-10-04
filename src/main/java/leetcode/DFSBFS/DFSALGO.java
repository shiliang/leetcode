package leetcode.DFSBFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DFSALGO {
    int res = 0;
    //no.996 一个数组的排列组合，相邻的元素之和是一个完全平方数，对重复的数进行剪枝
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

    //no.79在二维矩阵内搜索是否存在字符串,时间复杂度O(M*N*4^L),空间复杂度O(L),L为字符串的长度
    public boolean exist(char[][] board, String word) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (word.charAt(0) == board[i][j] && existdfs(board, word, i, j, 0, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean existdfs(char[][] board, String word, int i, int j, int index, boolean[][] visited) {
        if (i < 0 || i > board.length - 1 || j < 0 || j > board[0].length - 1
                || index > word.length() - 1 || board[i][j] != word.charAt(index) || visited[i][j]) {
            return false;
        }
        if (index == word.length() - 1) return true;
        visited[i][j] = true;
        if (existdfs(board, word, i + 1, j, index + 1 ,visited)
                || existdfs(board, word, i - 1, j, index + 1 ,visited)
                || existdfs(board, word, i, j + 1, index + 1 ,visited)
                || existdfs(board, word, i, j - 1, index + 1 ,visited)) {
            return true;
        }
        visited[i][j] = false;
        return false;
    }

    public void solveSudoku(char[][] board) {
        boolean[][] rows = new boolean[9][10];  //第i行的1-9有没有被使用过
        boolean[][] cols = new boolean[9][10];  //第j列的1-9有没有被使用过
        boolean[][] boxs = new boolean[9][10];  //在第i个盒子里面的元素n有没有被使用过

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    rows[i][num] = true;
                    cols[j][num] = true;
                    boxs[i / 3 * 3 + j / 3][num] = true;
                }
            }
        }
        solveSudokudfs(board, rows, cols, boxs, 0, 0);
    }

    private boolean solveSudokudfs(char[][] board,boolean[][] rows, boolean[][] cols,
                        boolean[][] boxs, int i, int j) {
        while (board[i][j] != '.') {
            if (++j >= 9) {
                i++;
                j = 0; //换到下一行
            }
            if (i >= 9) return true; //到最下面一行，搜索结束
        }

        for (int num = 1; num <= 9; num++) {
            int boxIndex = i / 3 * 3 + j / 3;
            if (!rows[i][num] && !cols[i][num] && !boxs[i][num]) {
                board[i][j] = (char) ('0' + num);
                rows[i][num] = true;
                cols[j][num] = true;
                boxs[boxIndex][num] = true;
                if (solveSudokudfs(board, rows, cols, boxs, i, j)) {
                    return true;  //寻找下一个需要填充的格子
                } else {
                    //回溯
                    rows[i][num] = false;
                    cols[i][num] = false;
                    boxs[boxIndex][num] = false;
                    board[i][j] = '.';
                }
            }
        }
        return false;
    }

}

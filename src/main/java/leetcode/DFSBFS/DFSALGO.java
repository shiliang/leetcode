package leetcode.DFSBFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DFSALGO {


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

    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<String>();
        helper(s, 0, "", res);
        return res;
    }

    public void helper(String s, int n, String out, List<String> res) {
        if (n == 4) {
            if (s.isEmpty()) res.add(out);
            return;
        }

        for (int k = 1; k < 4; ++k) {
            if (s.length() < k) break;
            int val = Integer.parseInt(s.substring(0, k));
            if (val > 255 || k != String.valueOf(val).length()) continue;
            helper(s.substring(k), n + 1, out + s.substring(0, k) + (n == 3 ? "" : "."), res);
        }
    }

}

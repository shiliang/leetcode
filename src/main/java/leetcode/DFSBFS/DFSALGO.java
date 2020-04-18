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
        List<String> res = new ArrayList<>();
        //生成4段ip地址
        if (s.length() > 12 || s.length() < 4) return res;
        restore(s, new ArrayList<>(), 0, 4, res);
        return res;
    }

    //需要分割的字符串，n为第几段ip地址，ip为当前生成的ip字符串
    public void restore(String s, List<String> path, int begin, int remain, List<String> res) {
        if (begin == s.length()) {
            if (remain == 0) {
                res.add(String.join(".", path));
            }
            return;
        }
        //这一段需要截取的位数，最大为3位
        for (int i = begin; i < begin + 3; i++) {
            //先剪枝
            if (i >= s.length()) break;
            if (remain * 3 < s.length() - i) continue; //剩下的字符按照最大3个一段仍然小于
            if (judgeIpSegment(s, begin, i)) {
                String currentSegment = s.substring(begin, i + 1);
                path.add(currentSegment);
                restore(s,path, i + 1, remain - 1, res);
                path.remove(path.size() - 1);
            }
        }
    }

    public boolean judgeIpSegment(String s, int left, int right) {
        int len = right - left + 1;
        if (len > 1 && s.charAt(left) == '0') return false;
        int res = 0;
        while (left <= right) {
            res = res * 10 + s.charAt(left) - '0';
            left++;
        }
        return res >= 0 && res <= 255;

    }

}

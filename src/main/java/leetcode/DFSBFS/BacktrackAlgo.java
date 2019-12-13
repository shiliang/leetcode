package leetcode.DFSBFS;

import leetcode.tree.TrieTree;

import java.util.*;

/*
    回溯算法
 */
public class BacktrackAlgo {

    //no.51 n个皇后
    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        List<List<String>> res = new ArrayList<>();
        solveNQueensBacktrack(0, board, res);
        return res;

    }

    public void solveNQueensBacktrack(int row, char[][] board, List<List<String>> res) {
        if (row == board.length) {
            List<String> tmp = new ArrayList<>();
            for (int i = 0; i < board.length; i++) {
                String str = new String(board[i]);
                tmp.add(str);
            }
            res.add(tmp);
        } else {
            for (int col = 0; col < board.length; col++) {
                if (!isValid(board, row, col)) continue;
                board[row][col] = 'Q';
                solveNQueensBacktrack(row + 1, board, res);
                board[row][col] = '.';
            }
        }

    }

    public boolean isValid(char[][] board, int row, int col) {
        //检查正上方
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == 'Q') return false;
        }
        //检查右斜上方
        for (int i = row - 1, j = col + 1; i >= 0 && j < board.length ; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }
        //检查左斜上方
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0 ; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }
        //下方不用检查,还没放置皇后
        return true;
    }

    //no.17电话号码的字母组合
    Map<String, String> phone = new HashMap<String, String>() {{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits.length() == 0) {
            return res;
        }
        letterCombinationsBacktrack("", digits, res);
        return res;
    }

    public void letterCombinationsBacktrack(String combination,
                                            String nextDigits,
                                            List<String> res) {
        if (nextDigits.length() == 0) {
            res.add(combination);
        } else {
            String digit = nextDigits.substring(0, 1);
            String letters = phone.get(digit);
            for (int i = 0; i < letters.length(); i++) {
                String letter = letters.substring(i, i + 1);
                letterCombinationsBacktrack(combination + letter,
                        nextDigits.substring(1),
                        res);
            }
        }
    }

    
}

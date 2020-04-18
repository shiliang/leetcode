package leetcode.array;

import org.junit.Test;

import static org.junit.Assert.*;

public class UnionFindTest {

    @Test
    public void solvetest() {
        char[][] board = {{'X', 'X', 'X', 'X'},
                        {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}};
        solve(board);
    }

    public void solve(char[][] board) {
        //把边缘上的'O'形成并查集，在把与边缘'O'所有的'O'联通
        //后面不在并查集里面的'O'改成'X'
        if (board.length == 0) return;
        int m = board.length;
        int n = board[0].length;
        UnionFind uf = new UnionFind(m * n + 1);
        int dummy = m * n; //并查集的根节点
        //将边缘的节点和根节点联通,index = x * n + y
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                uf.union(i * n, dummy);
            }
            if (board[i][n - 1] == 'O') {
                uf.union(i * n + n - 1, dummy);
            }
        }

        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O') {
                uf.union(j, dummy);
            }

            if (board[m - 1][j] == 'O') {
                uf.union(n * (m - 1) + j, dummy);
            }
        }

        int[][] dir = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};
        //将非边缘的节点联通
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (board[i][j] == 'O') {
                    //与四周的O节点连接
                    for (int k = 0; k < 4; k++) {
                        int x = i + dir[k][0];
                        int y = j + dir[k][1];
                        if (board[x][y] == 'O') {
                            uf.union(x * n + y, i * n + j);
                        }
                    }
                }

            }
        }
        //所有不和dummy联通的O节点都要被替换
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (!uf.connected(dummy, i * n + j)) board[i][j] = 'X';
            }
        }

    }

    @Test
    public void island() {
        char[][] grid = {{'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}};
        int res = numIslands(grid);
    }

    public int numIslands(char[][] grid) {
        if (grid.length <= 0 || grid[0].length <= 0) return 0;
        int m = grid.length;
        int n = grid[0].length;
        UnionFind uf = new UnionFind(m * n);
        int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}}; //左边和上边
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    for (int k = 0; k < dir.length; k++) {
                        int x = i + dir[k][0];
                        int y = j + dir[k][1];
                        if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == '1') {
                            uf.union(x * n + y, i * n + j);
                        }
                    }
                }
            }
        }
        return uf.getCount();
    }

}
package leetcode.DFSBFS;

import java.util.LinkedList;
import java.util.Queue;

public class BFSALGO {
    //01-matrix
    public int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    queue.offer(new int[] {i, j});
                    visited[i][j] = true;
                }
            }

        }
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int[] d : dirs) {
                int row = cell[0] + d[0];  //横坐标
                int col = cell[1] + d[1];  //纵坐标
                if (row < 0 || row >= m
                    || col < 0 || col >= n
                    || visited[row][col]) {
                    continue;
                }
                visited[row][col] = true;
                matrix[row][col] = matrix[cell[0]][cell[1]] + 1;
                queue.add(new int[] {row, col});

            }
        }

        return matrix;
    }
}

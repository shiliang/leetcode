package bytedance;

import java.awt.*;
import java.util.ArrayList;

public class BecomeCoder {

    /*
    公司的程序员不够用了，决定把产品经理都转变为程序员以解决开发时间长的问题。

    在给定的矩形网格中，每个单元格都可以用以下三个值之一：

    值0代表空单元格
    值1代表产品经理
    值2代表程序员

    每分钟，任何与程序员（在四个正方向上）相邻的产品经理都会变成程序员。

    返回知道单元格中没有产品经理为止所必须经过的最小分钟数。

    如果没有可能，返回-1.
    case1
    输入：
    0 2
    1 0
    输出：
    -1

    case2
    输入：
    1 2 1
    1 1 0
    0 1 1
    输出：
    3

     */
    public int coder(int[][] matrix) {
        int manage = get1(matrix);
        int m = matrix.length;
        int n = matrix[0].length;
        int count = 0;
        while (manage > 0) {
            ArrayList<Point> list = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] == 2) {
                        list.add(new Point(i, j));
                    }
                }
            }

            for (Point p:
                 list) {
                dfs(p.x, p.y, matrix);
            }
            int nmanage = get1(matrix);
            count++;
            if (manage == nmanage) break; //没有满足条件的
            manage = nmanage;

        }

        return manage == 0 ? count : -1;

    }

    public void dfs(int x, int y, int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        if ((x - 1) >= 0 && matrix[x - 1][y] == 1) {
            matrix[x - 1][y] = 2;
        } else if ((x + 1) < n && matrix[x + 1][y] == 1) {
            matrix[x + 1][y] = 2;
        } else if ((y - 1) >= 0 && matrix[x][y - 1] == 1) {
            matrix[x + 1][y] = 2;
        }
    }

    public int get1(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) count++;
            }
        }
        return count;
    }
}

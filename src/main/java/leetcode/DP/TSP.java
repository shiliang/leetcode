package leetcode.DP;

public class TSP {


    private static int[][] d;
    private static int[] bits;

    /**
     * @param args
     */
    public static void main(String[] args) {
        int[][] c = {
                {0, 3, 6, 7},
                {5, 0, 2, 3},
                {6, 4, 0, 2},
                {3, 7, 5, 0}
        };
        getMinCost(c);

    }

    /*动态规划 实现TSP问题
     *
     * 用二进制表示集合（比如1100，表示在当前集合中：节点1、节点2不存在，节点3和节点4存在）
     *
     * 从上到下求，递归
     */
    public static void getMinCost(int[][] a) {
        int n = a.length;
        bits = new int[n];
        for (int i = 0; i < n; i++) {
            bits[i] = 1 << i;
        }

        d = new int[1000][n];

        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d[0].length; j++) {
                d[i][j] = -1;
            }
        }
        int t = 1;
        for (int i = 1; i < n; i++) {  //初始化这些值：{1}->1   {2}->2    {3}-3
            d[t << (i - 1)][i] = a[0][i];
        }

        int res = Integer.MAX_VALUE;

        t = 0;
        for (int i = 1; i < n; i++) {
            t = t << 1;
            t = t | 1;
        }
        for (int i = 1; i < n; i++) {
            int r = getD(i, t, n, a);
            if (r + a[i][0] < res) {
                res = r + a[i][0];
            }
        }


        System.out.println(res);
    }

    private static int getD(int i, int t, int n, int[][] a) {
        int min = Integer.MAX_VALUE;

        if (d[t][i] != -1) {
            return d[t][i];
        }

        t = t & (~bits[i - 1]);

        for (int j = 1; j < n; j++) {
            int tt = t & bits[j - 1];   //遍历每个集合中的节点
            if (tt > 0) {
                int res = getD(j, t, n, a);
                if (res + a[j][i] < min) {
                    min = res + a[j][i];
                }
            }
        }

        d[t][i] = min;
        return d[t][i];
    }


}

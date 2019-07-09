package leetcode.DP;

import java.util.ArrayList;

public class TSP {

    /**
     * @param args
     */
    public static void main(String[] args) {
        int[][] c = {
                {0, 2, 6, 5},
                {2, 0, 4, 4},
                {6, 4, 0, 2},
                {5, 4, 2, 0}
        };

    }

    /*动态规划 实现TSP问题
     *
     * 用二进制表示集合（比如1100，表示在当前集合中：节点1、节点2不存在，节点3和节点4存在）
     *
     * 从上到下求，递归
     */
    public int travel(int[][] paths) {
        int len = paths.length;
        return 0;
    }



}

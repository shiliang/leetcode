package leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//区间问题
public class intervals {

    //56,区间合并
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));
        List<int[]> res = new ArrayList<>();
        int[] prev = null;
        for (int[] inter : intervals) {
            if (prev == null || prev[1] <inter[0]) {
                prev = inter;
                res.add(prev);
            } else if (prev[1] < inter[1]) {
                prev[1] = inter[1];
            }
        }
        return res.toArray(new int[res.size()][2]);
    }

    //57,插入区间
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        List<int[]> res = new ArrayList<>();
        int[][] arr = new int[n+1][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                arr[i][j] = intervals[i][j];
            }
        }
        arr[n][0] = newInterval[0];
        arr[n][1] = newInterval[1];
        Arrays.sort(arr, (a, b) -> (a[0] - b[0]));
        int[] prev = null;
        for(int[] inter : arr) {
            if (prev == null || prev[1] < inter[0]) {
                prev = inter;
                res.add(prev);
            } else if (prev[1] < inter[1]) {
                prev[1] = inter[1];
            }
        }
        return res.toArray(new int[res.size()][2]);

    }


}

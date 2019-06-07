package leetcode.array;

import java.util.ArrayList;
import java.util.List;

public class ConvexHullAlgo {

    //leetcode 587 erect the fence
    //jarvis步长算法,开始的时候先寻找最左边的点,下一个点看与横坐标夹角最小，再下一个点两个向量夹角最小
    public List<Point> outerTrees(int[][] points) {

        //转换成坐标
        int m = points.length;
        List<Point> pointList = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            Point ps = new Point();
            for (int j = 0; j < 2; j++) {
                if (j == 0) {
                    ps.x = points[i][j];
                } else {
                    ps.y = points[i][j];
                }
            }
            pointList.add(ps);
        }

        return pointList;
    }
}

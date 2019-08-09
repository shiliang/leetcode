package leetcode.geometry;

import java.awt.*;
import java.util.*;

public class GeoAlgo {

    public boolean isRectangleCover(int[][] rectangles) {
        int area = 0;
        Set<Point> corners = new HashSet<>();
        for (int[] rec : rectangles) {
            Point p1 = new Point(rec[0],rec[1]); //左下角开始逆时针
            Point p2 = new Point(rec[2],rec[1]);
            Point p3 = new Point(rec[2],rec[3]);
            Point p4 = new Point(rec[0],rec[3]);
            Point[] arr = new Point[4];
            arr[0] = p1;
            arr[1] = p2;
            arr[2] = p3;
            arr[3] = p4;
            for (int i = 0; i < 4; i++) {
                if (corners.contains(arr[i])) {
                    corners.remove(arr[i]);
                } else {
                    corners.add(arr[i]);
                }
            }
            area += (p3.x - p1.x) * (p3.y - p1.y);


        }
        if (corners.size() != 4) return false;
        //找出最左下角和右上角的两个点

        int[] xarr = new int[4];
        int[] yarr = new int[4];
        int index = 0;
        for (Point point : corners) {
            xarr[index] = point.x;
            yarr[index] = point.y;
            index++;
        }
        Arrays.sort(xarr);
        Arrays.sort(yarr);
        int x1 = xarr[0], y1 = yarr[0];
        int x2 = xarr[3], y2 = yarr[3];
        int sumArea = (x2 - x1) * (y2 - y1);
        return sumArea == area;
    }
}

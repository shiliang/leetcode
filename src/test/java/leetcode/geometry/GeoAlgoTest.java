package leetcode.geometry;

import org.junit.Test;

import static org.junit.Assert.*;

public class GeoAlgoTest {

    @Test
    public void isRectangleCover() {
        int[][]  rectangles = {
            {1,1,3,3},
            {3,1,4,2},
            {3,2,4,4},
            {1,3,2,4},
            {2,3,3,4}};
        GeoAlgo geoAlgo =new GeoAlgo();
        geoAlgo.isRectangleCover(rectangles);
    }
}

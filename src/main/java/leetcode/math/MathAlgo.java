package leetcode.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MathAlgo {
    class Fraction {
        long n, d;

        public Fraction(long n, long d) {
            this.n = n;
            this.d = d;
        }
    }
    public boolean isRationalEqual(String S, String T) {
        return false;
    }

    //求n以内的所有素数,采用刷选法，如果是素数则其所有的倍数都是合数
    public void getShuSu(int n) {



    }

    //二分法求数的根号值
    public double sqrt(int x) {
        double left = 0;
        double right = x;
        double precise = 1e-6;
        double mid = 0;
        while (Math.abs(right - left) >= precise) {
            mid = left + (right - left) / 2;
            if (mid * mid > x) {
                right = mid;
            } else if (mid * mid < x) {
                left = mid;
            }
        }
        return mid;
    }


}

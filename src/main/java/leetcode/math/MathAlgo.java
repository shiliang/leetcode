package leetcode.math;

import java.text.DecimalFormat;
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
    /*
        先找出一个区间 [a, b]，使得f(a)与f(b)异号。
        求该区间的中点 m = (a+b)/2，并求出 f(m) 的值。
        若 f(m) * f(a) < 0 则取 [a, m] 为新的区间, 否则取 [m, b].
        重复第2和第3步至理想精确度为止。
     */
    public double sqrt(int x) {
        double left = 0;
        double right = x;
        double precise = 0.000001;
        double mid = 0;
        DecimalFormat df = new DecimalFormat(".000000");
        while (Math.abs(right - left) >= precise) {
            mid = left + (right - left) / 2;
            if (mid * mid > x) {
                right = mid;
            } else if (mid * mid < x) {
                left = mid;
            }
        }
        return Double.parseDouble(df.format(mid));
    }


}

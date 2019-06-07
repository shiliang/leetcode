import com.sun.deploy.util.ArrayUtil;
import com.sun.tools.javac.util.ArrayUtils;

import java.util.*;

public class Exercise {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;
        return matchHelper(s, 0, p, 0);

    }

    public boolean matchHelper(String s, int ps, String p, int pp) {
        if (s.length() <= ps && p.length() <= pp) {
            return true;
        }

        if (s.length() > ps && p.length() <= pp) {
            return false;
        }

        //下一个字符是*
        if (pp + 1 < p.length() && p.charAt(pp + 1) == '*') {
            if (ps >= s.length())
                return matchHelper(s, ps, p, pp +2);
            else {
                if (p.charAt(pp) == s.charAt(ps) || p.charAt(pp) == '?') {
                    return matchHelper(s, ps + 1, p, pp + 2)
                            || matchHelper(s, ps + 1, p, pp)
                            || matchHelper(s, ps, p, pp + 2);
                } else {
                    return matchHelper(s, ps, p, pp + 2);
                }

            }
        }

        //下一个字符不是*
        if (ps >= s.length() )
            return false;
        else if (s.charAt(ps) == p.charAt(pp) || p.charAt(pp) == '?'){
            return matchHelper(s, ps + 1, p, pp + 1);
        }

        return false;
    }

    public String PrintMinNumber(int[] numbers) {
        Integer[] nums = new Integer[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            nums[i] = numbers[i];
        }
        Arrays.sort(nums, new Comparator<Integer>() {
            public int compare(Integer str1, Integer str2) {
                String s1 = str1 + "" + str2;
                String s2 = str2 + "" + str1;
                return s1.compareTo(s2);
            }
        });
        int len = numbers.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len ; i++) {
            sb.append(Integer.toString(nums[i]));
        }
        return sb.toString();
    }


}

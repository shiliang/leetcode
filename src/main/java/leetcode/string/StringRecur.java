package leetcode.string;

import java.util.HashMap;
import java.util.Map;

/*
    使用递归处理字符串
    记忆化递归
 */
public class StringRecur {
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        char[] chars = countAndSay(n - 1).toCharArray();
        int count = 0;
        char cur = chars[0];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if (cur != chars[i]) {
                sb.append(count);  //把前面的加入字符串
                sb.append(cur);
                cur = chars[i];
                count = 1;
            } else {
                count++; //和当前字符相同的话
            }
        }
        //最后一个字符
        if (count > 0) {
            sb.append(count + String.valueOf(cur));
        }
        return sb.toString();
    }

    //no.91求一串数字的解码方式，前一个或者两个可以转化成为26个字母
    public int numDecodingsRecu(String s) {
        if (s.length() == 0) return 0;
        Map<String, Integer> count = new HashMap<>();  //记忆化递归省去重复计算
        count.put("", 1);  //空串
        return ways(s, count);
    }

    public int ways(String s, Map<String, Integer> count) {
        if (count.containsKey(s)) return count.get(s);
        if (s.charAt(0) == '0') return 0;
        if (s.length() == 1) return 1;
        int w = ways(s.substring(1), count);
        int prefix = Integer.parseInt(s.substring(0, 2));
        if (prefix <= 26) {
            w += ways(s.substring(2), count);
        }
        count.put(s, w);
        return w;
    }


}

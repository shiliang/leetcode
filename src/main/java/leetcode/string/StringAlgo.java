package leetcode.string;

import java.util.HashMap;
import java.util.Map;
import java.util.*;

public class StringAlgo {


    //四则运算
    public int calculate(String strExpression)

    {
        int len = strExpression.length();
        if (len == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        char sign = '+';
        for (int i = 0; i < len; i++) {
            if (Character.isDigit(strExpression.charAt(i))) {
                num = num * 10 + strExpression.charAt(i) - '0'; //如果是多位数

            }
            if ((!Character.isDigit(strExpression.charAt(i))) &&
                ' ' != strExpression.charAt(i) || i == len -1) {
                if (sign == '-') {
                    stack.push(-num);
                }
                if (sign == '+') {
                    stack.push(num);
                }
                if (sign == '*') {
                    stack.push(stack.pop() * num);
                }
                if (sign == '/') {
                    stack.push(stack.pop() / num);
                }

                sign = strExpression.charAt(i);
                num = 0;
            }
        }
        int re = 0;
        for (int i : stack) {
            re += i;
        }
        return re;

    }

    //394. Decode String
    public String decodeString(String s) {
        if (s.length() == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int digit_begin = i;
                while (s.charAt(i) != '[') i++;
                //算数字的值
                int num = Integer.valueOf(s.substring(digit_begin, i));
                int count = 1;
                int str_begin = i + 1;
                i++;
                while (count != 0) {
                    if (s.charAt(i) == '[') count++;
                    else if (s.charAt(i) == ']') count--;
                    i++;
                }
                i--;
                //括号里面的字符串递归
                String str = decodeString(s.substring(str_begin, i));
                for (int j = 0; j < num; j++) {
                    sb.append(str);
                }
            } else {
                sb.append(c);
            }

        }

        return sb.toString();
    }

    //翻转字符串
    public String ReverseSentence(String str) {
        char[] chars = str.toCharArray();
        reverse(chars, 0, chars.length -1);
        int blank = -1;
        int nextBlank = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') {
                nextBlank = i;
                reverse(chars, blank + 1, nextBlank - 1);
                blank = nextBlank;
            }
        }
        //最后一个单词
        reverse(chars, blank + 1, chars.length - 1);
        return new String(chars);
    }

    private void reverse(char[] chars, int low, int high) {
        while (low < high) {
            char tmp = chars[low];
            chars[low] = chars[high];
            chars[high] = tmp;
            low++;
            high--;
        }
    }

    //循环左移
    public String LeftRotateString(String str,int n) {
        if (str.length() <= 0) return "";
        char[] chars = str.toCharArray();
        reverse(chars, 0, n -1);
        reverse(chars, n, chars.length - 1);
        reverse(chars,0, chars.length - 1);
        return new String(chars);

    }

    //正则表达式匹配,.表示任意一个字符,*表示前面的字符可以出现任意多次从0到n次
    public boolean match(char[] str, char[] pattern)
    {
        if (str == null || pattern == null) return false;
        return matchHelper(str, 0, pattern, 0);
    }

    public boolean matchHelper(char[] str, int s, char[] pattern, int p) {
        if (str.length <= s && pattern.length <= p) {
            return true;
        }
        //模式完了，字符串还有
        if (str.length > s && pattern.length <= p) {
            return false;
        }
        //pattern的下一个字符是*号
        if (p + 1 < pattern.length && pattern[p + 1] == '*') {
            //字符串匹配完
            if (s >= str.length) return matchHelper(str, s, pattern, p + 2); //*当做0处理
            else {
                if (pattern[p] == str[s] || pattern[p] == '.') {
                    //当前位置匹配完成，移动到下个模式串
                    return matchHelper(str, s + 1, pattern, p + 2) //匹配一个字符
                            || matchHelper(str, s + 1, pattern, p)  //匹配多个字符
                            || matchHelper(str, s, pattern, p + 2); //*当做0处理
                } else {
                    return matchHelper(str, s, pattern, p + 2);
                }
            }

        }

        //pattern的下一个字符不是*
        if (s >= str.length) return false;
        else {
            if (str[s] == pattern[p] || pattern[p] == '.') {
                return matchHelper(str, s + 1, pattern, p + 1);
            }
        }
        return false;
    }

    public boolean isNumeric(char[] str) {
        if (str == null || str.length == 0) return false;
        int index = 0;
        //第一位是正数或者负数
        if (str[index] == '+' || str[index] == '-') {
            index++;
        }

        if (index == str.length) return false;
        boolean numeric = true;
        index = scanDigits(str, index);
        if (index < str.length) {
            if (str[index] == '.') {
                //判断后面是否为小数位
                index++;
                index = scanDigits(str, index);
                if (index < str.length) {
                    if (str[index] == 'e' || str[index] == 'E') {
                        index++;
                        if (index == str.length) {
                            numeric = false;
                        } else {
                            numeric = isExponential(str, index);
                        }
                    } else {
                        numeric = false;
                    }
                } else {
                    numeric = true;
                }
            } else if (str[index] == 'e' || str[index] == 'E') {
                index++;
                if (index == str.length) {
                    numeric = false;
                } else {
                    numeric = isExponential(str, index);
                }
            } else {
                numeric = false;
            }
        }
        return numeric;

    }

    //判断是否是指数，指数部分不允许有小数
    private boolean isExponential(char[] str, int index) {
        //index指向e之后的第一个字符
        if (str[index] == '+' || str[index] == '-') {
            index++;
        }

        //到达第一个非数字字符或者到达数组尾部
        while (index < str.length && (str[index] >= '0' && str[index] <= '9')) {
            index++;
        }

        return index == str.length;

    }

    //扫描数字，返回第一位不为数字的下标
    private int scanDigits(char[] str, int index) {
        while (index < str.length && (str[index] >= '0' && str[index] <= '9')) {
            index++;
        }
        return index;
    }

    //'?'表示匹配任意一个字符，*表示匹配任意多字符包括空字符
    public boolean comparison(String s, String p) {
        int ps = 0, pp = 0; //字符串两个下标
        int starIdx = -1;  //记录最后出现的*下标
        int match = 0;  //记录starIdx出现时候字符串对应的下标
        while (ps < s.length()) {
            if (pp < p.length() &&
                    (p.charAt(pp) == '?' || s.charAt(ps) == p.charAt(pp))) {
                ps++;
                pp++;
            } else if (pp < p.length() && p.charAt(pp) == '*') {
                starIdx = pp;  //当前字符是*,默认匹配0个字符
                match = ps;
                pp++;
            } else if (starIdx != -1) {  //如果不匹配则找到前面的*出现地方，让*匹配多个字符
                pp = starIdx + 1;  //目的是让前面的*匹配多个字符
                match++;
                ps = match;
            } else {
                return false;
            }
        }

        //字符串匹配完，模式串没有匹配完
        while (pp < p.length() && p.charAt(pp) == '*') {
            pp++;
        }

        return pp == p.length();
    }

    //最长没有重复字符的子序列
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map= new HashMap<>();
        int start=0, len=0;
        // tmsmdut  i = s.length() - 1
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                if (map.get(c) >= start) //防止前面有相同的字符来更新比如a
                    start = map.get(c) + 1;
            }
            len = Math.max(len, i-start+1);
            map.put(c, i);
        }

        return len;
    }
    public String multiply(String num1, String num2) {
        //本位是i+j+1,进位是i+j
        int n1 = num1.length() - 1;
        int n2 = num2.length() - 1;
        if (n1 < 0 || n2 < 0) return "";
        int[] mul = new int[n1 + n2 + 2];
        for(int i = n1; i >= 0; --i) {
            for(int j = n2; j >= 0; --j) {
                int bitmul = (num1.charAt(i)-'0') * (num2.charAt(j)-'0');
                bitmul += mul[i+j+1];

                mul[i+j] += bitmul / 10;
                mul[i+j+1] = bitmul % 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        int i = 0;
        // 去掉前导0
        while(i < mul.length-1 && mul[i] == 0)
            i++;
        for(; i < mul.length; ++i)
            sb.append(mul[i]);
        return sb.toString();
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++)
            while (strs[i].indexOf(prefix) != 0) {  //从头开始包含子串
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        return prefix;
    }

    //763,划分字母区间
    public List<Integer> partitionLabels(String S) {
        int[] last_index = new int[128];
        for (int i = 0; i < S.length(); i++) {
            last_index[S.charAt(i)] = i;   //记录每个字符出现的最后
        }
        List<Integer> res = new ArrayList<>();
        int start = 0;
        int end = 0;
        for (int i = 0; i < S.length(); i++) {
            end = Math.max(end, last_index[S.charAt(i)]);
            if (end == i) {
                res.add(end - start + 1);
                start = end + 1;
            }
        }
        return res;

    }

    //中文字符串转long数字
    public  long parseChineseNumber(String s){
        long  num = 0;
        StringBuilder  sb = new StringBuilder();
        for(int i = 0 ; i< s.length();i++){
            switch(s.charAt(i)){
                case '零': sb.append('0');break;
                case '一': sb.append('1');break;
                case '二': sb.append('2');break;
                case '三': sb.append('3');break;
                case '四': sb.append('4');break;
                case '五': sb.append('5');break;
                case '六': sb.append('6');break;
                case '七': sb.append('7');break;
                case '八': sb.append('8');break;
                case '九': sb.append('9');break;

            }
        }
        //System.out.println(sb+"****");
        long  wei = 1;
        for(int i = sb.length() -1;i>=0 ;i--){
            num += (sb.charAt(i)-'0')*wei;
            System.out.println("num = " + num);
            wei = wei * 10;
        }
        return  num ;
    }

    //输入一组数据，求出现次数topk


    //no.468 验证是否是有效的ip地址
    public String validIPAddress(String IP) {
        if (IP.equals("")) return "Neither";
        if (isValidIPV4(IP)) return "IPv4";
        if (isValidIPV6(IP)) return "IPv6";
        return "Neither";
    }

    public boolean isValidIPV4(String ip) {
        if (ip.charAt(0) == '.' || ip.charAt(ip.length() - 1) == '.') return false;
        String[] tokens = ip.split("\\.");
        if (tokens.length != 4) return false;
        for (String token : tokens) {
            try {
                if (token.startsWith("0") && token.length() > 1) return false;
                if (Integer.parseInt(token) > 255 || token.charAt(0) == '-' || token.charAt(0) == '+') return false;

            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    public boolean isValidIPV6(String ip) {
        if (ip.charAt(0) == ':' || ip.charAt(ip.length() - 1) == ':') return false;
        String[] tokens = ip.split("\\:");
        if (tokens.length != 8) return false;
        for (String token : tokens) {
            if (token.length() > 4 || token.length() == 0) return false;
            for (int i = 0; i < token.length(); i++) {
                if ((token.charAt(i) >= '0' && token.charAt(i) <= '9')
                    || token.charAt(i) >= 'a' && token.charAt(i) <= 'f'
                    || token.charAt(i) >= 'A' && token.charAt(i) <= 'F') {
                    continue;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    //no.30 以index为起点的子串，包含了word中所有的单词
    public List<Integer> findSubstring(String s, String[] words) {
        Map<String, Integer> counts = new HashMap<>();
        List<Integer> indexs = new ArrayList<>();
        if (s == null || words == null || s.length() == 0 || words.length == 0) return indexs;
        for (String word : words) {
            counts.put(word, counts.getOrDefault(word, 0) + 1);
        }
        int n = s.length(), num = words.length, len = words[0].length();
        for (int i = 0; i <= n - num * len; i++) {
            Map<String, Integer> seen = new HashMap<>(); //记录一次数组的遍历所记录的单词
            int j = 0;
            while (j < num) {  //遍历单词数组
                String word = s.substring(i + j * len, i + (j + 1) * len); //往前一个单词的长度
                if (counts.containsKey(word)) {
                    seen.put(word, seen.getOrDefault(word, 0) + 1);
                    if (seen.get(word) > counts.getOrDefault(word, 0)) {  //如果在这个范围内出现某单词大于数组中的个数如goodgood
                        break;
                    }
                } else {
                    break;
                }
                j++;
            }
            if (j == num) {
                indexs.add(i);
            }
        }
        return indexs;
    }

}

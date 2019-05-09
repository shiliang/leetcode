package leetcode.string;

import java.util.Stack;

public class StringAlgo {

    public String validIPAddress(String IP) {
        if (IP.contains(".")) {
            return IPV4(IP);
        }

        if (IP.contains(":")) {
            return IPV6(IP);
        }

        return "Neither";
    }

    private String IPV4(String IP) {
        if (IP.charAt(IP.length() - 1) == '.') {
            return "Neither";
        }
        String[] strings = IP.split("\\.");
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].startsWith("0")) {
                return "Neither";
            }
            try {
                if (Integer.parseInt(strings[i]) > 255 ||
                        Integer.parseInt(strings[i]) < 0) {
                    return "Neither";
                }
            } catch (Exception e) {
                return "Neither";
            }

        }
        return "IPv4";
    }

    private String IPV6(String IP) {
        if (IP.charAt(IP.length() - 1) == ':') {
            return "Neither";
        }
        String[] strings = IP.split(":");
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].length() > 4 ) {
                return "Neither";
            }
        }
        return "IPv6";
    }

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
}

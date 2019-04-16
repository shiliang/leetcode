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
}

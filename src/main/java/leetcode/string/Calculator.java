package leetcode.string;

import java.util.Stack;

public class Calculator {
    //224,基本计算器,符号只有加减左括号右括号
    public int basiccalculate(String s) {
        int res = 0;
        return res;
    }

    //772,加减乘除左右括号
    public int calculate(String s) {
        s = s.replaceAll(" ", "");
        if (s.length() == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        char sign = '+';
        int res = 0, num = 0, i = 0;
        while (i < s.length()) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');
            }
            if (ch == '(') {
                int j = findClosing(s.substring(i)); //找到后面的对应闭括号
                num = calculate(s.substring(i + 1, i + j));
                i += j;
            }

            if (i == s.length() - 1 || !Character.isDigit(ch)) {
                switch (sign) {
                    case '+':
                        stack.push(num); break;
                    case '-':
                        stack.push(-num); break;
                    case '*':
                        stack.push(stack.pop() * num); break;
                    case '/':
                        stack.push(stack.pop() / num); break;
                }
                num = 0;
                sign = ch;
            }
            i++;
        }
        while (!stack.isEmpty()) res += stack.pop();
        return res;
    }

    public int findClosing(String s) {
        int leve1 = 0; //有可能有嵌套的括号
        int i = 0;
        for (i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') leve1++;
            else if (s.charAt(i) == ')') {
                leve1--;
                if (leve1 == 0) break;
            }
        }
        return i;
    }

}

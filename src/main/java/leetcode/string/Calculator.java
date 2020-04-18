package leetcode.string;

import java.util.Stack;

public class Calculator {


    //224,基本计算器,符号只有加减左括号右括号
    public int basiccalculate(String s) {
        Stack<Integer> nums = new Stack<>();   //存储操作数
        Stack<Character> ops = new Stack<>(); //存储操作符
        String str = s.replace(" ", "");
        int i = 0;
        while (i < str.length()) {
            //3个分枝，'(',其他三个符号'+''-'')',数字
            if (str.charAt(i) == '(') {
                ops.push('(');
                i++;
            } else if (str.charAt(i) == '+' || str.charAt(i) == '-' || str.charAt(i) == ')') {
                while (!ops.isEmpty() && (ops.peek() == '+' || ops.peek() == '-')) {
                    int b = nums.pop();
                    int a = nums.pop();
                    char op = ops.pop();
                    if (op == '+') {
                        nums.push(a + b);
                    } else if (op == '-') {
                        nums.push(a - b);
                    }
                }
                if (str.charAt(i) == ')') {
                    if (!ops.isEmpty() && ops.peek() == '(') ops.pop();
                } else {
                    ops.push(str.charAt(i));
                }
                i++;
            } else {
                int r = 0;
                while (i < str.length() && Character.isDigit(str.charAt(i))) {
                    r = 10 * r + (str.charAt(i++) - '0');
                }
                nums.push(r);
            }
        }
        //计算剩下的数
        while (!ops.isEmpty()) {
            int b = nums.pop();
            int a = nums.pop();
            char op = ops.pop();
            if (op == '+') {
                nums.push(a + b);
            } else if (op == '-') {
                nums.push(a - b);
            }
        }
        return nums.peek();
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

package huawei;

import java.util.*;

public class Main {

    static class Node{
        char data;
        Node left;
        Node right;

    }

    static Node Create(Node root, String input) {
        char[] str = input.toCharArray();
        Node p = root;
        Stack<Node> stack = new Stack();
        int k = 0;
        for (int i = 0; i < input.length(); i++) {
            char ch = str[i];
            switch (ch) {
                case '(': stack.push(p); k = 1; break;
                case ')': stack.pop(); break;
                case ',': k = 2; break;
                default:
                    p = new Node();
                    p.data = ch;
                    p.left = p.right = null;
                    if (root == null) root = p;
                    else {
                        if (k == 1) stack.peek().left = p;
                        else stack.peek().right = p;
                    }
            }
        }
        return root;
    }
    static StringBuilder sb = new StringBuilder();
    static void print(Node root) {
        if (root == null) return;
        print(root.left);
        sb.append(root.data);
        print(root.right);
    }
    static Node root = null;
    static String solution(String input) {
        Node q = Create(root, input);
        print(q);
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String res;

        String _input;
        try {
            _input = in.nextLine();
        } catch (Exception e) {
            _input = null;
        }

        res = solution(_input);
        System.out.println(res);


    }



}

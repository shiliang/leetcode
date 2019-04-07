package huawei;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Intership {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<String> s = new LinkedList<>();
        while (scan.hasNextLine()) {
            String a = scan.nextLine();
            s.add(a);
        }

        int linenum = Integer.parseInt(s.get(0));

        String str1 = s.get(1);
        String str2 = s.get(2);
        String[] num1 = str1.split(",");
        String[] num2 = str2.split(",");
        List<Integer> list = new LinkedList<>();
        int index1 = 0;
        int len1 = num1.length;
        int index2 = 0;
        int len2 = num2.length;

        while (len1 > index1 && len2 > index2) {
            int count1 = linenum;
            int count2 = linenum;
            for (int i = index1; i < len1 && count1 > 0; i++) {
                int val = Integer.parseInt(num1[i]);
                list.add(val);
                count1--;
                index1++;
            }

            for (int j = index2; j < len2 && count2 > 0; j++) {
                int val = Integer.parseInt(num2[j]);
                list.add(val);
                count2--;
                index2++;
            }


        }

        if (index1 < len1) {
            for (int i = index1; i < len1; i++) {
                int val = Integer.parseInt(num1[i]);
                list.add(val);
            }
        }

        if (index2 < len2) {
            for (int i = index2; i < len2; i++) {
                int val = Integer.parseInt(num2[i]);
                list.add(val);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            sb.append(","+list.get(i));
        }
        String res = sb.toString();
        System.out.println(res);
    }

    /*public void sum() {
        Scanner scan = new Scanner(System.in);
        // 从键盘接收数据

        // nextLine方式接收字符串
        System.out.println("nextLine方式接收：");
        // 判断是否还有输入
        if (scan.hasNextLine()) {
            String str2 = scan.nextLine();
            System.out.println("输入的数据为：" + str2);
        }
        scan.close();
    }*/

    /*public void sub() {
        *//* Scanner scan = new Scanner(System.in);
        // 从键盘接收数据

        // next方式接收字符串
        System.out.println("next方式接收：");
        // 判断是否还有输入
        if (scan.hasNext()) {
            String str1 = scan.next();
            System.out.println("输入的数据为：" + str1);
        }
        scan.close();*//*
    }*/

}

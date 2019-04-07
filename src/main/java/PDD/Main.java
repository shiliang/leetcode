package PDD;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> input = new ArrayList<>();
        int num = 0;
        //中断输入ctrl D
        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            String[] strlist = str.split(" ");
            for (int i = 0; i < strlist.length; i++) {
                input.add(Integer.parseInt(strlist[i]));
            }
        }
        int max1 = 0, max2 = 0, max3 = 0, min1 = 0, min2 = 0;
        for (int i = 0; i < input.size(); i++) {
            num = input.get(i);
            if (num > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max3 = max2;
                max2 = num;
            } else if (num > max3) {
                max3 = num;
            } else if (num < min1) {
                min2 = min1;
                min1 = num;
            } else if (num < min2) {
                min2 = num;
            } else {
                continue;
            }
        }
        int res = Math.max(max1*max2*max3, max1*min1*min2);
        System.out.println(res);

    }
}

package huawei;

import java.util.*;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String in = sc.nextLine();
        String[] ins = in.split(" ");
        int len1 = ins[0].length();
        int len2 = ins[1].length();
        int[] nums = new int[Math.max(len1, len2) + 1];
        int index = nums.length - 1;
        int i = 0, j = 0;
        for (i = len1 - 1,j = len2 - 1; i >= 0 || j >= 0; i--, j--) {
            int val1 = i >= 0 ? (ins[0].charAt(i) - '0') : 0;
            int val2 = j >= 0 ? (ins[1].charAt(j) - '0') : 0;
            int sum = val1  + val2 + nums[index];
            if (sum >= 10) {
                nums[index--] = sum % 10;
                nums[index] += 1;
            } else {
                nums[index--] = sum;
            }
        }
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        while (nums[cnt] == 0) cnt++;
        for (int k = cnt; k < nums.length; k++) {
            sb.append(nums[k]);
        }
        System.out.println(sb.toString());


    }




}
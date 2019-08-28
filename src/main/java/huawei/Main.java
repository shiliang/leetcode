package huawei;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] nums = new int[m][2];
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            if (map.containsKey(a)) {
                int val = map.get(a);
                map.put(a, val + 1);
            } else {
                map.put(a, 1);
            }

        }
        for (int key : map.keySet()) {
            int val = map.get(key);
            if (val % 2 == 0) {
                count++;
            }
        }
        System.out.println(count);

    }



}

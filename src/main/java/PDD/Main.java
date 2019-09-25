package PDD;

import java.util.*;

public class Main {
    public static int count = 1;
    static StringBuilder res = new StringBuilder();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int count = sc.nextInt();
        int[][] area = new int[count][2];
        for (int i = 0; i < count; i++) {
            area[i][0] = sc.nextInt();
            area[i][1] = sc.nextInt();
        }
        for (int i = 0; i < count; i++) {
            int a = getCount(arr, area[i][0], area[i][1]);
            System.out.println(a);
        }

    }

    public static int getCount(int[] arr, int l, int r) {
        int[] copy = new int[r - l + 1];
        int index = 0;
        int res = 0;
        for (int i = l - 1; i < r; i++, index++) {
            copy[index] = arr[i];
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < index; i++) {
            map.put(copy[i], map.getOrDefault(copy[i], 0) + 1);
        }

        for (Integer key : map.keySet()) {
            if (map.get(key) == 1) {
                res++;
            }
        }
        return res;
    }
}

package huawei;

import java.util.*;

public class Main {
    public static int pos = -1;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();
        while (sc.hasNext()) {
            list.add(sc.nextInt());
        }
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        find(arr, 0, list.size() - 1);
        System.out.println(pos);

    }

    public static void find(int[] arr, int left, int right) {
        if (left == right) return;
        int mid = (left + right) / 2;
        if (arr[mid] == 19) {
            pos = mid;
            return;
        } else if (arr[mid] < 19) {
            find(arr, left, mid - 1);
        } else {
            find(arr, mid + 1, right);
        }
    }




}

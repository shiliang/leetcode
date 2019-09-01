package PDD;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        ArrayList<Integer> res = new ArrayList<>();
        int[] arr = new int[n];
        //若干个连续子数组的最大和
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int sum = 0;
        int maxSum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            if (sum > maxSum) {
                maxSum = sum;
            } else if (sum < 0) {
                res.add(maxSum);
                sum = 0;
                maxSum = 0;
            }

        }
        //对结果按从大到小排序
        Collections.sort(res, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        int index = (k > res.size()) ? res.size() : k;
        for (int i = 0; i < index; i++) {
            System.out.println(res.get(i));
        }
    }
}

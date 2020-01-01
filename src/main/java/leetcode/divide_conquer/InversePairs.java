package leetcode.divide_conquer;

public class InversePairs {
    /*
        求逆序对的个数
     */
    private int num = 0;
    public int count(int [] array) {
        num = 0;
        int n = array.length;
        mergeSortCounting(array, 0, n - 1);
        return num;
    }

    public void mergeSortCounting(int[] array, int left, int right) {
        if (left >= right) return;
        int mid = (left + right) / 2;
        mergeSortCounting(array, left, mid);
        mergeSortCounting(array, mid + 1, right);
        merge(array, 0, mid, right);
    }

    public void merge(int[] array, int left, int mid, int right) {
        int i  = left;
        int j = mid + 1;
        int k = 0;
        int[] tmp = new int[right - left + 1];
        while (i <= mid && j <= right) {
            if (array[i] <= array[j]) {
                tmp[k++] = array[i++];
            } else {
                num += (mid - i + 1);
                tmp[k++] = array[j++];
            }
        }
        while (i <= mid) {
            tmp[k++] = array[i++];
        }
        while (j <= right) {
            tmp[k++] = array[j++];
        }

        for (int ii = 0; ii <= right - left; ii++) {
            array[left + ii] = tmp[ii];
        }
    }
}

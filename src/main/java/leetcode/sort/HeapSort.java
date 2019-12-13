package leetcode.sort;

public class HeapSort {


    public void heapSort(int[] arr) {
        int n = arr.length;
        buildHeap(arr, n);
        int k = n - 1;
        while (k >= 0) {
            swap(arr, 0, k);  //把堆顶的元素放到最后
            --k;
            heapify(arr, k + 1, 0); //从上往下堆化
        }
    }

    public void buildHeap(int[] arr, int n) {
        //非叶子节点从后往前处理
        for (int i = (n - 1) / 2; i >= 0; i--) {
            heapify(arr, n, i);
        }
    }

    //并且每个数据都是从上往下堆化,叶子节点不需要堆化
    public void heapify(int[] arr, int n, int i) {
        while (true) {
            int maxPos = i;
            if (i * 2 + 1 < n && arr[i] < arr[i * 2 + 1]) maxPos = i * 2 + 1;
            if (i * 2 + 2 < n && arr[maxPos] < arr[i * 2 + 2]) maxPos = i * 2 + 2;
            if (maxPos == i) break; //如果停止调整了就退出
            swap(arr, i, maxPos);
            i = maxPos;
        }
    }

    public void swap(int[] arr, int i, int maxPos) {
        int temp = arr[i];
        arr[i] = arr[maxPos];
        arr[maxPos] = temp;
    }


}

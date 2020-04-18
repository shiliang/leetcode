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
        //非叶子节点从后往前处理数组，并且每个数据都是从上往下堆化
        for (int i = (n - 1) / 2; i >= 0; i--) {
            heapify(arr, n, i);
        }
    }

    //并且每个数据都是从上往下堆化,叶子节点不需要堆化
    public void heapify(int[] nums, int n, int i) {
        int minPos = i;
        while (true) {
            if (i * 2 + 1 < n && nums[i] > nums[i * 2 + 1]) minPos = i * 2 + 1;
            if (i * 2 + 2 < n && nums[minPos] > nums[i * 2 + 2]) minPos = i * 2 + 2;
            if (minPos == i) break;
            swap(nums, i, minPos);
            i = minPos;
        }
    }

    public void swap(int[] arr, int i, int maxPos) {
        int temp = arr[i];
        arr[i] = arr[maxPos];
        arr[maxPos] = temp;
    }

    //no.215
    public int findKthLargest(int[] nums, int k) {
        //建立大小为k的小顶堆，然后取堆顶元素
        buildHeap(nums, k);
        for (int i = k; i < nums.length; i++) {
            if (nums[i] < nums[0]) continue;
            swap(nums, i, 0);
            heapify(nums, k, 0);  //从堆顶往下堆化
        }
        return nums[0];
    }


}

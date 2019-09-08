package leetcode.sort;

public class HeapSort {

    /**
     * 构建堆的过程
     * @param arr 需要排序的数组
     * @param parent 需要构建堆的根节点的序号
     * @param length 数组的长度
     */
    public void heapAdjust(int[] arr, int parent, int length) {
        int temp = arr[parent];
        int child = 2 * parent + 1; //左孩子
        while (child < length) {
            //如果有右孩子节点，并且右孩子节点的值大于左孩子的节点，则选取右孩子
            if (child + 1 < length && arr[child] < arr[child + 1]) {
                child++;
            }
            //如果父节点的值大于孩子节点的值，则结束
            if (temp >= arr[child]) {
                break;
            }
            //把孩子节点给父节点
            arr[parent] = arr[child];
            //选取孩子节点的左节点，继续向下筛选
            parent = child;
            child = 2 * child + 1;
        }
        arr[parent] = temp;
    }

    public void heapSort(int[] list) {
        //循环建立初始堆，从第一个非叶子结点从下至上，从右至左调整结构
        for (int i = list.length / 2; i >= 0; i--) {
            heapAdjust(list, i, list.length);
        }

        for (int i = list.length - 1; i > 0; i--) {
            int temp = list[i];
            list[i] = list[0];
            list[0] = temp;

            heapAdjust(list, 0, i);
        }
    }


}

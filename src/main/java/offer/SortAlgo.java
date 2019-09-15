package offer;

public class SortAlgo {
    //数组中的逆序对
    public int InversePairs(int[] array) {
        int len = array.length;
        if (len == 0 ) return 0;

        int[] copy = new int[len];

        return InversePairsCore(array, copy, 0, len - 1);
    }

    private int InversePairsCore(int[] array, int[] copy, int start, int end) {
        if (start == end) {
            copy[start] = array[start];
            return 0;
        }
        int len = (end - start) / 2; //对半分归并排序
        int left = InversePairsCore(array, copy, start, start + len) % 1000000007;
        int right = InversePairsCore(array, copy, start + len + 1, end) % 1000000007;

        //i初始化为前半段最后一个数字的下标
        int i = start + len;
        //j初始化为后半段最后一个数字的下标
        int j = end;
        int indexCopy = end;
        int count = 0;

        //统计数组之间的逆序对个数,数组内是有序的
        while (i >= start && j >= start + len + 1) {  //两个指针指向数组的末尾
            if (array[i] > array[j]) {
                copy[indexCopy--] = array[i--];  //存在逆序对两个指针往前移动
                count += j - start - len; //因为j是有序的，前面的部分肯定比i小
                if (count >= 1000000007) {
                    count %= 1000000007;
                }
            } else {
                copy[indexCopy--] = array[j--];
            }
        }

        for (; i >= start ; --i) {
            copy[indexCopy--] = array[i];
        }

        for (;  j >= start + len + 1 ; --j) {
            copy[indexCopy--] = array[j];

        }

        //把排序后的数组复制到原数组中
        for (int k = start; k <=end ; k++) {
            array[k] = copy[k];
        }
        return (left + right + count) % 1000000007;

    }

}

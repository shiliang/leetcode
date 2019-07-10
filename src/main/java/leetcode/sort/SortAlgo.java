package leetcode.sort;

import leetcode.list.ListNode;

import java.util.TreeMap;

public class SortAlgo {

    //计数排序
    public void sortColors(int[] nums) {
        int[] count = new int[3];

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int val = count[num] + 1;
            count[num] = val;
        }

        //填充
        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < count[j]; j++) {
                nums[k++] = i;
            }
        }


    }

    //非减排序数组,输出旋转数组的最小值
    public int minNumberInRotateArray(int [] array) {
        int len = array.length;
        if (len <= 0) return 0;

        int start = 0;
        int end = len - 1;
        int mid = start;
        while (array[start] >= array[end]) {
            if (end - start == 1) {
                mid = end;
                break;
            }

            mid = (start + end) / 2;
            //如果下标start, mid,end指向的三个数字相等，顺序查找
            if (array[start] == array[end]
            && array[mid] == array[start]) {
                return minInOrder(array, start, end);
            }

            if (array[mid] >= array[start]) {
                start = mid;
            } else if (array[mid] <= array[end]) {
                end = mid;
            }
        }

        return array[mid];
    }

    public int minInOrder(int[] array, int start, int end) {
        int result = array[start];
        for (int i = start + 1; i <= end ; i++) {
            if (result > array[i]) {
                result = array[i];
            }
        }
        return result;
    }

    //三路快排


    //


    //单链表快排
    public void linklistQuickSort(ListNode root) {


    }

    //148. Sort List
    public ListNode sortList(ListNode head) {
        if (head == null) return head;
        if (head.next == null) return head;

        //把一个链表等分成两段
        ListNode p1 = head;
        ListNode p2 = head;
        ListNode pre = head;

        while (p2 != null && p2.next != null) {
            pre = p1;
            p1 = p1.next;
            p2 = p2.next.next;
        }

        pre.next = null;  //把两个链表分开

        ListNode h1 = sortList(head);   //list1   head  to pre
        ListNode h2 = sortList(p1);    //list2 p1 to p2

        return merge(h1, h2);

    }

    public ListNode merge(ListNode h1, ListNode h2) {
        if (h1 == null) return h2;
        if (h2 == null) return h1;

        if (h1.val < h2.val) {
            h1.next = merge(h1.next, h2);
            return h1;
        } else {
            h2.next = merge(h1, h2.next);
            return h2;
        }
    }
}

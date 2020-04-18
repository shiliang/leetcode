package leetcode.sort;

import java.util.Stack;

public class QuickSort {
    //快排
    public void quickSort(int[] input, int start, int end) {
        if (start >= end) return;

        int index = partition(input, start, end);
        quickSort(input, start, index - 1);
        quickSort(input, index + 1, end);
    }

    public int partition(int[] input, int low, int high) {
        int pivot = input[low];  //选择最低点作为支点
        while (low < high) {
            //从后面开始比较，小于支点的填入前面的坑
            while (low < high && input[high] >= pivot) high--;
            if (low < high) {
                input[low] = input[high];
                low++;
            }

            //从前往后找，大于支点的填入后面的坑
            while (low < high && input[low] <= pivot) low++;
            if (low < high) {
                input[high] = input[low];
                high--;
            }

        }

        //最后把支点的值赋值
        input[low] = pivot;
        return low;  //返回支点的下标
    }


    //快排非递归
    public void quickSortNotRecursion(int[] input, int left, int right) {
        if (input.length <= 0) return;
        Stack<Integer> stack = new Stack<>();
        int i = 0, j = 0;
        stack.push(right);
        stack.push(left);
        while (!stack.isEmpty()) {
            i = stack.pop();
            j = stack.pop();
            if (i < j) {
                int k = partition(input, i, j);
                if (k > i) {
                    stack.push(k - 1);
                    stack.push(i);
                }

                if (j > k) {
                    stack.push(j);
                    stack.push(k + 1);
                }
            }
        }
    }


    //三路快排,分成3个部分，一部分比支点小，一部分等于，一部分大于
    public void threeWayQuickSort(int[] nums, int left, int right) {
        if (left >= right) return;

        int pivot = nums[left];
        //两边两个指针
        int lt = left;  //nums[left,lt-1] < pivot,小于支点的最后一个元素
        int gt = right;   //nums[gt,right] > pivot 大于支点的第一个元素
        int i = left + 1;  //nums[lt+1, i-1] == pivot
        while (i <= gt) {
            if (nums[i] < pivot) {
                swap(nums, i++, lt++);
            } else if (nums[i] > pivot) {
                swap(nums, i, gt--); //如果大的话先换回来在确定等和小
            } else {
                i++;
            }

        }
        threeWayQuickSort(nums, left, lt - 1);
        threeWayQuickSort(nums, gt + 1, right);

    }


    public void swap(int[] nums, int l, int r) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }


    //no.148 两种方法一种快排，一种归并排序
    //单链表快排
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode left = new ListNode(-1), mid = new ListNode(-1), right = new ListNode(-1); //三个哨兵
        ListNode ltail = left, mtail = mid, rtail = right;
        int val = head.val;
        for (ListNode p = head; p != null; p = p.next) {
            if (p.val < val) {
                ltail.next = p;
                ltail = ltail.next;
            } else if (p.val == val){
                mtail.next = p;
                mtail = mtail.next;
            } else {
                rtail.next = p;
                rtail = rtail.next;
            }
        }
        ltail.next = mtail.next = rtail.next = null;
        left.next = sortList(left.next);
        right.next = sortList(right.next);
        //合并三个链表
        ListNode leftTail = left.next == null ? left : getTail(left.next);
        ListNode midTail = getTail(mid.next);
        leftTail.next = mid.next;
        midTail.next = right.next;
        return left.next;
    }

    public ListNode getTail(ListNode head) {
        while (head.next != null) head = head.next;
        return head;
    }

    private ListNode quickSortHelper(ListNode head, ListNode end) {
        if (head == end || head.next == end) return head;
        ListNode tmpHead = new ListNode(-1);
        ListNode partition = head.next, p = partition, tp = tmpHead;
        //把小于划分节点值的节点放在tp临时链表上
        while (p.next != end) {
            if (p.next.val < partition.val) {
                tp.next = p.next;
                tp = tp.next;
                p.next = p.next.next;
            } else {
                p = p.next;
            }
        }
        //合并链表
        tp.next = head.next;
        head.next = tmpHead.next;
        quickSortHelper(head, partition);  //partition开区间不参与排序
        quickSortHelper(partition, end);
        return head.next;
    }


}

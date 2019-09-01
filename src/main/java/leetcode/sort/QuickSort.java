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

    //


    //no.148 两种方法一种快排，一种归并排序
    //单链表快排,把链表结尾的节点当支点，从头到尾遍历，比支点大的尾插到最后，小的保持不动
    //新的首尾节点也需要生成newHead, newTail   ()
    public void linklistQuickSort(ListNode root) {
        linklistQuickSortRecur(root, getTail(root));

    }

    public ListNode linklistQuickSortRecur(ListNode head, ListNode end) {
        if (head == null || head == end) return head;
        ListNode newHead = null, newEnd = null;
        ListNode pivot = listPartition(head, end, newHead, newEnd);
        //如果pivot是最小的节点，不用递归
        //左边部分
        if (newHead != pivot) {
            ListNode tmp = newHead;
            while (tmp.next != pivot) {
                tmp = tmp.next;
            }
            tmp.next = null;
            newHead = linklistQuickSortRecur(newHead, tmp);
            tmp = getTail(newHead);
            tmp.next = pivot;
        }
        pivot.next = linklistQuickSortRecur(pivot.next, newEnd);
        return newHead;
    }

    public ListNode listPartition( ListNode head, ListNode end,
                                   ListNode newHead, ListNode newEnd) {
        ListNode pivot = end;
        ListNode cur = head, prev = null, tail = pivot;
        while (cur != pivot) {
            if (cur.val < pivot.val) {
                if (newHead == null) {
                    newHead = cur;
                }
                prev = cur;
                cur = cur.next;
            } else {
                if (prev != null) {
                    prev.next = cur.next;
                }
                ListNode tmp = cur.next;
                cur.next = null;
                tail.next = cur;
                tail = cur;
                cur = tmp;

            }


        }

        //如果pivot是最小的节点
        if (newHead == null) {
            newHead = pivot;
        }
        newEnd = tail;
        return pivot;
    }

    public ListNode getTail(ListNode head) {
        if (head == null) return head;
        while (head.next != null) head = head.next;
        return head;
    }
}

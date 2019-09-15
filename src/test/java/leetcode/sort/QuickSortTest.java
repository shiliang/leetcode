package leetcode.sort;

import org.junit.Test;

import static org.junit.Assert.*;

public class QuickSortTest {

    QuickSort quickSort = new QuickSort();
    @Test
    public void threeWayQuickSort() {
        int[] input = {4,2,1,3,9,0,6,8};
        quickSort.threeWayQuickSort(input, 0, input.length - 1);
    }

    @Test
    public void linklistQuickSort() {
        ListNode node1 = new ListNode(4);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        quickSort.linklistQuickSort(node1);
    }
}
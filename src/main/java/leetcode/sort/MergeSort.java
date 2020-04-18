package leetcode.sort;

import leetcode.list.ListNode;

public class MergeSort {
    //148. Sort List
    public leetcode.list.ListNode sortList(leetcode.list.ListNode head) {
        if (head == null) return head;
        if (head.next == null) return head;

        //把一个链表等分成两段
        leetcode.list.ListNode p1 = head;
        leetcode.list.ListNode p2 = head;
        leetcode.list.ListNode pre = head;

        while (p2 != null && p2.next != null) {
            pre = p1;
            p1 = p1.next;
            p2 = p2.next.next;
        }

        pre.next = null;  //把两个链表分开

        leetcode.list.ListNode h1 = sortList(head);   //list1   head  to pre
        leetcode.list.ListNode h2 = sortList(p1);    //list2 p1 to p2

        return mergelist(h1, h2);

    }

    public leetcode.list.ListNode mergelist(leetcode.list.ListNode h1, ListNode h2) {
        if (h1 == null) return h2;
        if (h2 == null) return h1;

        if (h1.val < h2.val) {
            h1.next = mergelist(h1.next, h2);
            return h1;
        } else {
            h2.next = mergelist(h1, h2.next);
            return h2;
        }
    }

    //两路归并算法，两个排好序的子序列合并为一个子序列
    public void merge(int []a,int left,int mid,int right){
        int []tmp=new int[a.length];//辅助数组
        int p1=left,p2=mid+1,k=left;//p1、p2是检测指针，k是存放指针

        while(p1<=mid && p2<=right){
            if(a[p1]<=a[p2])
                tmp[k++]=a[p1++];
            else
                tmp[k++]=a[p2++];
        }

        while(p1<=mid) tmp[k++]=a[p1++];//如果第一个序列未检测完，直接将后面所有元素加到合并的序列中
        while(p2<=right) tmp[k++]=a[p2++];//同上

        //复制回原素组
        for (int i = left; i <=right; i++)
            a[i]=tmp[i];
    }

    public void mergeSort(int [] a,int start,int end){
        if(start<end){//当子序列中只有一个元素时结束递归
            int mid=(start+end)/2;//划分子序列
            mergeSort(a, start, mid);//对左侧子序列进行递归排序
            mergeSort(a, mid+1, end);//对右侧子序列进行递归排序
            merge(a, start, mid, end);//合并
        }
    }

}

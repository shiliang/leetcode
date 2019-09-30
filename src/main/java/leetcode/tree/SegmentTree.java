package leetcode.tree;

import java.util.TreeMap;

public class SegmentTree {
    //leetcode 307
    class SegmentTreeNode {
        int start;
        int end;
        int sum;
        SegmentTreeNode left;
        SegmentTreeNode right;

        public SegmentTreeNode(int start, int end, int val, SegmentTreeNode left, SegmentTreeNode right) {
            this.start = start;
            this.end = end;
            this.sum = val;
            this.left = left;
            this.right = right;
        }
    }

    public SegmentTreeNode buildTree(int start, int end, int[] nums) {
        if (start == end) return new SegmentTreeNode(start, end, nums[start], null, null);
        int mid = (start + end) / 2;
        SegmentTreeNode left = buildTree(start, mid, nums);
        SegmentTreeNode right = buildTree(mid+1, end, nums);
        return new SegmentTreeNode(start, end, left.sum + right.sum, left, right); //父节点
    }

    public void updateTree(SegmentTreeNode root, int index, int val) {
        if (root.start == root.end && root.end == index) {
            root.sum = val;
            return;
        }
        int mid = (root.start + root.end) / 2;
        if (index <= mid) { //在树的左边部分中
            updateTree(root.left, index, val);
        } else {
            updateTree(root.right, index, val);
        }
        root.sum = root.left.sum + root.right.sum; //回溯的的时候动态更新根节点的值
    }

    public int querySum(SegmentTreeNode root, int i, int j) {
        if (root.start == i && root.end == j) {
            return root.sum;  //正好落在这个范围内
        }
        int mid = (root.start + root.end) / 2;
        if (j <= mid) {
            //全部落在左子树中
            return querySum(root.left, i, j);
        } else if (i > mid) {
            //全部落在右子树中
            return querySum(root.right, i, j);
        } else {
            //一部分落在左子树，一部分落在右子树中
            return querySum(root.left, i, mid) + querySum(root.right, mid + 1, j);
        }
    }

}

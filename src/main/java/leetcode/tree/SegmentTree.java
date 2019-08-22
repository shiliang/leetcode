package leetcode.tree;

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
        return new SegmentTreeNode(start, end, left.sum + right.sum, left, right);
    }

    public void updateTree(SegmentTreeNode node, int index, int val) {

    }
}

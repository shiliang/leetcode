package leetcode.tree;

import org.junit.Test;

import static org.junit.Assert.*;

public class SegmentTreeTest {
    int[] input = {0,9,5,7,3};
    SegmentTree segmentTree = new SegmentTree();

    @Test
    public void build() {
        SegmentTree.SegmentTreeNode root = segmentTree.buildTree(0, input.length - 1, input);
        segmentTree.querySum(root, 3, 3);
    }


}
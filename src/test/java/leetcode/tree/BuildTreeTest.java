package leetcode.tree;

import org.junit.Test;

import static org.junit.Assert.*;

public class BuildTreeTest {

    BuildTree buildTree = new BuildTree();

    @Test
    public void reConstructBinaryTree() {
        int[] pre = {1,2,4,5,7,8,3,6};
        int[] in = {4,2,7,5,8,1,3,6};
        TreeNode node = buildTree.reConstructBinaryTree(pre, in);
    }

    @Test
    public void preOrder() {
        int[] pre = {1,2,4,5,7,8,3,6};
        int[] in = {4,2,7,5,8,1,3,6};
        TreeNode node = buildTree.reConstructBinaryTree(pre, in);
        buildTree.preOrder(node);

    }

    @Test
    public void preOrderNoRec() {
        int[] pre = {1,2,4,5,7,8,3,6};
        int[] in = {4,2,7,5,8,1,3,6};
        TreeNode node = buildTree.reConstructBinaryTree(pre, in);
        buildTree.preOrderNoRec(node);

    }
}
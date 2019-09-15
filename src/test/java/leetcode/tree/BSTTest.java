package leetcode.tree;

import org.junit.Test;

import static org.junit.Assert.*;

public class BSTTest {
    BST bst = new BST();

    @Test
    public void recoverTree() {
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(2);
        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        bst.recoverTree(node1);

    }
}
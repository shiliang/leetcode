package leetcode.tree;

import org.junit.Test;

import static org.junit.Assert.*;

public class TreeAlgoTest {

    @Test
    public void serialize() {
        TreeNode root = new TreeNode(1);
        TreeNode root1 = new TreeNode(2);
        TreeNode root2 = new TreeNode(3);
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(5);
        root.left = root1;
        root.right = root2;
        root2.left = node1;
        root2.right = node2;

        TreeAlgo treeAlgo = new TreeAlgo();
        String res = treeAlgo.serialize(root);
        TreeNode node = treeAlgo.deserialize(res);
    }
}
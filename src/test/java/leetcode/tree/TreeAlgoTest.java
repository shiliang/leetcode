package leetcode.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void PrintByZigZag() {
        /*
                1
               /  \
              2    3
             /    / \
            4    5   6
                / \
               7   8

         */
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(7);
        TreeNode node7 = new TreeNode(8);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = null;
        node2.left = node4;
        node2.right = node5;
        node3.left = null;
        node3.right = null;
        node4.left = node6;
        node4.right = node7;
        node5.left = null;
        node5.right = null;
        node6.left = null;
        node6.right = null;
        node7.left = null;
        node7.right = null;
        TreeAlgo treeAlgo = new TreeAlgo();
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        res = treeAlgo.PrintZigZag(root);

    }

    @Test
    public void reConstructBinaryTree() {
        TreeAlgo treeAlgo = new TreeAlgo();
        int[] pre = {1,2,4,7,3,5,6,8};
        int[] in = {4,7,2,1,5,3,8,6};
        TreeNode node = treeAlgo.reConstructBinaryTree(pre, in);
    }

    @Test
    public void VerifySquenceOfBST() {
        TreeAlgo treeAlgo = new TreeAlgo();
        int[] input = {5,7,6,9,11,10,8};
        boolean res = treeAlgo.VerifySquenceOfBST(input);
    }


    @Test
    public void pathSum() {
        TreeAlgo treeAlgo = new TreeAlgo();
        TreeNode root = new TreeNode(5);
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(8);
        TreeNode node3 = new TreeNode(11);
        TreeNode node4 = new TreeNode(13);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(7);
        TreeNode node7 = new TreeNode(2);
        TreeNode node8 = new TreeNode(5);
        TreeNode node9 = new TreeNode(1);

        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node5.left = node8;
        node5.right = node9;
        List<List<Integer>> path = new ArrayList<>();
        path = treeAlgo.pathSum(root,22);
    }

    @Test
    public void lowestCommonAncestor() {
        TreeAlgo treeAlgo = new TreeAlgo();
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(6);
        TreeNode node3 = new TreeNode(1);
        root.left = node1;
        root.right = node3;
        node1.left = node2;
        TreeNode a = treeAlgo.lowestCommonAncestor(root, node1, node2);
    }

    @Test
    public void inorderSuccessor() {
        TreeAlgo treeAlgo = new TreeAlgo();
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(6);
        TreeNode node3 = new TreeNode(1);
    }

}

package leetcode.tree;

import java.util.Stack;

public class BST {

    //no.99 二叉搜索树中
    TreeNode first = null, second = null, prev = null;
    public void recoverTree(TreeNode root) {
        if (root == null) return;
        recoverTreeHelper(root);
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    public void recoverTreeHelper(TreeNode root) {
        if (root == null) return;
        recoverTreeHelper(root.left);
        if (prev != null && root.val < prev.val) {
            if (first == null) first = prev;   //满足条件的是第一个比后面值大的
            second = root;   //最后一个比后面值大的
        }
        prev = root;
        recoverTreeHelper(root.right);
    }

    //no.98验证是否是二叉搜索树,非递归中序遍历
    public boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        double inorder = Double.MIN_VALUE;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.val <= inorder) return false;
            inorder = root.val;
            root = root.right;
        }
        return true;
    }


}

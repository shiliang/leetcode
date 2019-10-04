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

    //no.450在二叉搜索树中删除某个节点
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return root;
        if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left != null && root.right != null) {
                //在右子树中找出最靠左的节点
                TreeNode min = root.right;
                while (min.left != null) min = min.left;
                root.val = min.val;
                root.right = deleteNode(root.right, min.val); //把右子树中最左边的左子树节点删除
            } else {
                TreeNode newNode = root.left == null ? root.right : root.left;
                return newNode;
            }


        }

        return root;
    }

}

package leetcode.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class BuildTree {
    //根据前序序列和中序序列重建二叉树
    //前序{1,2,4,7,3,5,6,8}  中序{4,7,2,1,5,3,8,6}
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        if (pre.length == 0 || in.length == 0) return null;
        return constructCore(pre, in,
                0, pre.length - 1,
                0, in.length - 1);
    }

    public TreeNode constructCore(int[] pre, int[] in,
                                  int startPre, int endPre,
                                  int startIn, int endIn) {
        int rootValue = pre[startPre];
        TreeNode root = new TreeNode(rootValue);
        root.right = root.left = null;
        if (startPre == endPre && startIn == endIn) {
            return root;
        }

        //找出根节点在中序中的位置
        int rootIndex = startIn;
        while (rootValue != in[rootIndex] && rootIndex <= endIn) {
            rootIndex++;
        }

        //算出中序根节点前一部分的长度
        int inLeftLength = rootIndex - startIn;
        //构建左子树
        if (inLeftLength > 0) {
            root.left = constructCore(pre, in,
                    startPre + 1, startPre + inLeftLength,
                    startIn, rootIndex - 1);
        }

        //构造右子树
        if (inLeftLength < (endPre - startPre) ) {
            root.right = constructCore(pre, in,
                    startPre + inLeftLength + 1, endPre,
                    rootIndex + 1, endIn);
        }

        return root;

    }

    //三种遍历方式，递归和非递归

    //前序递归
    public void preOrder(TreeNode root) {
        if (root == null) return;
        System.out.println(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }

    //前序非递归
    public void preOrderNoRec(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pNode = root;
        while (pNode != null || !stack.isEmpty()) {
            if (pNode != null) {
                System.out.println(pNode.val + " ");
                stack.push(pNode);
                pNode = pNode.left;
            } else {
                TreeNode node = stack.pop();  //回溯到最近一个父节点且右子树不为空
                pNode = node.right;
            }
        }
    }

    //中序
    public void inOrder(TreeNode root) {
        if (root == null) return;
        preOrder(root.left);
        System.out.println(root.val);
        preOrder(root.right);
    }

    public void inOrderNoRec(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pNode = root;
        while (pNode != null || !stack.isEmpty()) {
            if (pNode != null) {
                stack.push(pNode);
                pNode = pNode.left;
            } else {
                TreeNode node = stack.pop();
                System.out.println(node.val + " ");
                pNode = node.right;
            }
        }
    }

    //后序
    public void postOrder(TreeNode root) {
        if (root == null) return;
        preOrder(root.left);
        preOrder(root.right);
        System.out.println(root.val);
    }

    public List<Integer> postOrderNoRec(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root == null) return result;
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode curNode = stack.pop();
            result.addFirst(curNode.val);
            if (curNode.left != null) stack.push(curNode.left);
            if (curNode.right != null) stack.push(curNode.right);
        }

        return result;
    }



    //层次遍历
    
}

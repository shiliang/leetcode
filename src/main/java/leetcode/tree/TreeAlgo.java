package leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

public class TreeAlgo {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "#!";
        }
        String res = String.valueOf(root.val) + "!";
        res += serialize(root.left);
        res += serialize(root.right);
        return res;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] values = data.split("!");
        Queue<String> queue = new LinkedList<String>();
        for (int i = 0; i < values.length; i++) {
            queue.offer(values[i]);
        }

        return preOrder(queue);
    }

    public TreeNode preOrder(Queue<String> que) {
        String value = que.poll();
        if (value.equals("#")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(value));
        root.left = preOrder(que);
        root.right = preOrder(que);
        return root;
    }
}

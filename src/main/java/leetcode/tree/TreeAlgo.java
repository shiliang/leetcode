package leetcode.tree;

import java.util.*;

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

    public ArrayList<ArrayList<Integer>> PrintByZigZag(TreeNode pRoot) {
        if (pRoot == null) return null;
        Deque<TreeNode> dq = new LinkedList<>();
        ArrayList<Integer> tempList = new ArrayList<>();
        ArrayList<ArrayList<Integer> > res = new ArrayList<>();
        boolean lr = true; //打印顺序
        TreeNode head = pRoot;
        TreeNode last = head;
        TreeNode nLast = null;
        dq.offerFirst(head);
        while (!dq.isEmpty()) {
            if (lr) {  //从左到右打印，尾部进顶部弹出左子树先入右子树后入
                head = dq.pollFirst();
                tempList.add(head.val);
                if (head.left != null) {
                    nLast = nLast == null ? head.left : nLast;
                    dq.offerLast(head.left);
                }
                if (head.right != null) {
                    nLast = nLast == null ? head.right : nLast;
                    dq.offerLast(head.right);
                }

            } else {  //从右到左打印，顶部进入右子树先进，底部弹出
                head = dq.pollLast();
                tempList.add(head.val);
                if (head.right != null) {
                    nLast = nLast == null ? head.right : nLast;
                    dq.offerFirst(head.right);
                }
                if (head.left != null) {
                    nLast = nLast == null ? head.left : nLast;
                    dq.offerFirst(head.left);
                }

            }
            if (head == last && !dq.isEmpty()) {
                lr = !lr;  //改变方向
                last = nLast;
                nLast = null;
                res.add(tempList);
                tempList.clear();
            }

        }
        return res;
    }
}

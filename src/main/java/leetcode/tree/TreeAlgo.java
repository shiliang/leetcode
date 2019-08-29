package leetcode.tree;

import java.util.*;

public class TreeAlgo {
    public boolean flag = false;
    public TreeNode res = null;
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) return null;
        helper(root, p);
        return res;
    }

    public void helper(TreeNode root, TreeNode p) {
        if (root == null) return;
        helper(root.left, p);
        if (flag) {
            res = root;
            return;
        }
        if (root == p) {
            flag = true;
        }
        helper(root.right, p);
    }

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

    //按层次打印二叉树，并打印出相应的行号
    public ArrayList<ArrayList<Integer>> printByLevel(TreeNode head) {
        ArrayList<ArrayList<Integer> > res = new ArrayList<>();
        ArrayList<Integer> tempList = new ArrayList<>();
        if (head == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        int level = 1;
        TreeNode last = head;
        TreeNode nlast = null;
        queue.offer(head);
        System.out.print("Level" + (level++) + ":");
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            tempList.add(node.val);
            System.out.print(node.val + " ");
            if (node.left != null) {
                queue.offer(node.left);
                nlast = node.left;
            }

            if (node.right != null) {
                queue.offer(node.right);
                nlast = node.right;
            }

            if (node == last && !queue.isEmpty()) {
                System.out.print("\nLevel" + (level++) + ":");
                res.add(new ArrayList<Integer>(tempList));
                tempList.clear();
                last = nlast;
            }
        }
        return res;

    }


    //按Z字型打印二叉树
    public ArrayList<ArrayList<Integer> > PrintZigZag(TreeNode pRoot) {
        ArrayList<ArrayList<Integer> > res = new ArrayList<ArrayList<Integer> >();
        ArrayList<Integer> tempList = new ArrayList<Integer>();
        if (pRoot == null) return res;
        Deque<TreeNode> dq = new LinkedList<TreeNode>();
        TreeNode last = pRoot;
        TreeNode nlast = null;
        TreeNode node = null;
        boolean lr = true;
        dq.offerFirst(pRoot);
        while (!dq.isEmpty()) {
            if (lr) {  //从左到右打印
                node = dq.pollFirst();
                tempList.add(node.val);
                if (node.left != null) {
                    dq.offerLast(node.left);
                    nlast = nlast == null ? node.left : nlast;  //新一层遍历的时候确定下一层最后一个节点
                }
                if (node.right != null) {
                    dq.offerLast(node.right);
                    nlast = nlast == null ? node.right : nlast;
                }
            } else {
                node = dq.pollLast();
                tempList.add(node.val);
                if (node.right != null) {
                    dq.offerFirst(node.right);
                    nlast = nlast == null ? node.right : nlast;
                }

                if (node.left != null) {
                    dq.offerFirst(node.left);
                    nlast = nlast == null ? node.left : nlast;
                }
            }

            if (node == last && !dq.isEmpty()) {
                lr = !lr;  //改变方向
                last = nlast;
                nlast = null;
                res.add(new ArrayList<Integer>(tempList));
                tempList.clear();
            }
        }
        res.add(new ArrayList<Integer>(tempList));
        return res;
    }

    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        if (pre.length == 0 || in.length == 0) return null;
        int len = pre.length;
        return constructCore(pre, in,0,len-1,0,len-1);
    }

    public TreeNode constructCore(int[] pre, int[] in,
                                  int startPreorder, int endPreorder,
                                  int startInorder, int endInorder) {
        int rootValue = pre[startPreorder];
        TreeNode root = new TreeNode(0);
        root.val = rootValue;
        root.left = root.right = null;
        if ((startPreorder == endPreorder) && (startInorder == endInorder)) {
            return root;
        }

        //在中序中遍历根节点的值
        int rootInorder = startInorder;
        while (rootInorder <= endInorder && rootValue != in[rootInorder]) {
            rootInorder++;
        }
        int leftLength = rootInorder - startInorder;
        int leftPreorderLength = startPreorder + leftLength;

        //构建左子树
        if (leftLength > 0) {
            root.left = constructCore(pre, in,
                    startPreorder + 1, startPreorder + leftLength,
                    startInorder, rootInorder - 1);
        }

        //构建右子树
        if(leftLength < (endPreorder-startPreorder)) {
            root.right = constructCore(pre, in,
                    leftPreorderLength + 1, endPreorder,
                    rootInorder + 1, endInorder);
        }
        return root;
    }

    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) return result;
        queue.offer(root);
        while (queue.size() != 0) {
            TreeNode temp = queue.poll();
            if (temp.left != null) {
                queue.offer(temp.left);
            }

            if (temp.right != null) {
                queue.offer(temp.right);
            }
            result.add(temp.val);
        }
        return result;
    }


    //判断数组是不是后序遍历
    public boolean VerifySquenceOfBST(int [] sequence) {
        if (sequence.length == 0) return false;
        boolean result = isVerify(sequence, 0, sequence.length - 1);
        return result;
    }

    public boolean isVerify(int[] sequence, int start, int end) {
        if (start >= end) return true;
        int root = sequence[end];
        int seprator = start;
        while (sequence[seprator] < root) {
            seprator++;
        }
        //递归左边
        boolean isLeftVerify = isVerify(sequence, start, seprator - 1);

        //判断右边节点是不是都比根节点大
        boolean isRightVerify = true;

        for (int i = seprator; i < end; i++) { //end为根节点
            if (sequence[i] < root) {
                isRightVerify = false;
                break;
            }
        }

        //再递归判断右边部分是否满足后序序列
        if (isRightVerify) {
            isRightVerify = isVerify(sequence, seprator, end - 1);
        }

        return isLeftVerify && isRightVerify;
    }


    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> path = new ArrayList<>();
        helper(root, sum, path, new ArrayList<>());
        return path;

    }

    public void helper(TreeNode root, int sum, List<List<Integer>> path, ArrayList<Integer> tmpList) {
        if (root == null) return;
        tmpList.add(root.val);
        if (root.left == null && root.right == null && sum == root.val) {
            path.add(new ArrayList<>(tmpList));
            tmpList.remove(tmpList.size() - 1);
            return;
        } else {
            helper(root.left, sum - root.val, path, tmpList);
            helper(root.right, sum - root.val, path, tmpList);
        }
        tmpList.remove(tmpList.size() - 1);

    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;  //左右子树分别包含pq说明是最近的公共祖先
        } else if (left != null) {
            return left;
        } else if (right != null) {
            return right;
        }
        return null;
    }

    //212，单词搜索II
    public List<String> findWords(char[][] board, String[] words) {
        TrieTree trieTree = new TrieTree();
        for (String w : words) {
            trieTree.insert(w);
        }
        List<String> res = new ArrayList<>();
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, visited, i, j, m, n, trieTree.root, res);
            }
        }
        return res;

    }

    public void dfs(char[][] board, boolean[][] visited,
                    int i, int j, int m, int n,
                    TrieTree.TrieNode node, List<String> res) {
        if (i < 0 || j < 0 || i >= m || j >= n || visited[i][j]) return;
        node = node.get(board[i][j]);
        if (node == null) return;
        if (node.isEnd()) {
            res.add(node.word);
        }
        visited[i][j] = true;
        dfs(board, visited, i + 1, j, m, n, node, res);
        dfs(board, visited, i - 1, j, m, n, node, res);
        dfs(board, visited, i, j + 1, m, n, node, res);
        dfs(board, visited, i, j - 1, m, n, node, res);
        visited[i][j] = false;
    }



}

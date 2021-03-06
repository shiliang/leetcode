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




    //no.662,求二叉树最大宽度
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        int maxW = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        LinkedList<Integer> indexList = new LinkedList<>();
        queue.add(root);
        indexList.add(1);
        int size = 1; //记录每一层的节点数
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            size--;
            int index = indexList.removeFirst();
            if (node.left != null) {
                queue.add(node.left);
                indexList.add(2 * index);
            }
            if (node.right != null) {
                queue.add(node.right);
                indexList.add(2 * index + 1);
            }
            if (size == 0) {
                if (indexList.size() >= 2) {
                    maxW = Math.max(maxW, indexList.getLast() - indexList.getFirst() + 1);
                }
                size = queue.size();
            }
        }
        return maxW;

    }

    //二叉树的层序遍历
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        int size = 1;
        queue.add(root);
        List<Integer> tmp = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            size--;
            tmp.add(node.val);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }

            if (size == 0) {
                res.add(new ArrayList<>(tmp));
                tmp.clear();
                size = queue.size();
            }

        }
        return res;

    }

    //no.652,寻找重复的子树
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        HashMap<String, Integer> counts = new HashMap<>();
        List<TreeNode> ans = new ArrayList<>();
        findDuplicateSubtreeshelper(root, counts, ans);
        return ans;
    }

    public String findDuplicateSubtreeshelper(TreeNode root, HashMap<String, Integer> counts, List<TreeNode> ans) {
        if (root == null) return "#";
        StringBuilder key = new StringBuilder();
        key.append(root.val);
        key.append(",");
        key.append(findDuplicateSubtreeshelper(root.left, counts, ans));
        key.append(",");
        key.append(findDuplicateSubtreeshelper(root.right, counts, ans));
        counts.put(key.toString(), counts.getOrDefault(key.toString(), 0) + 1);
        if (counts.get(key.toString()) == 2) {
            ans.add(root);
        }
        return key.toString();
    }

    //no.124二叉树中的最大路径和
    private int maxSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxPathSumHelper(root);
        return maxSum;
    }

    public int maxPathSumHelper(TreeNode root) {
        if (root == null) return 0;
        //如果穿过节点，节点的值加上左右子树的值
        int leftSum = Math.max(0, maxPathSumHelper(root.left));
        int rightSum = Math.max(0, maxPathSumHelper(root.right));
        maxSum = Math.max(maxSum, leftSum + rightSum + root.val);
        return Math.max(leftSum, rightSum) + root.val; //返回只能返回一条边
    }

    //no.687 返回相同值的最长路径
    int ans = 0;
    public int longestUnivaluePath(TreeNode root) {
        if (root == null) return 0;
        univaluePath(root);
        return ans;
    }

    public int univaluePath(TreeNode root) {
        if (root == null) return 0;
        int l = univaluePath(root.left);
        int r = univaluePath(root.right);
        int pl = 0;
        int pr = 0;
        //路径的个数是边的个数
        if (root.left != null && root.val == root.left.val) pl = l + 1;
        if (root.right != null && root.val == root.right.val) pr = r + 1;
        ans = Math.max(ans, pl + pr);
        return Math.max(pl, pr);

    }

    

}

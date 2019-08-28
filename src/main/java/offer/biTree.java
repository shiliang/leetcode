package offer;

public class biTree {

    //把二叉搜索树转化成双向链表
    TreeNode pre = null;
    public TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null) return pRootOfTree;

        TreeNode pNode = pRootOfTree;
        ConvertHelper(pNode);
        TreeNode res = pRootOfTree;
        while (res.left != null) {
            res = res.left;
        }
        return res;
    }

    public void ConvertHelper(TreeNode cur) {
        if (cur == null) return;
        ConvertHelper(cur.left);
        cur.left = pre;
        if (pre != null) {
            pre.right = cur;
        }
        pre = cur;
        ConvertHelper(cur.right);
    }
}

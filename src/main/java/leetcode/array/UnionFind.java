package leetcode.array;

public class UnionFind {
    private int[] parent, rank;  //rank表示树高
    private int count = 0; //树的节点的个数

    public UnionFind(int n) {
        count = n;
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;  //初始化父节点指向自己
        }
    }

    //返回父节点
    public int find(int p) {
        while (p != parent[p]) {
            parent[p] = parent[parent[p]]; //往上找最上层的父节点,路径压缩
            p = parent[p];
        }
        return p; //返回最上层的父节点
    }

    //连通两个对象，合并
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;
        if (rank[rootP] < rank[rootQ]) {
            parent[rootP] = rootQ;
        } else {
            parent[rootQ] = rootP;
            if (rank[rootP] == rank[rootQ]) {  //如果是两个子树高度相等
                rank[rootP]++;
            }
        }

        count--;
    }

    //一共有多少个连通分量
    public int getCount() {
        return count;
    }
}

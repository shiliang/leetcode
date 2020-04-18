package leetcode.array;

public class UnionFind {
    private int[] parent, size;  //size记录树的重量
    private int count = 0; //树的节点的个数

    public UnionFind(int n) {
        count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;  //初始化父节点指向自己
            size[i] = 1;
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
        if (size[rootP] < size[rootQ]) {  //树矮的指向树高的即以树高的作为父节点来保持树的平衡
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        } else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }

        count--;  //联通分量减一
    }

    //一共有多少个连通分量
    public int getCount() {
        return count;
    }

    //判断两个节点的连通性
    public boolean connected(int p, int q) {
        int rooP = find(p);
        int rooQ = find(q);
        return rooP == rooQ;
    }
}

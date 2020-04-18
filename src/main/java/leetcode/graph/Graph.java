package leetcode.graph;

import java.util.LinkedList;

public class Graph {
    //有向有权图的邻接表表示
    private LinkedList<Edge>[] adj; //邻接表
    private int v; //顶点的个数

    public Graph(int v) {
        this.v = v;
        this.adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            this.adj[i] = new LinkedList<>();
        }
    }

    private class Edge {
        public int sid; //边的起始顶点编号
        public int tid; //边的终止编号
        public int w; //权重

        public Edge(int sid, int tid, int w) {
            this.sid = sid;
            this.tid = tid;
            this.w = w;
        }
    }

    public void addEdge(int s, int t, int w) {
        this.adj[s].add(new Edge(s, t, w));
    }

    private class Vertex {
        public int id; //顶点编号ID
        public int dist; //从起始顶点到这个顶点的距离

        public Vertex(int id, int dist) {
            this.id = id;
            this.dist = dist;
        }
    }
}

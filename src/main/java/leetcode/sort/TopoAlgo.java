package leetcode.sort;

import java.util.ArrayList;
import java.util.LinkedList;

public class TopoAlgo {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<Integer>[] graph = new ArrayList[numCourses];
        int[] degree = new int[numCourses];  //记录入度
        ArrayList<Integer> bfs  = new ArrayList();
        for (int i = 0; i < numCourses; ++i) graph[i] = new ArrayList<Integer>();
        for (int[] p : prerequisites) {
            graph[p[0]].add(p[1]);
            degree[p[1]]++;
        }

        for (int i = 0; i < numCourses; i++) {
            if (degree[i] == 0) bfs.add(i);
        }

        for (int i = 0; i < bfs.size(); i++) {
            for (int j : graph[bfs.get(i)]) {
                if (--degree[j] == 0) bfs.add(j);
            }
        }
        return bfs.size() == numCourses;

    }

    public class Graph {
        private int v;
        private LinkedList<Integer>[] adj; //邻接表

        public Graph(int v) {
            this.v = v;
            adj = new LinkedList[v];
            for (int i = 0; i < v; i++) {
                adj[i] = new LinkedList<>();
            }
        }

        public void addEdge(int s, int t) {
            //边s->t
            adj[s].add(t);
        }
    }

    public void topoSortByKahn(int v) {
        Graph graph = new Graph(v);
        int[] inDegree = new int[v];
        //统计各个顶点的入度
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < graph.adj[i].size(); j++) {
                int w = graph.adj[i].get(j);
                inDegree[w]++;
            }
        }
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < v; i++) {
            if (inDegree[i] == 0) queue.add(i);
        }
        while (!queue.isEmpty()) {
            int i = queue.remove();
            System.out.println("->" + i);
            for (int j = 0; j < graph.adj[i].size(); j++) {
                int k = graph.adj[i].get(j);
                inDegree[k]--;
                if (inDegree[k] == 0) queue.add(k);
            }
        }
    }
}

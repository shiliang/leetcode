package leetcode.graph;

import java.util.*;

public class BiGraph {

    public boolean possibleBipartition(int N, int[][] dislikes) {
        //自建邻接表
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(N + 1);
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] d : dislikes) {
            graph.get(d[0]).add(d[1]);
        }
        int[] colors = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            if (colors[i] == 0) {
                Queue<Integer> queue = new LinkedList<>();
                queue.offer(i);
                colors[i] = 1;
                while(!queue.isEmpty()) {
                    int t = queue.poll();
                    for (int v : graph.get(t)) {
                        if (colors[v] == colors[t]) return false;
                        if (colors[v] == 0) {
                            colors[v] = colors[t] * -1;
                            queue.offer(v);
                        }
                    }
                }
            }

        }
        return true;
    }
}

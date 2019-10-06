package leetcode.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestPath {

    //no.743，网络延时时间，从出发点k到其他各点的最短路径再找出其中的最大距离
    //邻接矩阵
    public int networkDelayTime(int[][] times, int N, int K) {
        Queue<Integer> queue = new LinkedList<>();
        int INF = Integer.MAX_VALUE;
        int maxDist = -1; //最大距离
        int[][] graph = new int[N + 1][N + 1];
        boolean[] flag = new boolean[N + 1];
        int[] path = new int[N + 1];  //表示点k到path[index]的最短距离
        Arrays.fill(path, INF);
        for (int i = 1; i < graph.length; i++) {
            for (int j = 1; j < graph[i].length; j++) {
                graph[i][j] = -1;
            }
        }
        //读入边的权重
        for (int[] time : times) {
            graph[time[0]][time[1]] = time[2];
        }

        queue.add(K);
        path[K] = 0;
        while (true) {
            int index = -1;
            int min = Integer.MAX_VALUE;

            for (int i = 1; i < graph.length; i++) {
                if (path[i] < min && !flag[i]) {
                    min = path[i];
                    index = i;
                }
            }
            if (index == -1) break;
            flag[index] = true;

            for (int i = 1; i < graph[index].length; i++) {
                if (graph[index][i] != INF &&
                !flag[i] && path[index] + graph[index][i] < path[i]) {
                    path[i] = path[index] + graph[index][i];
                }
            }


        }

        for (int i = 1; i < path.length; i++) {
            if (path[i] == INF) return -1;
            else if (maxDist < path[i]) maxDist = path[i];
        }
        return maxDist;

    }
}

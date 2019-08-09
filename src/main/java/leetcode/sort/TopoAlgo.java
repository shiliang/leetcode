package leetcode.sort;

import java.util.ArrayList;

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
}

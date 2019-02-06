package leetcode.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//no.207 Course Schedule
public class GraphSolution {

    private Map<Integer, List<Integer>> map =new HashMap<Integer, List<Integer>>();

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        boolean[] visited = new boolean[numCourses];
        //判断有向图是否存在着环
        for (int[] prereq : prerequisites) {
            //一行只有两个元素组成一条有向边
            if (!map.containsKey(prereq[0])) {
                map.put(prereq[0], new LinkedList<Integer>());
                map.get(prereq[0]).add(prereq[1]);
            } else {
                map.get(prereq[0]).add(prereq[1]);
            }
        }

        for (int course : map.keySet()) {
            visited = new boolean[numCourses];
            if (dfs(course, visited)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(int v, boolean[] visited) {
        if (map.containsKey(v)) {
            if (visited[v]) {
                return true;
            }
            visited[v] = true;
            if (map.get(v).size() != 0) {
                return dfs(map.get(v).remove(0),visited);
            }
        }
        return false;
    }
}

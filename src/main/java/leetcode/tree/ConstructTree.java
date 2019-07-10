package leetcode.tree;

import java.util.*;

/*
    把输入的数字序列存入邻接表中
    找到根节点
 */
public class ConstructTree {
    static HashMap<Edge, Boolean> edges = new HashMap<>();
    static HashMap<Integer, Integer> timestamp = new HashMap<>();
    static HashMap<Integer, Boolean> hasFather = new HashMap<>();
    static HashMap<Integer, ArrayList<Integer>> adjacencyList = new HashMap<>();
    static HashMap<Integer, Integer> dist = new HashMap<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        ConstructTree constructTree = new ConstructTree();
        for (int i = 0; i < n; i++) {
            String str[] = scanner.next().split(",");
            int first = Integer.parseInt(str[0]); //父节点
            int second = Integer.parseInt(str[1]);  //子节点
            int tm = 0;
            Edge edge = new Edge(first, second);
            if (edges.containsKey(edge)) {
                continue;
            } else {
                edges.put(edge, true);
            }

            if (!timestamp.containsKey(first)) {
                timestamp.put(first, tm++);
            }
            if (!timestamp.containsKey(second)) {
                timestamp.put(second, tm++);
            }
            hasFather.put(second, true);
            if (!adjacencyList.containsKey(first)) {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(second);
                adjacencyList.put(first, list);
            } else {
                adjacencyList.get(first).add(second);
            }
        }

        String result = process(n);
        System.out.println(result);

    }

    private static String process(int n) {
        String result = "";
        int root = -1;
        for (Integer p : adjacencyList.keySet()) {
            if (hasFather.get(p) == null) {
                root = p;  //找到所有节点的根节点
                break;
            }
        }
        if (root == -1) {
            return "Not a tree";
        } else {
            ArrayList<Integer> res = bfs(root);
            if (res.size() < timestamp.size()) {  //整个树不连通
                return "Not a tree";
            } else {
                result += res.get(0) + "";
                for (int i = 0; i < res.size(); i++) {
                    result += "," + res.get(i);
                }
            }
        }
        return result;
    }

    private static ArrayList<Integer> bfs(int root) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(root);
        ArrayList<Integer> nodes = new ArrayList<>();
        dist.put(root, 0);
        while (!queue.isEmpty()) {
            int t = queue.poll();
            nodes.add(t);
            ArrayList<Integer> childs = adjacencyList.get(t);
            if (childs == null) {
                continue;
            }
            for (Integer child : childs) {
                if (dist.containsKey(child)) {
                    return new ArrayList<>();
                }
                dist.put(child, dist.get(child) + 1);
                queue.offer(child);
            }
        }

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for (Integer node : nodes) {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(dist.get(node));
            list.add(timestamp.get(node));
            list.add(node);
            ans.add(list);
        }

        nodes.clear();
        Collections.sort(ans, new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                if (Objects.equals(o1.get(0), o2.get(0))) { //如果距离相等按时间排序
                    return o1.get(1) - o2.get(1);
                }
                return o1.get(0) - o2.get(0);
            }
        });

        for (ArrayList<Integer> t : ans) {
            nodes.add(t.get(2));
        }
        return nodes;
    }
}

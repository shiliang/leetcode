package leetcode.graph;

import java.util.*;


public class GraphSolution {

    private static final int MAX_ROW = 5;
    private static final int MAX_COL = 5;
    /*
    predecessor[4][4]是坐标为(3, 4)的点，就表示从(3, 4)走到了(4, 4)
     */
    private Point[][] predecessor;
    private static int[][] maze = {
            {0, 1, 0, 0, 0},
            {0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 1, 0}

    };
    private Map<Integer, List<Integer>> map =new HashMap<Integer, List<Integer>>();
    private Stack<Point> stack = new Stack<Point>();

    public GraphSolution() {
        this.predecessor = new Point[MAX_ROW][MAX_COL];
        for (int i = 0; i < MAX_ROW; i++) {
            for (int j = 0; j < MAX_COL; j++) {
                predecessor[i][j] = new Point(-1,-1);
            }
        }
    }

    //no.207 Course Schedule
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        boolean[] visited = new boolean[numCourses];
        //判断有向图是否存在着环
        for (int[] prereq : prerequisites) {
            //一行只有两个元素组成一条有向边
            if (!map.containsKey(prereq[0])) {
                map.put(prereq[0], new LinkedList<Integer>());  //邻接表
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

    private boolean dfs(int k, boolean[] visited) {
        if (map.containsKey(k)) {
            //之前被访问过，能组成环
            if (visited[k]) {
                return true;
            }
            visited[k] = true;
            if (map.get(k).size() != 0) {
                //查找以链表中元素为起点的边
                return dfs(map.get(k).remove(0),visited);
            }
        }
        return false;
    }

    /*
    int maze[5][5] = {

     0, 1, 0, 0, 0,

     0, 1, 0, 1, 0,

     0, 0, 0, 0, 0,

     0, 1, 1, 1, 0,

     0, 0, 0, 1, 0,

    };

    它表示一个迷宫，其中的1表示墙壁，0表示可以走的路，只能横着走或竖着走，不能斜着走，要求编程序找出从左上角到右下角的路线
    */

    public void findPath() {

        //起点
        Point point = new Point(0,0);

        //走过的路用2表示
        maze[point.getRow()][point.getCol()] = 2;
        stack.push(point);
        while (!stack.empty()) {
            point = stack.pop();
            if (point.getRow() == MAX_ROW -1 &&
                point.getCol() == MAX_COL - 1) {
                break;  //到达终点
            }

            if (point.getCol() + 1 < MAX_COL &&
                maze[point.getRow()][point.getCol() + 1] == 0) {
                visit(point.getRow(), point.getCol() + 1, point); //向右走
            }

            if (point.getRow() + 1 < MAX_ROW &&
                maze[point.getRow() + 1][point.getCol()] == 0) {
                visit(point.getRow() + 1, point.getCol() + 1, point); //向下走
            }

            if (point.getCol() - 1 >= 0 &&
                maze[point.getRow()][point.getCol() - 1] == 0) {
                visit(point.getRow(), point.getCol() - 1, point); //向左走
            }

            if (point.getRow() - 1 >= 0 &&
                maze[point.getRow() - 1][point.getCol()] == 0) {
                visit(point.getRow() - 1, point.getCol(), point);
            }
            printMaze();
        }

        if (point.getRow() == MAX_ROW - 1 && point.getCol() == MAX_COL - 1) {  //找到终点
            System.out.printf("(%d, %d)\n", point.getRow(),point.getCol());
            while (this.predecessor[point.getRow()][point.getCol()].getRow() != -1) {
                point = predecessor[point.getRow()][point.getCol()];
                System.out.printf("(%d, %d)\n", point.getRow(),point.getCol());
            }
        } else {
            System.out.println("No path!\n");
        }
    }

    private void visit(int row, int col, Point prePoint) {
        Point visitPoint = new Point(row, col);
        maze[row][col] = 2;
        predecessor[row][col] = prePoint;
        stack.push(visitPoint); //走过的点压栈便于后面回溯或者打印路线
    }

    private void printMaze() {
        for (int i = 0; i < MAX_ROW; i++) {
            for (int j = 0; j < MAX_COL; j++) {
                System.out.println(maze[i][j]);
            }
        }
        System.out.println("*******");
    }

    public boolean hasPath(char[] matrix, int rows, int cols, char[] str)
    {
        int visited[] = new int[matrix.length];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (hasPathHelper(matrix, rows, cols, i, j, str, 0, visited)) {
                    return true;
                }
            }
            
        }

        return false;
    }

    private boolean hasPathHelper(char[] matrix, int rows, int cols, int i, int j, char[] str, int k, int[] visited) {
        int index = i * cols + j;
        if (i < 0 || i >= rows || j < 0 || j >= cols || matrix[index] != str[k] || visited[index] == 1) {
            return false;
        }
        if (k == str.length - 1) return true;
        visited[index] = 1;
        if (hasPathHelper(matrix, rows, cols, i - 1, j, str, k + 1, visited)
            || hasPathHelper(matrix, rows, cols, i + 1, j, str, k + 1, visited)
                || hasPathHelper(matrix, rows, cols, i, j - 1, str, k + 1, visited)
                || hasPathHelper(matrix, rows, cols, i, j + 1, str, k + 1, visited)
        ) {
            return true;
        }
        visited[index] = 0;
        return false;

    }
    




}

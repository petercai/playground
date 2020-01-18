package cai.peter.algorithm.graph.matrix;

import java.util.LinkedList;
import java.util.Queue;

/*
You are given a m x n 2D grid initialized with these three possible values.

-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room.

We use the value 2^31 - 1 = 2147483647 to represent INF as you may assume
that the distance to a gate is less than 2147483647.

Fill each empty room with the distance to its nearest gate.
If it is impossible to reach a Gate, that room should remain filled with INF

Example1
Input:
[[2147483647,-1,0,2147483647],
[2147483647,2147483647,2147483647,-1],
[2147483647,-1,2147483647,-1],
[0,-1,2147483647,2147483647]]
Output:
[
[3,-1, 0, 1],
[2, 2, 1,-1],
[1,-1, 2,-1],
[0,-1, 3, 4]]

Explanation:
the 2D grid is:
INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF
the answer is:
  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4
Example2

Input:
[[0,-1],[2147483647,2147483647]]
Output:
[[0,-1],[1,2]]
 */
public class WallNGate {
    public void wallsAndGatesMagicArray(int[][] rooms) {
        // Write your code here
        int n = rooms.length;
        if (n == 0) {
            return;
        }
        int m = rooms[0].length;

        int dx[] = {0, 1, 0, -1};
        int dy[] = {1, 0, -1, 0};

        Queue<Integer> qx = new LinkedList<>();
        Queue<Integer> qy = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (rooms[i][j] == 0) {
                    qx.offer(i);
                    qy.offer(j);
                }
            }
        }

        while (!qx.isEmpty()) {
            int cx = qx.poll();
            int cy = qy.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if (0 <= nx && nx < n && 0 <= ny && ny < m
                        && rooms[nx][ny] == Integer.MAX_VALUE) {
                    qx.offer(nx);
                    qy.offer(ny);
                    rooms[nx][ny] = rooms[cx][cy] + 1;
                }
            }
        }
    }

    public void dfs(int[][] rooms) {
        if (rooms == null || rooms.length == 0) {
            return;
        }

        int m = rooms.length;
        int n = rooms[0].length;

        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    _dfs(i, j, 0, visited, rooms);
                }
            }
        }
    }

    private void _dfs(int row, int col, int distance, boolean[][] visited, int[][] rooms) {
        int rows = rooms.length;
        int cols = rooms[0].length;

        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            return;
        }

        // visited
        if (visited[row][col]) {
            return;
        }

        // Is wall?
        if (rooms[row][col] == -1) {
            return;
        }

        // Distance greater than current
        if (distance > rooms[row][col]) {
            return;
        }


        // Mark as visited
        visited[row][col] = true;

        if (distance < rooms[row][col]) {
            rooms[row][col] = distance;
        }

        // go up, down, left and right
        _dfs(row - 1, col, distance + 1, visited, rooms);
        _dfs(row + 1, col, distance + 1, visited, rooms);
        _dfs(row, col - 1, distance + 1, visited, rooms);
        _dfs(row, col + 1, distance + 1, visited, rooms);

        // Mark as unvisited
        visited[row][col] = false;
    }

    public void bfs(int[][] rooms) {
        if (rooms == null || rooms.length == 0) {
            return;
        }

        int m = rooms.length;
        int n = rooms[0].length;

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    _bfs(i, j, 0, rooms, queue);
                }
            }
        }
    }

    private void _bfs(int row, int col, int distance, int[][] rooms, Queue<Integer> queue) {
        _fill(row, col, distance, rooms, queue);

        int rows = rooms.length;
        int cols = rooms[0].length;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cord = queue.poll();
                int x = cord / cols;
                int y = cord % cols;

                _fill(x - 1, y, distance + 1, rooms, queue);
                _fill(x + 1, y, distance + 1, rooms, queue);
                _fill(x, y - 1, distance + 1, rooms, queue);
                _fill(x, y + 1, distance + 1, rooms, queue);

            }
            distance++;
        }
    }

    private void _fill(int row, int col, int distance, int[][] rooms, Queue<Integer> queue) {
        int rows = rooms.length;
        int cols = rooms[0].length;

        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            return;
        }

        if (rooms[row][col] == -1) {
            return;
        }

        if (distance > rooms[row][col]) {
            return;
        }

        if (distance < rooms[row][col]) {
            rooms[row][col] = distance;
        }

        int cord = row * cols + col;
        queue.offer(cord);
    }
}

package cai.peter.algorithm.graph.matrix;

import java.util.LinkedList;
import java.util.Queue;

/*
Description

Given a boolean 2D matrix, 0 is represented as the sea, 1 is represented as the island.
If two 1 is adjacent, we consider them in the same island.
We only consider up/down/left/right adjacent.

Find the number of islands.


Example 1:
Input:
[
  [1,1,0,0,0],
  [0,1,0,0,1],
  [0,0,0,1,1],
  [0,0,0,0,0],
  [0,0,0,0,1]
]
Output:
3

Example 2:
Input:
[
  [1,1]
]
Output:
1
 */
public class NumberOfIslands {
    class Coordinate {
        int x, y;
        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public int numIslands(boolean[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int n = grid.length;
        int m = grid[0].length;
        int islands = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j]) {
                    markByBFS(grid, i, j);
                    islands++;
                }
            }
        }

        return islands;
    }

    private void markByBFS(boolean[][] grid, int x, int y) {
        // magic numbers!
        int[] directionX = {0, 1, -1, 0};
        int[] directionY = {1, 0, 0, -1};

        Queue<Coordinate> queue = new LinkedList<>();

        queue.offer(new Coordinate(x, y));
        grid[x][y] = false;

        while (!queue.isEmpty()) {
            Coordinate coor = queue.poll();
            for (int i = 0; i < 4; i++) {
                Coordinate adj = new Coordinate(
                        coor.x + directionX[i],
                        coor.y + directionY[i]
                );
                if (!inBound(adj, grid)) {
                    continue;
                }
                if (grid[adj.x][adj.y]) {
                    grid[adj.x][adj.y] = false;
                    queue.offer(adj);
                }
            }
        }
    }

    private boolean inBound(Coordinate coor, boolean[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        return coor.x >= 0 && coor.x < n && coor.y >= 0 && coor.y < m;
    }


    /*
    The basic idea of the following solution is merging adjacent lands, and the merging should be done recursively.
    Each element is visited once only. So time is O(m*n).
     */
    public int numIslandsDFS(char[][] grid) {
        if(grid==null || grid.length==0||grid[0].length==0)
            return 0;

        int rows = grid.length;
        int cols = grid[0].length;

        int count=0;
        for(int i = 0; i< rows; i++){
            for(int j = 0; j< cols; j++){
                if(grid[i][j]=='1'){
                    count++;
                    _merge(grid, i, j);
                }
            }
        }

        return count;
    }

    private void _merge(char[][] grid, int i, int j){
        int m=grid.length;
        int n=grid[0].length;

        if(i<0||i>=m||j<0||j>=n||grid[i][j]!='1')
            return;

        grid[i][j]='X';

        _merge(grid, i-1, j);
        _merge(grid, i+1, j);
        _merge(grid, i, j-1);
        _merge(grid, i, j+1);
    }
}

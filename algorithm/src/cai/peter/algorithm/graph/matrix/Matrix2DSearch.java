package cai.peter.algorithm.graph.matrix;

import org.junit.Assert;
import org.junit.Test;

/*
Description
Write an efficient algorithm that searches for a value in an m x n matrix,
return the occurrence of it.

This matrix has the following properties:

Integers in each row are sorted from left to right.
Integers in each column are sorted from up to bottom.
No duplicate integers in each row or column.
Have you met this question in a real interview?

Example 1:
Input:
	[[3,4]]
	target=3
Output:1

Example 2:
Input:
    [
      [1, 3, 5, 7],
      [2, 4, 7, 8],
      [3, 5, 9, 10]
    ]
    target = 3
Output:2

https://www.jiuzhang.com/solution/search-a-2d-matrix-ii/
 */
public class Matrix2DSearch {
  public int search(int[][] a, int target) {
    return 0;
  }

  public int searchMatrix1(int[][] matrix, int target) {
    // write your code here
    int r = matrix.length - 1;
    int c = 0;
    int ans = 0;
    while (r >= 0 && c < matrix[0].length) {
      if (target == matrix[r][c]) {
        ans++;
        r--;
        c++;
        continue;
      }
      if (target < matrix[r][c]) {
        r--;
      } else {
        c++;
      }
    }
    return ans;
  }

  public int searchMatrix(int[][] matrix, int target) {
    // check corner case
    if (matrix == null || matrix.length == 0) {
      return 0;
    }
    if (matrix[0] == null || matrix[0].length == 0) {
      return 0;
    }

    // from bottom left to top right
    int n = matrix.length;
    int m = matrix[0].length;
    int x = n - 1;
    int y = 0;
    int count = 0;

    while (x >= 0 && y < m) {
      if (matrix[x][y] < target) {
        y++;
      } else if (matrix[x][y] > target) {
        x--;
      } else {
        count++;
        x--;
        y++;
      }
    }
    return count;
  }

  @Test
  public void test() {
    int[][] a = new int[][] {{3, 4}};
    Assert.assertEquals(1, searchMatrix1(a, 3));
  }
  @Test
    public void test1(){
      int[][] a = new int[][]{
      {1, 3, 5, 7},
      {2, 4, 7, 8},
      {3, 5, 9, 10}
    };
  }
}

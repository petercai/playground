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
    public int search(int[][] a, int target){
        return 0;
    }
    @Test
    public void test(){
        int[][] a = new int[][]{{3,4}};
        Assert.assertEquals(1,search(a,3));
    }
}

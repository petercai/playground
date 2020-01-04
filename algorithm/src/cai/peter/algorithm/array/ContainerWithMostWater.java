package cai.peter.algorithm.array;

import org.junit.Assert;
import org.junit.Test;

/*
Given n non-negative integers a 1 , a 2 , ..., an, where each represents a point at coordi-
nate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai)
and (i, 0 ). Find two lines, which together with x-axis forms a container, such that the
container contains the most water.

Initially we can assume the result is 0.
Then we scan from both sides.
If leftHeight < rightHeight, move right and find a value that is greater than leftHeight.
Similarily, if leftHeight > rightHeight, move left and find a value that is greater than rightHeight.
Additionally, keep tracking the max value.

 */
public class ContainerWithMostWater {
    public int getMostWater(int[] a){
        if( a==null && a.length<2) return 0;
        int max = 0, i=0,j=a.length-1;
        while(i<j){
            max = Math.max(max, (j-i)*Math.min(a[i],a[j]));
            if( a[i]<a[j]) i++;
            else j--;
            j=j;
        }
        return max;
    }


    @Test
    public void test(){
        int[] a = new int[]{3,5,4,6,9,8};
        Assert.assertEquals(20, getMostWater(a));
    }
}

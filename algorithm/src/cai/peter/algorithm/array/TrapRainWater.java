package cai.peter.algorithm.array;

import org.junit.Assert;
import org.junit.Test;

/*
Given n non-negative integers representing an elevation map where the width of each
bar is 1 , compute how much water it is able to trap after raining.
For example, given [ 0 , 1 , 0 , 2 , 1 , 0 , 1 , 3 , 2 , 1 , 2 , 1 ], return 6 .
 */
public class TrapRainWater {
    public int mostWaterDP(int[] a){
        int result = 0;
        if( a==null || a.length < 2 ) return result;

        int len = a.length;
        int[] i = new int[len];
        int[] j = new int[len];

        int max = a[0];
        i[0] = a[0];
        for(int m = 1; m< len; m++){
            max = Math.max(max, a[m]);
            i[m] = max;
        }
        max = a[len-1];
        j[len-1] = max;
        for( int m=len-2;m>=0;m--){
            max = Math.max(max,a[m]);
            j[m] =  max;
        }
        for(int m = 0;m<len;m++){
            result += Math.min(i[m],j[m]) - a[m];
        }
        return result;
    }

    @Test
    public void test(){
        int[] a = new int[]{0 , 1 , 0 , 2 , 1 , 0 , 1 , 3 , 2 , 1 , 2 , 1};
        Assert.assertEquals(6,mostWaterDP(a));
    }
}

package cai.peter.algorithm.array;

import java.util.ArrayList;
import java.util.List;

/*
Given an array of n positive integers and a positive integer s, ﬁnd the minimal length
of a subarray of which the sum ≥ s. If there isn’t one, return 0 instead.
For example, given the array [ 2 , 3 , 1 , 2 , 4 , 3 ] and s = 7 , the subarray [ 4 , 3 ] has the minimal
length of 2 under the problem constraint.
 */
public class SubarraySum {
    public int subarray(int[] src, int sum){
        int i=0,j=src.length-1;
        int res = 0;
        int min=0;
        List<Integer> holder = new ArrayList<>();
        while(i!=j){
            res = src[i]+src[j];
            min = Math.min(src[i],src[j]);
            if (Integer.valueOf(sum).compareTo(res) > 0 )
            {
                i++;
            }
            else{
                j++;
            }
        }
        return 0;
    }
}

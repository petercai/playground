package cai.peter.algorithm.array;

import org.junit.Test;

import java.util.Arrays;

/*
Given a sorted array, remove the duplicates in place such that each element appear
only once and return the new length. Do not allocate extra space for another array,
you must do this in place with constant memory.
For example, given input array A = [ 1 , 1 , 2 ], your function should return length = 2 ,
and A is now [ 1 , 2 ]
 */
public class RemoveDuplicateFromArray {
    public int remove(int[] a)
    {
        int i = 0;
        for(int j=1;j<a.length;j++){
            if(a[i]!=a[j]){
                i++; // the slow pointer catch up first
                a[i] = a[j]; // move item back
            }
        }
        return i+1; // the slow one is the pointer of length
    }

    @Test
    public void test(){
        int[] a= new int[]{1,2,2,2,3,5};
        System.out.print(remove(a));
        System.out.print(":");
        System.out.println(Arrays.toString(a));
    }
}

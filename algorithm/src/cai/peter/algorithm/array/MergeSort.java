package cai.peter.algorithm.array;

import org.junit.Test;

import java.util.Arrays;

/*
To combine both collections Mergesort starts in each collection at the beginning.
It picks the object which is smaller and inserts this object into the new collection.
For this collection it now selects the next elements and selects the smaller element from both collections.
 */

/*
In comparison to Quicksort the divide part in Mergesort is simple, while the merging part is complex.

Quicksort can sort "inline" in an existing collection,
e.g. it does not have to create a copy of the collection while Mergesort requires a copy.
 */
public class MergeSort {
    int[] helper;
    /*
     * O(nlog(n))
     */
    public void mergeSort(int[] a)
    {
        if( a==null || a.length < 2 ) return ;
        helper = new int[a.length];
        _divide(a,0, a.length-1);
    }

    void _divide(int[] ar, int start, int end)
    {
        if( start < end )
        {
            int mid = start + (end-start)/2;
            _divide(ar, start, mid);
            _divide(ar,mid+1,end);
            _conquer(ar, start, mid, end);
        }

    }
    void _conquer(int[]ar, int start, int mid, int end)
    {
        /*
        make a copy
         */
        for(int i=start;i<=end;i++)
        {
            helper[i] = ar[i];
        }
        int i=start;
        int j = mid+1;
        int k = start;
        // Copy the smallest values from either the left or the right side back
        // to the original array
        while(i<=mid&&j<=end)
        {
            if( helper[i]<=helper[j])
                ar[k]=helper[i++];
            else
                ar[k]=helper[j++];
            k++;
        }
        // Copy the rest of the left side of the array into the target array (if there is)
        while(i<=mid)
            ar[k++]=helper[i++];
    }

    @Test
    public  void testMergeSort()
    {

        int a[] = new int[] {49,38,65,97,76,13,27,49,78,34,12,64,5,4,62,99,98,54,56,17,18,23,34,15,35,25,53,51};


        System.out.println(Arrays.toString(a));
        mergeSort(a);
        System.out.println(Arrays.toString(a));
    }
}

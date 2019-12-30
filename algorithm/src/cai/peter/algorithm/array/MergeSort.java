package cai.peter.algorithm.array;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class MergeSort {
    int[] ar;
    int[] helper;
    /*
     * O(nlog(n))
     */
    public void mergeSort(int[] a)
    {
        if( a==null || a.length < 2 ) return ;
        ar = a;
        helper = new int[a.length];

        _divide(0, a.length-1);
    }

    void _divide(int start, int end)
    {
        if( start < end )
        {
            int mid = start + (end-start)/2;
            _divide(start, mid);
            _divide(mid+1,end);
            _merge(start, mid, end);
        }

    }
    void _merge(int start, int mid, int end)
    {
        for(int i=start;i<=end;i++)
        {
            helper[i] = ar[i];
        }
        int i=start;
        int j = mid+1;
        int k = start;
        while(i<=mid&&j<=end)
        {
            if( helper[i]<=helper[j])
                ar[k]=helper[i++];
            else
                ar[k]=helper[j++];
            k++;
        }
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

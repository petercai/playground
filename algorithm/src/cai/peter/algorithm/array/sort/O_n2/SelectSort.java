package cai.peter.algorithm.array.sort.O_n2;

import cai.peter.algorithm.array.ArrayUtil;
import org.junit.Test;

import java.util.Arrays;

/*
Selection Sort begins with the element in the 1st position of an unsorted array
and scans through subsequent elements to find the smallest element.
Once found, the smallest element is swapped with the element in the 1st position.

The algorithm then moves on to the element in the 2nd position and scans through subsequent elements
to find the index of the 2nd smallest element.
Once found, the second smallest element is swapped with the element in the 2nd position.

This process goes on until we reach the n-1th element of the array,
which puts the n-1th smallest element in the n-1th position.
The last element automatically falls in place, in the n-1th iteration, thereby sorting the array.
 */
public class SelectSort {
  public void sort(int[] a) {
    if (a == null || a.length < 2) return;
    for (int i = 0; i < a.length; i++) {
      int min = i;
      for (int j = i + 1; j < a.length; j++) {
        if (a[min] > a[j]) min = j; // start from i, find minimum in j without swap
      }
      if (min != i) ArrayUtil.swap(a, i, min);
    }
  }
  @Test
    public void test(){
      int a[] = new int[] {49,38,65,97,76,13,27,49,78,34,12,64,5,4,62,99,98,54,56,17,18,23,34,15,35,25,53,51};

      System.out.println(Arrays.toString(a));
      sort(a);
      System.out.print(Arrays.toString(a));
  }
}

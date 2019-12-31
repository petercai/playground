package cai.peter.algorithm.array.sort.O_nLogn;

import org.junit.Test;

import java.util.Arrays;

public class QuickSort {
  /*
   * O(nlog(n))
   */
  public void sort(int[] a) {
    if (a == null || a.length < 2) return;
    conquer(a, 0, a.length - 1);
  }

  private void conquer(int[] a, int start, int end) {
    if (start >= end) return;

    int pivot = a[start + (end - start) / 2];
    int i = start, j = end;
    while (i <= j) {
      while (a[i] < pivot) i++;
      while (a[j] > pivot) j--;
      if (i <= j) swap(a, i++, j--);
    }
    if (start < j) conquer(a, start, j);
    if (end > i) conquer(a, i, end);
  }

  private void swap(int[] a, int f, int s) {
    int t = a[f];
    a[f] = a[s];
    a[s] = t;
  }

  @Test
  public void test() {
    int a[] =
        new int[] {
          49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34,
          15, 35, 25, 53, 51
        };

    System.out.println(Arrays.toString(a));
    sort(a);
    System.out.println(Arrays.toString(a));
  }
}

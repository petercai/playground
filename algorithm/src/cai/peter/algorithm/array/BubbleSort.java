package cai.peter.algorithm.array;

import org.junit.Test;

import java.util.Arrays;

public class BubbleSort {
  public void sort(int[] a) {
    if (a == null || a.length < 2) return;
    for (int i = a.length - 1; i > 0; i--) {
      /*
      the biggest goes to the end of array in each iteration
       */
      for (int j = 0; j < i; j++) {
        if (a[j] > a[j + 1]) swap(a, j, j + 1);
      }
    }
  }

  private void swap(int[] a, int i, int j) {
    int t = a[i];
    a[i] = a[j];
    a[j] = t;
  }

  @Test
    public void test(){
      int a[] = new int[] {49,38,65,97,76,13,27,49,78,34,12,64,5,4,62,99,98,54,56,17,18,23,34,15,35,25,53,51};
    System.out.println(Arrays.toString(a));
    sort(a);
    System.out.println(Arrays.toString(a));
  }
}

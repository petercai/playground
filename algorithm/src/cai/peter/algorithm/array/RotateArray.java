package cai.peter.algorithm.array;

import org.junit.Test;

import java.util.Arrays;
import java.util.function.Consumer;

/*
Rotate an array of n elements to the right by k steps.
For example, with n = 7 and k = 3 , the array [ 1 , 2 , 3 , 4 , 5 , 6 , 7 ] is rotated to [ 5 , 6 , 7 , 1 , 2 , 3 , 4 ].



 */
public class RotateArray {
  public void r1(int[] src, int k) {
    int len = src.length;
    int order = len - k;
    int[] tmp = new int[len];
    for (int i = 0; i < order; i++) {
      tmp[i + k] = src[i];
    }
    for (int i = order; i < len; i++) {
      tmp[i - order] = src[i];
    }

    System.arraycopy(tmp, 0, src, 0, len);
  }

  public void r2(int[] a, int k){
    int seg = a.length-k;
    _r(a, 0, seg-1);
    _r(a, seg, a.length-1);
    _r(a, 0, a.length-1);
  }
  public void _r(int[]a, int i, int j){
    while( i<j){
      int t = a[i];
      a[i]=a[j];
      a[j]=t;
      i++;
      j--;
    }
  }

  @Test
  public void testRotation() {
    int[] s = {1, 2, 3, 4, 5, 6, 7};
    r2(s, 3);
    System.out.println(Arrays.toString(s));
  }
}

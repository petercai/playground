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

  interface Triple {
    void apply(int[] a, int start, int end);
  }

  public void r2(int[] s, int k) {
    Triple reverse =
        (int[] a, int start, int end) -> {
          while (start < end) {
            int tmp = a[start];
            a[start] = a[end];
            a[end] = tmp;
            start++;
            end--;
          }
        };

    int order = s.length-k;//7-3
    reverse.apply(s,0,order-1);
    reverse.apply(s,order, s.length-1);
    reverse.apply(s,0,s.length-1);
  }

  @Test
  public void testRotation() {
    int[] s = {1, 2, 3, 4, 5, 6, 7};
    r2(s, 3);
    System.out.println(Arrays.toString(s));
  }
}

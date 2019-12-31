package cai.peter.algorithm.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ArrayUtil {
  /*
      Given an array of meeting time intervals consisting of start and end times [[s 1 ,e 1 ],[s 2 ,e 2 ],...]
  ﬁnd the minimum number of conference rooms required.
       */
  public boolean missingNumber(int[] s) {
    return false;
  }

   /*
      Given an array of integers, ﬁnd if the array contains any duplicates. Your function
  should return true if any value appears at least twice in the array, and it should return
  false if every element is distinct
       */
  public boolean containsDuplicate(int[] s) {
    return false;
  }


  public static int a[] = new int[] {49,38,65,97,76,13,27,49,78,34,12,64,5,4,62,99,98,54,56,17,18,23,34,15,35,25,53,51};

  public static  void swap(int[] a, int i, int j) {
    int tmp = a[i];
    a[i] = a[j];
    a[j] = tmp;
  }
}

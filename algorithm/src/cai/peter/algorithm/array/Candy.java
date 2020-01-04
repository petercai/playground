package cai.peter.algorithm.array;

import org.junit.Test;

import java.util.Arrays;

/*
There are N children standing in a line. Each child is assigned a rating value. You are
giving candies to these children subjected to the following requirements:
1 . Each child must have at least one candy. 2 . Children with a higher rating get
more candies than their neighbors.
What is the minimum candies you must give?


We can always assign a neighbor with 1 more if the neighbor has higher a rating value.
However, to get the minimum total number, we should always start adding 1s in the ascending order.
We can solve this problem by scanning the array from both sides.

First, scan the array from left to right, and assign values for all the ascending pairs.
Then scan from right to left and assign values to descending pairs.
 */
public class Candy {
  public int assign(int[] rates) {
    int[] candies = new int[rates.length];
    candies[0] = 1;

    for (int i = 1; i < rates.length; i++) {
      if (rates[i] > rates[i - 1]) candies[i] = candies[i - 1] + 1;
      else candies[i] = 1;
    }
    int result = candies[candies.length - 1];
    for (int i = rates.length - 2; i >= 0; i--) {
      if (rates[i] > rates[i + 1]) candies[i] += 1;
      result += candies[i];
    }

    return result;
  }

  public int trapRainWater(int[] a){
    return 0;
  }

  @Test
  public void test() {
    int[] r = {3, 1, 5, 4, 2};
    System.out.println(assign((r)));
  }
}

package cai.peter.algorithm.array;

import org.junit.Test;

import java.util.Arrays;

/*
There are N children standing in a line. Each child is assigned a rating value. You are
giving candies to these children subjected to the following requirements:
1 . Each child must have at least one candy. 2 . Children with a higher rating get
more candies than their neighbors.
What is the minimum candies you must give?
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

  @Test
  public void test() {
    int[] r = {3, 1, 5, 4, 2};
    System.out.println(assign((r)));
  }
}
